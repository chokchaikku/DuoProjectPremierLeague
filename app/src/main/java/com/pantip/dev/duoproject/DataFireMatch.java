package com.pantip.dev.duoproject;

public class DataFireMatch {
    private String time,vs;
    public DataFireMatch(){}

    public DataFireMatch(String time, String vs) {
        this.time = time;
        this.vs = vs;

    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getVs() {
        return vs;
    }

    public void setVs(String vs) {
        this.vs = vs;
    }

}

