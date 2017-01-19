package com.soccer.soccercheck.adapters;

import android.content.Context;
import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.GenericRequestBuilder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.caverock.androidsvg.SVG;
import com.soccer.soccercheck.R;
import com.soccer.soccercheck.model.ChampionsLeagueTable;
import com.soccer.soccercheck.model.StandingChampionsLeague;
import com.soccer.soccercheck.model.Standings;
import com.soccer.soccercheck.viewHolders.ChampionsLeagueTableViewHolder;

import java.io.InputStream;

/**
 * Created by ederson.js on 01/11/2016.
 */

public class ChampionsLeagueTableAdapter extends RecyclerView.Adapter<ChampionsLeagueTableViewHolder> {
    private static final String TAG = "ChampionsLeagueTable";

    private Standings leagueTable;
    private Context mContext;
    private GenericRequestBuilder<Uri, InputStream, SVG, PictureDrawable> requestBuilder;

    public ChampionsLeagueTableAdapter(ChampionsLeagueTable championsLeagueTable, Context context, GenericRequestBuilder requestBuilder) {
        this.leagueTable = championsLeagueTable.getStandings();
        this.mContext = context;
        this.requestBuilder = requestBuilder;
    }

    @Override
    public ChampionsLeagueTableViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.champions_league_table_content, viewGroup, false);
        ChampionsLeagueTableViewHolder mChampionsLeagueTableViewHolder = new ChampionsLeagueTableViewHolder(view);
        view.setTag(mChampionsLeagueTableViewHolder);

        return mChampionsLeagueTableViewHolder;
    }

    @Override
    public void onBindViewHolder(ChampionsLeagueTableViewHolder leagueTableViewHolder, int position) {
        Log.i(TAG, "onBindViewHolder() inside method " + position);

        // Gets the position of the item on the List and add the object information
        final StandingChampionsLeague standingChampionsLeague = this.leagueTable.getmStandingChampionsLeagueGroupA().get(position);

        Log.i(TAG, "inside method | uri: " + standingChampionsLeague.getCrestURI());

        Uri uri = Uri.parse(standingChampionsLeague.getCrestURI());

        requestBuilder.diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .load(uri)
                .override(30, 30)
                .into(leagueTableViewHolder.getImageViewTeamLogoCl());

        leagueTableViewHolder.getGroup().setText(standingChampionsLeague.getGroup());
        leagueTableViewHolder.getRank().setText(standingChampionsLeague.getRank().toString());
        leagueTableViewHolder.getTeam().setText(standingChampionsLeague.getTeam());
        leagueTableViewHolder.getPlayedGames().setText(standingChampionsLeague.getPlayedGames().toString());
        leagueTableViewHolder.getPoints().setText(standingChampionsLeague.getPoints().toString());

        // Listener to the card view item to show a detail when is clicked
//        leagueTableViewHolder.setOnItemClickListener(new OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                ((OnTeamSelectedListener)mContext).onTeamSelected(standing, position);
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return (leagueTable != null) ? leagueTable.getmStandingChampionsLeagueGroupA().size() : 0;
    }


}
