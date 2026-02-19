package com.pengoo.brainTracker.dto;

import java.time.LocalDate;

public class StudySessionResponse {

    private int minutesStudied;
    private int xpEarned;
    private LocalDate date;

    public StudySessionResponse(int minutesStudied, int xpEarned, LocalDate date){
        this.minutesStudied = minutesStudied;
        this.xpEarned = xpEarned;
        this.date = date;
    }

    public int getMinutesStudied(){
        return minutesStudied;
    }

    public int getXpEarned() {
        return xpEarned;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "StudySessionResponse{" +
                "minutesStudied=" + minutesStudied +
                ", xpEarned=" + xpEarned +
                ",Date=" + date + '\'' +
                '}';
    }
}
