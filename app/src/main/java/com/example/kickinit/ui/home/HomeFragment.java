package com.example.kickinit.ui.home;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.kickinit.R;
import com.example.kickinit.api.ApiClient;
import com.example.kickinit.api.ApiService;
import com.example.kickinit.models.Match;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private RecyclerView recyclerView;
    private MatchAdapter matchAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = root.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        matchAdapter = new MatchAdapter(null);
        recyclerView.setAdapter(matchAdapter);

        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        fetchMatches();

        return root;
    }

    private void fetchMatches() {
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<List<Match>> call = apiService.getAllMatches(); // Assuming you have this API endpoint
        call.enqueue(new Callback<List<Match>>() {
            @Override
            public void onResponse(Call<List<Match>> call, Response<List<Match>> response) {
                if (response.isSuccessful()) {
                    matchAdapter.setMatches(response.body());
                } else {
                    Toast.makeText(getContext(), "Failed to retrieve matches", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Match>> call, Throwable t) {
                Toast.makeText(getContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
