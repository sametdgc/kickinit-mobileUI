// TeamAdapter.java
package com.example.kickinit.ui.dashboard;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kickinit.R;
import com.example.kickinit.models.Team;

import java.util.List;

public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.TeamViewHolder> {

    private List<Team> teamList;

    public TeamAdapter(List<Team> teamList) {
        this.teamList = teamList;
    }

    @NonNull
    @Override
    public TeamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_team, parent, false);
        return new TeamViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TeamViewHolder holder, int position) {
        Team team = teamList.get(position);
        holder.teamName.setText(team.getName());
        holder.teamPlayers.setText(team.getPlayerIds().toString());
    }

    @Override
    public int getItemCount() {
        return teamList.size();
    }

    public void setTeams(List<Team> teams) {
        this.teamList = teams;
        notifyDataSetChanged();
    }

    public static class TeamViewHolder extends RecyclerView.ViewHolder {
        TextView teamName, teamPlayers;

        public TeamViewHolder(@NonNull View itemView) {
            super(itemView);
            teamName = itemView.findViewById(R.id.team_name);
            teamPlayers = itemView.findViewById(R.id.team_players);
        }
    }
}
