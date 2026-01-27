package com.pengoo.brainTracker.controller;


import com.pengoo.brainTracker.dto.StudySessionDto;
import com.pengoo.brainTracker.dto.XpResponse;
import com.pengoo.brainTracker.model.entity.StudySession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
public class BrainXpController {

    private int totalXp = 0;
    private List<StudySession> sessionList = new ArrayList<>();

    @GetMapping("/hello")
    public String hello(){
        return "Brain Tracker is working, W speed";
    }

    @PostMapping("/sessions")
    public XpResponse logSession(@RequestBody StudySessionDto dto){
        int xpEarned = dto.getMinutesStudied()/10;
        totalXp += xpEarned;

        StudySession session =  new StudySession(
                null,
                LocalDate.now(),
                dto.getMinutesStudied(),
                xpEarned
        );
        sessionList.add(session);
        return new XpResponse(xpEarned, totalXp);
    }

    @GetMapping("/sessions")
    public List<StudySession> getSessionList(){
        return sessionList;
    }

    @GetMapping("/xp")
    public XpResponse getTotalXp() {
        // Return total XP (xpEarned = 0 because no new session in this call)
        return new XpResponse(0, totalXp);
    }
}
