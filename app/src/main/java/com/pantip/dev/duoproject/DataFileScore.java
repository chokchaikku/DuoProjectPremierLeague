package com.pantip.dev.duoproject;

public class DataFileScore {
    private String rank,club,fight,win,ever,lose,total;

    public DataFileScore(){}

    public DataFileScore(String rank, String club, String fight, String win, String ever, String lose, String total) {


        this.rank = rank;
        this.club = club;
        this.fight = fight;
        this.win = win;
        this.ever = ever;
        this.lose = lose;
        this.total = total;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = club;
    }

    public String getFight() {
        return fight;
    }

    public void setFight(String fight) {
        this.fight = fight;
    }
    public String getWin() {
        return win;
    }

    public void setWin(String win) {
        this.win = win;
    }
    public String getEver() {
        return ever;
    }

    public void setEver(String ever) {
        this.ever = ever;
    }
    public String getLose() {
        return lose;
    }

    public void setLose(String lose) {
        this.lose = lose;
    }
    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }


}
