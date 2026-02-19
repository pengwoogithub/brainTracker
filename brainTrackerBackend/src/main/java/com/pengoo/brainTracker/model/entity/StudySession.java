package com.pengoo.brainTracker.model.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class StudySession {

    @Id
    @GeneratedValue
    private Long id;

    private String topic;
    private LocalDate date;
    private int minutesStudied;
    private int xpEarned;

    //JPA
    protected StudySession(){
    }

    public StudySession(String topic, LocalDate date, int minutesStudied, int xpEarned){
        this.topic = topic;
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

    //getters
    public Long getId(){return id;}
    public String getTopic(){return topic;}
    public int getMinutesStudied() {
        return minutesStudied;
    }
    public int getXpEarned(){return xpEarned;}
    public LocalDate getDate(){return date;}
}
