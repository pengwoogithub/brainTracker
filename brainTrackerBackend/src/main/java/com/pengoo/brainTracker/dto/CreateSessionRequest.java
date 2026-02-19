package com.pengoo.brainTracker.dto;

public class CreateSessionRequest {
    private String topic;
    private int minutesStudied;


    public CreateSessionRequest(String topic, int minutesStudied){
        this.topic = topic;
        this.minutesStudied = minutesStudied;
    }


    //getters
    public int getMinutesStudied(){
        return minutesStudied;
    }
    public String getTopic(){return topic;}

}
