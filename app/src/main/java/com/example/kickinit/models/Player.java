package com.example.kickinit.models;

import java.util.List;

public class Player {
    private String id;
    private String firstName;
    private String lastName;
    private int age;
    private List<String> preferredPositions;

    // Getters
    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public List<String> getPreferredPositions() {
        return preferredPositions;
    }
}
