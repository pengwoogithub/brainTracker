package com.pengoo.brainTracker.dto;

public class XpResponse {

    private int xpEarned;
    private int totalXp;

    public XpResponse(int xpEarned, int totalXp){
        this.xpEarned = xpEarned;
        this.totalXp = totalXp;
    }

    public int getXpEarned() {
        return xpEarned;
    }

    public int getTotalXp() {
        return totalXp;
    }
}
