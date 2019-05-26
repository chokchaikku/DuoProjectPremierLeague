package com.pantip.dev.duoproject;

public class DataFire {
    private String goalscore,name,team;
    public DataFire(){}

    public DataFire(String goalscore, String name, String team) {
        this.goalscore = goalscore;
        this.name = name;
        this.team = team;
    }

    public String getGoalscore() {
        return goalscore;
    }

    public void setGoalscore(String goalscore) {
        this.goalscore = goalscore;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }
}
