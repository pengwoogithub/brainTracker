package com.pengoo.brainTracker.dto;

public class StudySessionDto {

    private int minutesStudied;

    public StudySessionDto(int minutesStudied){
        this.minutesStudied = minutesStudied;
    }

    public int getMinutesStudied() {
        return minutesStudied;
    }
}
