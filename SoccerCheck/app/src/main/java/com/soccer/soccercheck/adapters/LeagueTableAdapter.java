package com.soccer.soccercheck.adapters;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.soccer.soccercheck.R;
import com.soccer.soccercheck.model.Competition;
import com.soccer.soccercheck.model.LeagueTable;
import com.soccer.soccercheck.model.Standing;
import com.soccer.soccercheck.viewHolders.FixturesViewHolder;
import com.soccer.soccercheck.viewHolders.LeagueTableViewHolder;
import com.squareup.picasso.Picasso;

import org.joda.time.DateTime;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by ederson.js on 01/11/2016.
 */

public class LeagueTableAdapter extends RecyclerView.Adapter<LeagueTableViewHolder> {
    private static final String TAG = "LeagueTableAdapter";

    private List<Standing> leagueTable;
    private Context context;
    private DateTime time;
    private SimpleDateFormat simpleDateFormat;
    private View.OnClickListener onClickListener;

    public LeagueTableAdapter(LeagueTable leagueTable, Context context) {
        this.leagueTable = leagueTable.getStanding();
        this.context = context;
        simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
    }

    @Override
    public LeagueTableViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.component_card_view_league_table, viewGroup, false);
        LeagueTableViewHolder mLeagueTableViewHolder = new LeagueTableViewHolder(view);
        view.setTag(mLeagueTableViewHolder);

        return mLeagueTableViewHolder;
    }

    @Override
    public void onBindViewHolder(LeagueTableViewHolder leagueTableViewHolder, int position) {
        Log.i(TAG, "onBindViewHolder() inside method " + position);
        Context context = leagueTableViewHolder.getImageViewTeamLogo().getContext();

        // Gets the position of the item on the List and add the object information
        final Standing standing = this.leagueTable.get(position);

        Log.i(TAG, "inside method | uri: " + standing.getCrestURI());


        Picasso.with(context).load(standing.getCrestURI()).fit().centerInside().into(leagueTableViewHolder.getImageViewTeamLogo());

        leagueTableViewHolder.getPositionTeam().setText(standing.getPosition().toString());
        leagueTableViewHolder.getTeamName().setText(standing.getTeamName());
        leagueTableViewHolder.getPlayedGames().setText(standing.getPlayedGames().toString());

    }

    @Override
    public int getItemCount() {
        return (leagueTable != null) ? leagueTable.size() : 0;
    }


}
