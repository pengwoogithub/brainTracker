package com.pengoo.brainTracker.model.entity;


import java.time.LocalDate;

public class StudySession {

    private long id;
    private LocalDate date;
    private int minutesStudied;
    private int xpEarned;


    public StudySession(long id, LocalDate date, int minutesStudied, int xpEarned){
        this.id = id;
        this.date = date;
        this.minutesStudied = minutesStudied;
        this.xpEarned = xpEarned;
    }

    //setters
    public void setId(long id){
        this.id = id;
    }
    public void setDate(LocalDate date){
        this.date = date;
    }
    public void setMinutesStudied(int minutesStudied){
        this.minutesStudied = minutesStudied;
    }
    public void setXpEarned(int xpEarned){
        this.xpEarned = xpEarned;
    }

    public int getMinutesStudied() {
        return minutesStudied;
    }
    public int getXpEarned(){return xpEarned;}
    public LocalDate getDate(){return date;}
}
