package com.example.kickinit.models;

import java.util.Date;

public class Match {
    private String id;
    private Date date;
    private String homeTeamName;
    private String awayTeamName;
    private int homeTeamScore;
    private int awayTeamScore;

    // Getters
    public String getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public String getHomeTeamName() {
        return homeTeamName;
    }

    public String getAwayTeamName() {
        return awayTeamName;
    }

    public int getHomeTeamScore() {
        return homeTeamScore;
    }

    public int getAwayTeamScore() {
        return awayTeamScore;
    }
}
