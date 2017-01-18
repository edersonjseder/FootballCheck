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

    public void setTextViewFixtureDate(TextView textViewFixtureDate) {
        this.textViewFixtureDate = textViewFixtureDate;
    }

    public TextView getTextViewFixtureStatus() {
        return textViewFixtureStatus;
    }

    public void setTextViewFixtureStatus(TextView textViewFixtureStatus) {
        this.textViewFixtureStatus = textViewFixtureStatus;
    }

    public TextView getTextViewAwayTeamName() {
        return textViewAwayTeamName;
    }

    public TextView getTextViewScoreAwayTeam() {
        return textViewScoreAwayTeam;
    }

    public void setTextViewScoreAwayTeam(TextView textViewScoreAwayTeam) {
        this.textViewScoreAwayTeam = textViewScoreAwayTeam;
    }

    public TextView getTextViewScoreHomeTeam() {
        return textViewScoreHomeTeam;
    }

    public void setTextViewScoreHomeTeam(TextView textViewScoreHomeTeam) {
        this.textViewScoreHomeTeam = textViewScoreHomeTeam;
    }

    public void setTextViewAwayTeamName(TextView textViewAwayTeamName) {
        this.textViewAwayTeamName = textViewAwayTeamName;
    }

    public TextView getTextViewHomeTeamName() {
        return textViewHomeTeamName;
    }

    public void setTextViewHomeTeamName(TextView textViewHomeTeamName) {
        this.textViewHomeTeamName = textViewHomeTeamName;
    }

    public ImageView getImageViewAwayTeam() {
        return imageViewAwayTeam;
    }

    public void setImageViewAwayTeam(ImageView imageViewAwayTeam) {
        this.imageViewAwayTeam = imageViewAwayTeam;
    }

    public ImageView getImageViewHomeTeam() {
        return imageViewHomeTeam;
    }

    public void setImageViewHomeTeam(ImageView imageViewHomeTeam) {
        this.imageViewHomeTeam = imageViewHomeTeam;
    }
}
