import { useState, useEffect } from "react";

function App() {
  const [sessions, setSessions] = useState([]);
  const [minutes, setMinutes] = useState("");
  const [totalXp, setTotalXp] = useState(0);
  const [totalXpToday, setTotalXpToday] = useState(0);
  const [longestStreak, setLongestStreak] = useState(0);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");

  //fetch session
  const fetchSessions = async () => {
    try {
      const res = await fetch("http://localhost:8080/study");
      if (!res.ok) throw new Error("Failed to fetch sessions");
      const data = await res.json();

      const sorted = [...data].sort(
        (a, b) => new Date(b.date) - new Date(a.date)
      );

      setSessions(sorted);
    } catch (err) {
      setError("Could not load sessions.");
      console.error(err);
    }
  };

  const fetchSummary = async () => {
    try {
      const res = await fetch("http://localhost:8080/study/summary");
      if (!res.ok) throw new Error("Failed to fetch summary");
      const data = await res.json();
      setTotalXp(data.totalXp);
    } catch (err) {
      console.error(err);
    }
  };

  const fetchSummaryToday = async() => {
    try {
      const res = await fetch("http://localhost:8080/study/summary/today");
      if(!res.ok) throw new Error("Faiked to fetch summary");
      const data = await res.json();
    setTotalXpToday(data.totalXpToday);

    }catch(err){
      console.error(err);
    }
    
  };

  const fetchLongestStreak = async () => {
    try {
      const res = await fetch("http://localhost:8080/study/streak/longest");
      if (!res.ok) throw new Error("Failed to fetch streak");
      const data = await res.json();

      setLongestStreak({
        streak: data.longestStreak ?? 0
      });

    } catch (err) {
      console.error(err);
    }
  };

  // initial load
  useEffect(() => {
    fetchSessions();
    fetchSummary();
    fetchLongestStreak();
  }, []);

  // session add
  const addSession = async () => {
    if (!minutes || minutes <= 0) return;

    try {
      setLoading(true);
      setError("");

      const res = await fetch("http://localhost:8080/study", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ minutesStudied: Number(minutes) }),
      });

      if (!res.ok) throw new Error("Failed to add session");

      setMinutes("");

      // Refresh everything after adding
      await fetchSessions();
      await fetchSummary();
      await fetchLongestStreak();
    } catch (err) {
      setError("Failed to add session.");
      console.error(err);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div style={{ padding: "30px", fontFamily: "Arial" }}>
      <h1>🧠 BrainTracker</h1>

      {/* STATS */}
      <div style={{ marginBottom: "20px" }}>
        <h2>Total XP: {totalXp}</h2>
        <h2>Total XP Today: {totalXpToday}</h2>
        <h3>Longest Streak: {longestStreak?.streak ?? 0} days</h3>
      </div>

      {/* ADD SESSION */}
      <div style={{ marginBottom: "20px" }}>
        <input
          type="number"
          placeholder="Minutes studied"
          value={minutes}
          onChange={(e) => setMinutes(e.target.value)}
          style={{ padding: "5px", marginRight: "10px" }}
        />

        <button
          onClick={addSession}
          disabled={loading}
          style={{ padding: "6px 12px" }}
        >
          {loading ? "Adding..." : "Add Session"}
        </button>
      </div>

      {error && <p style={{ color: "red" }}>{error}</p>}

      <hr />

      {/* SESSION LIST */}
      <h2>Sessions</h2>

      {sessions.length === 0 ? (
        <p>No sessions yet.</p>
      ) : (
        <ul>
          {sessions.map((s) => (
            <li key={s.id}>
              {s.date} — {s.minutesStudied} min — {s.xpEarned} XP
            </li>
          ))}
        </ul>
      )}
    </div>
  );
}

export default App;
