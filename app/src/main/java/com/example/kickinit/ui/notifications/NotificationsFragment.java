package com.example.kickinit.ui.notifications;

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
import com.example.kickinit.databinding.FragmentNotificationsBinding;
import com.example.kickinit.models.Player;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;
    private FragmentNotificationsBinding binding;
    private PlayerAdapter playerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        notificationsViewModel = new ViewModelProvider(this).get(NotificationsViewModel.class);
        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        RecyclerView recyclerView = binding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        playerAdapter = new PlayerAdapter(new ArrayList<>()); // Pass an empty list initially
        recyclerView.setAdapter(playerAdapter);

        fetchPlayers();

        return root;
    }

    private void fetchPlayers() {
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<List<Player>> call = apiService.getAllPlayers();
        call.enqueue(new Callback<List<Player>>() {
            @Override
            public void onResponse(Call<List<Player>> call, Response<List<Player>> response) {
                if (response.isSuccessful()) {
                    playerAdapter.setPlayers(response.body());
                } else {
                    Toast.makeText(getContext(), "Failed to retrieve players", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Player>> call, Throwable t) {
                Toast.makeText(getContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
