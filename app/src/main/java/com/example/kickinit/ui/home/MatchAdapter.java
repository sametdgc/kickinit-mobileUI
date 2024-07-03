package com.example.kickinit.ui.home;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kickinit.models.Match;
import com.example.kickinit.R;

import java.util.List;

public class MatchAdapter extends RecyclerView.Adapter<MatchAdapter.MatchViewHolder> {

    private List<Match> matchList;

    public MatchAdapter(List<Match> matchList) {
        this.matchList = matchList;
    }

    public void setMatches(List<Match> matchList) {
        this.matchList = matchList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MatchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_match, parent, false);
        return new MatchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MatchViewHolder holder, int position) {
        Match match = matchList.get(position);
        holder.matchDate.setText(match.getDate().toString());
        holder.homeTeamName.setText(match.getHomeTeamName());
        holder.awayTeamName.setText(match.getAwayTeamName());
        holder.matchScore.setText(match.getHomeTeamScore() + " - " + match.getAwayTeamScore());
    }

    @Override
    public int getItemCount() {
        return matchList != null ? matchList.size() : 0;
    }

    public static class MatchViewHolder extends RecyclerView.ViewHolder {
        TextView matchDate, homeTeamName, awayTeamName, matchScore;

        public MatchViewHolder(@NonNull View itemView) {
            super(itemView);
            matchDate = itemView.findViewById(R.id.match_date);
            homeTeamName = itemView.findViewById(R.id.match_team1);
            awayTeamName = itemView.findViewById(R.id.match_team2);
            matchScore = itemView.findViewById(R.id.match_score);
        }
    }
}
