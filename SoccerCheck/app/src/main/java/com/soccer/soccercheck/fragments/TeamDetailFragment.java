package com.soccer.soccercheck.fragments;

import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.GenericRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.StreamEncoder;
import com.bumptech.glide.load.resource.file.FileToStreamDecoder;
import com.caverock.androidsvg.SVG;
import com.soccer.soccercheck.R;
import com.soccer.soccercheck.model.Standing;
import com.soccer.soccercheck.model.Team;
import com.soccer.soccercheck.services.TeamService;
import com.soccer.soccercheck.util.SvgDecoder;
import com.soccer.soccercheck.util.SvgDrawableTranscoder;
import com.soccer.soccercheck.util.SvgSoftwareLayerSetter;

import java.io.InputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by root on 16/01/17.
 */

public class TeamDetailFragment extends Fragment {
    private static final String TAG = "TeamDetailFragment";

    public static final String STANDING = "standing";

    private ImageView imageTeamLogo;
    private TextView textViewNameTeam;
    private TextView textViewShortNameTeam;
    private TextView textViewCodeTeam;
    private TextView textViewSquadMarketValueTeam;

    private TextView textViewNameTeamLabel;
    private TextView textViewShortNameTeamLabel;
    private TextView textViewCodeTeamLabel;
    private TextView textViewSquadMarketValueTeamLabel;

    private Call<Team> mCallTeam;

    private GenericRequestBuilder<Uri, InputStream, SVG, PictureDrawable> requestBuilder;

    public static TeamDetailFragment newInstance(Standing standing) {
        Log.i(TAG, "newInstance() inside method");

        TeamDetailFragment fragment = new TeamDetailFragment();

        Bundle args = new Bundle();
        args.putSerializable(STANDING, standing);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate() inside method");

        Bundle args = getArguments();
        Standing mStanding = (Standing) args.getSerializable(STANDING);

        Log.i(TAG, "Standing Value Link: " + mStanding.getLinks().getTeam().getHref());

        int idTeam = getIdFromLink(mStanding);

        if (savedInstanceState != null)
            return;

        if (savedInstanceState == null) {
            getTeamInfo(idTeam);

        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_team_detail_soccer, container, false);

        imageTeamLogo = (ImageView) v.findViewById(R.id.id_image_team_logo);

        textViewNameTeam = (TextView) v.findViewById(R.id.textview_team_name);
        textViewShortNameTeam = (TextView) v.findViewById(R.id.textview_short_name_team);
        textViewCodeTeam = (TextView) v.findViewById(R.id.textview_code_team);
        textViewSquadMarketValueTeam = (TextView) v.findViewById(R.id.textview_squad_market_value_team);

        textViewNameTeamLabel = (TextView) v.findViewById(R.id.textview_team_name_label);
        textViewShortNameTeamLabel = (TextView) v.findViewById(R.id.textview_short_name_team_label);
        textViewCodeTeamLabel = (TextView) v.findViewById(R.id.textview_code_team_label);
        textViewSquadMarketValueTeamLabel = (TextView) v.findViewById(R.id.textview_squad_market_value_team_label);

        requestBuilder = Glide.with(getActivity())
                .using(Glide.buildStreamModelLoader(Uri.class, getActivity()), InputStream.class)
                .from(Uri.class)
                .as(SVG.class)
                .transcode(new SvgDrawableTranscoder(), PictureDrawable.class)
                .sourceEncoder(new StreamEncoder())
                .cacheDecoder(new FileToStreamDecoder<SVG>(new SvgDecoder()))
                .decoder(new SvgDecoder())
                .listener(new SvgSoftwareLayerSetter<Uri>());

        return v;
    }

    public void getTeamInfo(Integer id) {

        mCallTeam = TeamService.Factory.create().getTeam(id);

        mCallTeam.enqueue(new Callback<Team>() {
            @Override
            public void onResponse(Call<Team> call, Response<Team> response) {

                Team mTeam = response.body();

                if(mTeam != null) {

                    showTeamInfo(mTeam);

                }

            }

            @Override
            public void onFailure(Call<Team> call, Throwable t) {
                Log.i(TAG, "onFailure() inside method");
                t.printStackTrace();
            }
        });

    }

    private void showTeamInfo(Team mTeam) {
        Log.i(TAG, "showTeamInfo() inside method");

        if (mTeam != null) {

            loadNet(mTeam.getCrestUrl());

            textViewNameTeamLabel.setVisibility(View.VISIBLE);
            textViewShortNameTeamLabel.setVisibility(View.VISIBLE);
            textViewCodeTeamLabel.setVisibility(View.VISIBLE);
            textViewSquadMarketValueTeamLabel.setVisibility(View.VISIBLE);

            textViewNameTeam.setText(mTeam.getName());
            textViewShortNameTeam.setText(mTeam.getShortName());
            textViewCodeTeam.setText(mTeam.getCode());
            textViewSquadMarketValueTeam.setText(mTeam.getSquadMarketValue());

        }

    }

    private void loadNet(String url) {

        Uri uri = Uri.parse(url);

        requestBuilder.diskCacheStrategy(DiskCacheStrategy.SOURCE)
                // SVG cannot be serialized so it's not worth to cache it
                .load(uri)
                .into(imageTeamLogo);
    }

    public int getIdFromLink(Standing standing) {
        Log.i(TAG, "getIdFromLink() inside method");

        int idTeam = 0;
        String[] link = standing.getLinks().getTeam().getHref().split("/");

        for (int i = 0; i < link.length; i++){
            Log.i(TAG, "inside for: " + link[i]);

            try {

                idTeam = Integer.parseInt(link[i]);

            } catch (NumberFormatException e) {
                continue;
            }

        }

        return idTeam;
    }
}
