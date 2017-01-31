package com.soccer.soccercheck.viewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.soccer.soccercheck.R;
import com.soccer.soccercheck.listeners.OnItemClickListener;

/**
 * Created by root on 01/11/16.
 */

public class FixturesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private TextView textViewFixtureDate;
    private TextView textViewFixtureStatus;
    private TextView textViewAwayTeamName;
    private TextView textViewScoreAwayTeam;
    private TextView textViewHomeTeamName;
    private TextView textViewScoreHomeTeam;
    private ImageView imageViewAwayTeam;
    private ImageView imageViewHomeTeam;
    private OnItemClickListener onItemClickListener;

    public FixturesViewHolder(View itemView) {
        super(itemView);

        textViewFixtureDate = (TextView) itemView.findViewById(R.id.textview_fixture_date);
        textViewFixtureStatus = (TextView) itemView.findViewById(R.id.textview_fixture_status);
        imageViewAwayTeam = (ImageView) itemView.findViewById(R.id.imageView_away_team);
        imageViewHomeTeam = (ImageView) itemView.findViewById(R.id.imageView_home_team);
        textViewAwayTeamName = (TextView) itemView.findViewById(R.id.textview_away_team_name);
        textViewScoreAwayTeam = (TextView) itemView.findViewById(R.id.textview_score_away_team);
        textViewHomeTeamName = (TextView) itemView.findViewById(R.id.textview_home_team_name);
        textViewScoreHomeTeam = (TextView) itemView.findViewById(R.id.textview_score_home_team);

        itemView.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        onItemClickListener.onItemClick(v, getLayoutPosition());
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public TextView getTextViewFixtureDate() {
        return textViewFixtureDate;
    }

    public TextView getTextViewFixtureStatus() {
        return textViewFixtureStatus;
    }

    public TextView getTextViewAwayTeamName() {
        return textViewAwayTeamName;
    }

    public TextView getTextViewScoreAwayTeam() {
        return textViewScoreAwayTeam;
    }

    public TextView getTextViewScoreHomeTeam() {
        return textViewScoreHomeTeam;
    }

    public TextView getTextViewHomeTeamName() {
        return textViewHomeTeamName;
    }

    public ImageView getImageViewAwayTeam() {
        return imageViewAwayTeam;
    }

    public ImageView getImageViewHomeTeam() {
        return imageViewHomeTeam;
    }

}
