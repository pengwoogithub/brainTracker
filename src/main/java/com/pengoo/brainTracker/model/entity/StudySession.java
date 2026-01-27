package com.pengoo.brainTracker.model.entity;


import java.time.LocalDate;

public class StudySession {

    private Long id;
    private LocalDate date;
    private int minutesStudied;
    private int xpEarned;


    public StudySession(Long id, LocalDate date, int minutesStudied, int xpEarned){
        this.id = id;
        this.date = date;
        this.minutesStudied = minutesStudied;
        this.xpEarned = xpEarned;
    }

    public int getMinutesStudied() {
        return minutesStudied;
    }
}
