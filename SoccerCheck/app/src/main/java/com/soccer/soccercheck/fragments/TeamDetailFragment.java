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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.GenericRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.StreamEncoder;
import com.bumptech.glide.load.resource.file.FileToStreamDecoder;
import com.caverock.androidsvg.SVG;
import com.soccer.soccercheck.R;
import com.soccer.soccercheck.listeners.OnShowPlayerListListener;
import com.soccer.soccercheck.model.Standing;
import com.soccer.soccercheck.model.Team;
import com.soccer.soccercheck.services.TeamService;
import com.soccer.soccercheck.util.ExtensionLinkGetter;
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

    private LinearLayout groupButtons;

    private Button btnPlayers;
    private Button btnFixtures;

    private Call<Team> mCallTeam;

    private Team mTeam;

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

        int idTeam = getIdFromLink(mStanding.getLinks().getTeam().getHref());

        if (savedInstanceState != null)
            return;

        if (savedInstanceState == null) {
            getTeamInfo(idTeam);

        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView() inside method");

        View view = inflater.inflate(R.layout.fragment_team_detail_soccer, container, false);

        imageTeamLogo = (ImageView) view.findViewById(R.id.id_image_team_logo);

        textViewNameTeam = (TextView) view.findViewById(R.id.textview_team_name);
        textViewShortNameTeam = (TextView) view.findViewById(R.id.textview_short_name_team);
        textViewCodeTeam = (TextView) view.findViewById(R.id.textview_code_team);
        textViewSquadMarketValueTeam = (TextView) view.findViewById(R.id.textview_squad_market_value_team);

        textViewNameTeamLabel = (TextView) view.findViewById(R.id.textview_team_name_label);
        textViewShortNameTeamLabel = (TextView) view.findViewById(R.id.textview_short_name_team_label);
        textViewCodeTeamLabel = (TextView) view.findViewById(R.id.textview_code_team_label);
        textViewSquadMarketValueTeamLabel = (TextView) view.findViewById(R.id.textview_squad_market_value_team_label);

        groupButtons = (LinearLayout) view.findViewById(R.id.group_buttons);

        btnPlayers = (Button) view.findViewById(R.id.btn_team_players);
        btnFixtures = (Button) view.findViewById(R.id.btn_team_fixtures);

        btnPlayers.setOnClickListener(onClickShowPlayersListener);
        btnFixtures.setOnClickListener(onClickShowFixturesListener);

        requestBuilder = Glide.with(getActivity())
                .using(Glide.buildStreamModelLoader(Uri.class, getActivity()), InputStream.class)
                .from(Uri.class)
                .as(SVG.class)
                .transcode(new SvgDrawableTranscoder(), PictureDrawable.class)
                .sourceEncoder(new StreamEncoder())
                .cacheDecoder(new FileToStreamDecoder<SVG>(new SvgDecoder()))
                .decoder(new SvgDecoder())
                .listener(new SvgSoftwareLayerSetter<Uri>());

        return view;
    }

    public void getTeamInfo(Integer id) {

        mCallTeam = TeamService.Factory.create().getTeam(id);

        mCallTeam.enqueue(new Callback<Team>() {
            @Override
            public void onResponse(Call<Team> call, Response<Team> response) {

                setmTeam(response.body());

                if(getmTeam() != null) {

                    showTeamInfo(getmTeam());

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

            textViewNameTeam.setText(mTeam.getName());
            textViewShortNameTeam.setText(mTeam.getShortName());
            textViewCodeTeam.setText(mTeam.getCode());
            textViewSquadMarketValueTeam.setText(mTeam.getSquadMarketValue());

            textViewNameTeamLabel.setVisibility(View.VISIBLE);
            textViewShortNameTeamLabel.setVisibility(View.VISIBLE);
            textViewCodeTeamLabel.setVisibility(View.VISIBLE);
            textViewSquadMarketValueTeamLabel.setVisibility(View.VISIBLE);

            groupButtons.setVisibility(View.VISIBLE);

        }

    }

    private View.OnClickListener onClickShowPlayersListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            Log.i(TAG, "onClick() inside method");

            Log.i(TAG, "URL " + getmTeam().getLinks().getSelf().getHref());

            int idTeam = getIdFromLink(getmTeam().getLinks().getSelf().getHref());

            Log.i(TAG, "ID " + idTeam);

            ((OnShowPlayerListListener)getContext()).showPlayerList(idTeam);

        }

    };

    private View.OnClickListener onClickShowFixturesListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            Toast.makeText(getContext(), "OnShowFixturesTeam", Toast.LENGTH_SHORT).show();

        }

    };

    private void loadNet(String url) {

        Uri uri = Uri.parse(url);

        String extension = ExtensionLinkGetter.getExtensionFromLink(url);

        if((extension.equals("gif")) || (extension.equals("png")) || (extension.equals("jpg"))) {
            Log.i(TAG, "inside if - " + extension);

            Glide.with(getActivity())
                    .load(uri)
                    .override(125, 125)
                    .into(imageTeamLogo);

        } else {

            requestBuilder.diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    // SVG cannot be serialized so it's not worth to cache it
                    .load(uri)
                    .override(125, 125)
                    .into(imageTeamLogo);

        }

    }

    public int getIdFromLink(String url) {
        Log.i(TAG, "getIdFromLink() inside method");

        int idTeam = 0;
        String[] link = url.split("\\/");

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

    public Team getmTeam() {
        return mTeam;
    }

    public void setmTeam(Team mTeam) {
        this.mTeam = mTeam;
    }
}
