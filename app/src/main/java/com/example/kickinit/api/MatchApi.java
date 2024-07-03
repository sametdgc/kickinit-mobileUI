package com.example.kickinit.api;

import com.example.kickinit.models.Match;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MatchApi {
    @GET("api/matches/all") // Ensure this endpoint matches your API's endpoint
    Call<List<Match>> getAllMatches();
}
