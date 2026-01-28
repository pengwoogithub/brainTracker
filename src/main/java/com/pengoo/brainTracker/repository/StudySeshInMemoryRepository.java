package com.pengoo.brainTracker.repository;

import com.pengoo.brainTracker.model.entity.StudySession;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class StudySeshInMemoryRepository {

    private List<StudySession> sessionList = new ArrayList<>();
    private long idCounter = 1;

    public StudySeshInMemoryRepository(){
        sessionList.add(new StudySession(idCounter++, LocalDate.now(), 50, 5));
    }

    public StudySession save(StudySession session){
        session.setId(idCounter++);
        sessionList.add(session);
        return session;
    }

    public List<StudySession> getAllSessions(){
        return sessionList;
    }
}
