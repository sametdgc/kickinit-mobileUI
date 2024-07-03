package com.example.kickinit.api;

import com.example.kickinit.models.Team;
import com.example.kickinit.models.Match;
import com.example.kickinit.models.Player;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @GET("api/teams/all")
    Call<List<Team>> getAllTeams();

    @GET("/api/matches/all")
    Call<List<Match>> getAllMatches();
    @GET("api/players/all")
    Call<List<Player>> getAllPlayers();

    @POST("api/teams/save")
    Call<Team> createTeam(@Body Team team);
}
