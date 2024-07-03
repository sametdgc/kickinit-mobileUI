package com.example.kickinit.models;

import java.util.List;

public class Team {
    private String name;
    private List<String> playerIds;

    public Team(String name, List<String> playerIds) {
        this.name = name;
        this.playerIds = playerIds;
    }

    private String id;

    // Getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<String> getPlayerIds() {
        return playerIds;
    }
}
