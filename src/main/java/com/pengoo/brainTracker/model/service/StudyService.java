package com.pengoo.brainTracker.model.service;


import com.pengoo.brainTracker.dto.CreateSessionRequest;
import com.pengoo.brainTracker.dto.StudySessionResponse;
import com.pengoo.brainTracker.dto.TotalXpResponse;
import com.pengoo.brainTracker.dto.XpResponse;
import com.pengoo.brainTracker.model.entity.StudySession;
import com.pengoo.brainTracker.repository.StudySeshInMemoryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudyService {


    private StudySeshInMemoryRepository repository;
    private int totalXp = 0;

    public StudyService(StudySeshInMemoryRepository repository){
        this.repository = repository;
    }

    public XpResponse addSession(CreateSessionRequest dto){
        int xpEarned = dto.getMinutesStudied()/10;
        totalXp += xpEarned;

        StudySession session = new StudySession(
          0,
          LocalDate.now(),
          dto.getMinutesStudied(),
          xpEarned
        );

        repository.save(session);

        return new XpResponse(xpEarned,totalXp);
    }

    public List<StudySessionResponse> getAllSessions(){
       return repository.getAllSessions().stream()
               .map(s -> new StudySessionResponse(s.getMinutesStudied(), s.getXpEarned(), s.getDate()))
               .collect(Collectors.toList());
    }

    public TotalXpResponse getTotalXp(){
        int totalXp = repository.getAllSessions().stream().mapToInt(StudySession::getXpEarned).sum();
        int sessionsCount = repository.getAllSessions().size();

        return new TotalXpResponse(totalXp, sessionsCount);
    }

    //just need a check condition for streak
    public XpResponse checkStreakBonus(){
         List<LocalDate> dateList = repository.getAllSessions()
                 .stream()
                 .map(StudySession::getDate)
                 .sorted()
                 .toList();

        int streak = dateList.isEmpty()? 0 : 1;
         for(int i = 1;i<dateList.size();i++){
             if(dateList.get(i).equals(dateList.get(i-1).plusDays(1))){
                streak++;
             }
             else{
                 streak = 1;
             }
         }
        if(!dateList.isEmpty() && dateList.getLast().plusDays(1).equals(LocalDate.now())){
            streak++;
        }

        int streakBonus = streak;
        int totalIncludingBonus = totalXp + streakBonus;

        return new XpResponse(streakBonus,totalIncludingBonus);

    }
}
