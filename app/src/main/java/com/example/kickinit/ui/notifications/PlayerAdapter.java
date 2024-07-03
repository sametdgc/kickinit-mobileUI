package com.example.kickinit.ui.notifications;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kickinit.R;
import com.example.kickinit.models.Player;

import java.util.List;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder> {

    private List<Player> playerList;

    public PlayerAdapter(List<Player> playerList) {
        this.playerList = playerList;
    }

    @NonNull
    @Override
    public PlayerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_player, parent, false);
        return new PlayerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerViewHolder holder, int position) {
        Player player = playerList.get(position);
        holder.playerName.setText(player.getFirstName() + " " + player.getLastName());
        holder.playerAge.setText(String.valueOf(player.getAge()));
        holder.playerPositions.setText(player.getPreferredPositions().toString());
    }

    @Override
    public int getItemCount() {
        return playerList.size();
    }

    public void setPlayers(List<Player> players) {
        this.playerList = players;
        notifyDataSetChanged();
    }

    public static class PlayerViewHolder extends RecyclerView.ViewHolder {
        TextView playerName, playerAge, playerPositions;

        public PlayerViewHolder(@NonNull View itemView) {
            super(itemView);
            playerName = itemView.findViewById(R.id.player_name);
            playerAge = itemView.findViewById(R.id.player_age);
            playerPositions = itemView.findViewById(R.id.player_positions);
        }
    }
}
