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

public class ChampionsLeagueTableViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    /** Fields View **/
    private ImageView imageViewTeamLogoCl;
    private TextView group;
    private TextView rank;
    private TextView team;
    private TextView teamId;
    private TextView playedGames;
    private TextView crestURI;
    private TextView points;
    private TextView goals;
    private TextView goalsAgainst;
    private TextView goalDifference;

    private OnItemClickListener onItemClickListener;

    public ChampionsLeagueTableViewHolder(View itemView) {
        super(itemView);

        imageViewTeamLogoCl = (ImageView) itemView.findViewById(R.id.imageView_team_logo_cl);
        group = (TextView) itemView.findViewById(R.id.textview_group);
        rank = (TextView) itemView.findViewById(R.id.textview_rank);
        team = (TextView) itemView.findViewById(R.id.textview_team);
        playedGames = (TextView) itemView.findViewById(R.id.textview_played_games_cl);
        points = (TextView) itemView.findViewById(R.id.textview_points);

        itemView.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        onItemClickListener.onItemClick(v, getLayoutPosition());
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public ImageView getImageViewTeamLogoCl() {
        return imageViewTeamLogoCl;
    }

    public TextView getGroup() {
        return group;
    }

    public TextView getRank() {
        return rank;
    }

    public TextView getTeam() {
        return team;
    }

    public TextView getPlayedGames() {
        return playedGames;
    }

    public TextView getPoints() {
        return points;
    }
}
