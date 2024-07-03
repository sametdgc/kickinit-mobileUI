package com.example.kickinit.api;

import com.example.kickinit.models.Team;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TeamApi {
    @GET("/api/teams/all")
    Call<List<Team>> getAllTeams();
}
