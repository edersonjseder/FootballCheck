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

public class LeagueTableViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    /** Fields View **/
    private ImageView imageViewTeamLogo;
    private TextView positionTeam;
    private TextView teamName;
    private TextView playedGames;
    private TextView points;
    private TextView goals;
    private TextView goalsAgainst;
    private TextView goalDifference;
    private TextView wins;
    private TextView draws;
    private TextView losses;
    private OnItemClickListener onItemClickListener;

    public LeagueTableViewHolder(View itemView) {
        super(itemView);

        imageViewTeamLogo = (ImageView) itemView.findViewById(R.id.imageView_team);
        positionTeam = (TextView) itemView.findViewById(R.id.textview_position);
        teamName = (TextView) itemView.findViewById(R.id.textview_team_name);
        playedGames = (TextView) itemView.findViewById(R.id.textview_played_games);

        itemView.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        onItemClickListener.onItemClick(v, getLayoutPosition());
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public ImageView getImageViewTeamLogo() {
        return imageViewTeamLogo;
    }

    public TextView getPositionTeam() {
        return positionTeam;
    }

    public TextView getTeamName() {
        return teamName;
    }

    public TextView getPlayedGames() {
        return playedGames;
    }
}
