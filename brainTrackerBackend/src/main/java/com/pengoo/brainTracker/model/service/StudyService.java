package com.pengoo.brainTracker.model.service;


import com.pengoo.brainTracker.dto.*;
import com.pengoo.brainTracker.model.entity.StudySession;
import com.pengoo.brainTracker.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StudyService {

    @Qualifier("SessionRepository")
    private SessionRepository repository;
    private int totalXp = 0;

    public StudyService(SessionRepository repository) {
        this.repository = repository;
    }

    public XpResponse addSession(CreateSessionRequest dto) {
        int xpEarned = dto.getMinutesStudied() / 10;
        totalXp += xpEarned;

        StudySession session = new StudySession(
                dto.getTopic(),
                LocalDate.now(),
                dto.getMinutesStudied(),
                xpEarned
        );

        repository.save(session);

        return new XpResponse(xpEarned, totalXp);
    }

    public List<StudySessionResponse> getAllSessions() {
        return repository.findAll().stream()
                .map(s -> new StudySessionResponse(s.getMinutesStudied(), s.getXpEarned(), s.getDate()))
                .collect(Collectors.toList());
    }

    public TotalXpResponse getTotalXp() {
        int totalXp = repository.findAll().stream().mapToInt(StudySession::getXpEarned).sum();
        int sessionsCount = repository.findAll().size();

        return new TotalXpResponse(totalXp, sessionsCount);
    }

    //just need a check condition for streak
    public XpResponse checkStreakBonus() {
        List<LocalDate> dateList = repository.findAll()
                .stream()
                .map(StudySession::getDate)
                .sorted()
                .toList();

        int streak = dateList.isEmpty() ? 0 : 1;
        for (int i = 1; i < dateList.size(); i++) {
            if (dateList.get(i).equals(dateList.get(i - 1).plusDays(1))) {
                streak++;
            } else {
                streak = 1;
            }
        }
        //check
        if (!dateList.isEmpty() && dateList.getLast().plusDays(1).equals(LocalDate.now())) {
            streak++;
        }

        int streakBonus = streak;
        int totalIncludingBonus = totalXp + streakBonus;

        return new XpResponse(streakBonus, totalIncludingBonus);
    }

    public SummaryResponse getSummaryToday() {

        List<StudySession> studyListToday = repository.findAll()
                .stream()
                .filter(s -> s.getDate().equals(LocalDate.now()))
                .toList();

        int totalStudyMinToday = studyListToday
                .stream()
                .mapToInt(StudySession::getMinutesStudied)
                .sum();

        int totalXpToday = studyListToday
                .stream()
                .mapToInt(StudySession::getXpEarned)
                .sum();

        return new SummaryResponse(LocalDate.now(), totalStudyMinToday, totalXpToday);
    }

    public List<WeekSummaryResponse> getSummaryWeek() {
        Map<LocalDate, List<StudySession>> listWeek = repository.findAll()
                .stream()
                .filter(s -> s.getDate().isAfter(LocalDate.now().minusDays(6)))
                .collect(Collectors.groupingBy(StudySession::getDate));

        List<WeekSummaryResponse> weekSummaryList = new ArrayList<>();
        for (Map.Entry<LocalDate, List<StudySession>> entry : listWeek.entrySet()) {
            LocalDate date = entry.getKey();
            List<StudySession> session = entry.getValue();

            int totalMinutesWeek = session.stream()
                    .mapToInt(StudySession::getMinutesStudied)
                    .sum();

            int totalXpWeek = session.stream()
                    .mapToInt(StudySession::getXpEarned)
                    .sum();

            weekSummaryList.add(new WeekSummaryResponse(date, totalMinutesWeek, totalXpWeek));
        }

        return weekSummaryList;
    }

    public LongestStreakResponse getLongestStreak() {
        List<StudySession> listSessions = repository.findAll(Sort.by("date"));
        int longestStreak = 0;

        LocalDate startDate = null;
        LocalDate lastDate = null;

        int streakCount = listSessions.isEmpty()? 0 : 1;
        for (int i = 1; i < listSessions.size(); i++) {
            if (listSessions.get(i).getDate().isEqual(listSessions.get(i-1).getDate().plusDays(1))) {
                streakCount++;
            } else {
                if(streakCount>longestStreak) {
                    longestStreak = streakCount;
                    lastDate = listSessions.get(i).getDate();
                    startDate = lastDate.minusDays(streakCount);
                }
                streakCount = 1;
            }
        }
        if(streakCount==1){
             return new LongestStreakResponse(longestStreak, LocalDate.now(), LocalDate.now());
        }
        if(streakCount>longestStreak) {
            longestStreak = streakCount;
            lastDate = listSessions.getLast().getDate();
            startDate = lastDate.minusDays(streakCount);
        }
        return new LongestStreakResponse(longestStreak, startDate, lastDate);
    }
}















