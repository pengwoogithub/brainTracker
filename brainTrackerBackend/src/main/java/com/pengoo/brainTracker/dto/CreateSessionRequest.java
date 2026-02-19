package com.pengoo.brainTracker.dto;

public class CreateSessionRequest {
    private int minutesStudied;


    public CreateSessionRequest(int minutesStudied){
        this.minutesStudied = minutesStudied;
    }

    public int getMinutesStudied(){
        return minutesStudied;
    }

    public void setMinutesStudies(int minutesStudied){
        this.minutesStudied = minutesStudied;
    }
}
