package com.example.kickinit.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kickinit.R;
import com.example.kickinit.api.ApiClient;
import com.example.kickinit.api.ApiService;
import com.example.kickinit.models.Team;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    private TeamAdapter teamAdapter;
    private LinearLayout createTeamForm;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        RecyclerView recyclerView = root.findViewById(R.id.recycler_view_teams);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        teamAdapter = new TeamAdapter(new ArrayList<>());
        recyclerView.setAdapter(teamAdapter);

        createTeamForm = root.findViewById(R.id.create_team_form);
        createTeamForm.setVisibility(View.GONE);

        Button addTeamButton = root.findViewById(R.id.add_team_button);
        addTeamButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createTeamForm.setVisibility(View.VISIBLE);
            }
        });

        Button createTeamButton = root.findViewById(R.id.create_team_button);
        EditText teamNameEditText = root.findViewById(R.id.team_name);
        EditText playerIdsEditText = root.findViewById(R.id.player_ids);

        createTeamButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String teamName = teamNameEditText.getText().toString();
                String playerIdsString = playerIdsEditText.getText().toString();
                List<String> playerIds = Arrays.asList(playerIdsString.split(","));
                createTeam(new Team(teamName, playerIds));
            }
        });

        fetchTeams();

        return root;
    }

    private void fetchTeams() {
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<List<Team>> call = apiService.getAllTeams();
        call.enqueue(new Callback<List<Team>>() {
            @Override
            public void onResponse(Call<List<Team>> call, Response<List<Team>> response) {
                if (response.isSuccessful()) {
                    teamAdapter.setTeams(response.body());
                } else {
                    Toast.makeText(getContext(), "Failed to retrieve teams", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Team>> call, Throwable t) {
                Toast.makeText(getContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void createTeam(Team team) {
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<Team> call = apiService.createTeam(team);
        call.enqueue(new Callback<Team>() {
            @Override
            public void onResponse(Call<Team> call, Response<Team> response) {
                if (response.isSuccessful()) {
                    fetchTeams();
                    createTeamForm.setVisibility(View.GONE);
                } else {
                    Toast.makeText(getContext(), "Failed to create team", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Team> call, Throwable t) {
                Toast.makeText(getContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
