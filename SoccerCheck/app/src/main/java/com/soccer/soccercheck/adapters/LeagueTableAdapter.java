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
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.caverock.androidsvg.SVG;
import com.soccer.soccercheck.R;
import com.soccer.soccercheck.listeners.OnItemClickListener;
import com.soccer.soccercheck.listeners.OnTeamSelectedListener;
import com.soccer.soccercheck.model.LeagueTable;
import com.soccer.soccercheck.model.Standing;
import com.soccer.soccercheck.util.ExtensionLinkGetter;
import com.soccer.soccercheck.viewHolders.LeagueTableViewHolder;

import java.io.InputStream;
import java.util.List;

/**
 * Created by ederson.js on 01/11/2016.
 */

public class LeagueTableAdapter extends RecyclerView.Adapter<LeagueTableViewHolder> {
    private static final String TAG = "LeagueTableAdapter";

    private List<Standing> leagueTable;
    private Context mContext;
    private GenericRequestBuilder<Uri, InputStream, SVG, PictureDrawable> requestBuilder;

    public LeagueTableAdapter(LeagueTable leagueTable, Context context, GenericRequestBuilder requestBuilder) {
        this.leagueTable = leagueTable.getStanding();
        this.mContext = context;
        this.requestBuilder = requestBuilder;
    }

    @Override
    public LeagueTableViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_league_table_content, viewGroup, false);
        LeagueTableViewHolder mLeagueTableViewHolder = new LeagueTableViewHolder(view);
        view.setTag(mLeagueTableViewHolder);

        return mLeagueTableViewHolder;
    }

    @Override
    public void onBindViewHolder(LeagueTableViewHolder leagueTableViewHolder, int position) {
        Log.i(TAG, "onBindViewHolder() inside method " + position);

        // Gets the position of the item on the List and add the object information
        final Standing standing = this.leagueTable.get(position);
        String extension = ExtensionLinkGetter.getExtensionFromLink(standing.getCrestURI());

        Log.i(TAG, "inside method | uri: " + standing.getCrestURI());

        Uri uri = Uri.parse(standing.getCrestURI());

        if((extension.equals("gif")) || (extension.equals("png")) || (extension.equals("jpg"))) {
            Log.i(TAG, "inside if - " + extension);

            Glide.with(mContext)
                    .load(uri)
                    .override(30, 30)
                    .into(leagueTableViewHolder.getImageViewTeamLogo());

        } else {

            requestBuilder.diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .load(uri)
                    .override(30, 30)
                    .into(leagueTableViewHolder.getImageViewTeamLogo());

        }

        leagueTableViewHolder.getPositionTeam().setText(standing.getPosition().toString());
        leagueTableViewHolder.getTeamName().setText(standing.getTeamName());
        leagueTableViewHolder.getPlayedGames().setText(standing.getPlayedGames().toString());

        // Listener to the card view item to show a detail when is clicked
        leagueTableViewHolder.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ((OnTeamSelectedListener)mContext).onTeamSelected(standing, position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return (leagueTable != null) ? leagueTable.size() : 0;
    }


}
