package com.pengoo.brainTracker.dto;

import java.time.LocalDate;

public class StudySessionResponse {

    private int minuteStudied;
    private int xpEarned;
    private LocalDate date;

    public StudySessionResponse(int minuteStudied, int xpEarned, LocalDate date){
        this.minuteStudied = minuteStudied;
        this.xpEarned = xpEarned;
        this.date = date;
    }

    public int getMinuteStudied(){
        return minuteStudied;
    }

    public int getXpEarned() {
        return xpEarned;
    }

    public LocalDate getDate() {
        return date;
    }
}
