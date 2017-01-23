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
import com.soccer.soccercheck.model.Players;
import com.soccer.soccercheck.model.Team;
import com.soccer.soccercheck.util.ExtensionLinkGetter;
import com.soccer.soccercheck.util.SvgDecoder;
import com.soccer.soccercheck.util.SvgDrawableTranscoder;
import com.soccer.soccercheck.util.SvgSoftwareLayerSetter;

import java.io.InputStream;

/**
 * Created by root on 16/01/17.
 */

public class PlayerDetailFragment extends Fragment {
    private static final String TAG = "PlayerDetailFragment";

    public static final String PLAYER = "player";

    private TextView textViewPlayerName;
    private TextView textViewPlayerPosition;
    private TextView textViewPlayerNumber;
    private TextView textViewPlayerDateBirth;
    private TextView textViewPlayerNationality;
    private TextView textViewPlayerEndContract;
    private TextView textViewPlayerMarketValue;

    private TextView textViewPlayerNameLabel;
    private TextView textViewPlayerPositionLabel;
    private TextView textViewPlayerNumberLabel;
    private TextView textViewPlayerDateBirthLabel;
    private TextView textViewPlayerNationalityLabel;
    private TextView textViewPlayerEndContractLabel;
    private TextView textViewPlayerMarketValueLabel;

    private Players mPlayers;


    public static PlayerDetailFragment newInstance(Players players) {
        Log.i(TAG, "newInstance() inside method");

        PlayerDetailFragment fragment = new PlayerDetailFragment();

        Bundle args = new Bundle();
        args.putSerializable(PLAYER, players);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate() inside method");

        Bundle args = getArguments();
        setmPlayers((Players) args.getSerializable(PLAYER));

        Log.i(TAG, "Player value " + getmPlayers().getName());

        if (savedInstanceState != null)
            return;

        getPlayerInfo();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView() inside method");

        View view = inflater.inflate(R.layout.fragment_player_detail_soccer, container, false);

        textViewPlayerName = (TextView) view.findViewById(R.id.textview_player_detail_name);
        textViewPlayerPosition = (TextView) view.findViewById(R.id.textview_player_detail_position);
        textViewPlayerNumber = (TextView) view.findViewById(R.id.textview_player_detail_number);
        textViewPlayerDateBirth = (TextView) view.findViewById(R.id.textview_player_detail_date_birth);
        textViewPlayerNationality = (TextView) view.findViewById(R.id.textview_player_detail_nationality);
        textViewPlayerEndContract = (TextView) view.findViewById(R.id.textview_player_detail_contract_until);
        textViewPlayerMarketValue = (TextView) view.findViewById(R.id.textview_player_detail_market_value);

        textViewPlayerNameLabel = (TextView) view.findViewById(R.id.textview_player_detail_name_label);
        textViewPlayerPositionLabel = (TextView) view.findViewById(R.id.textview_player_detail_position_label);
        textViewPlayerNumberLabel = (TextView) view.findViewById(R.id.textview_player_detail_number_label);
        textViewPlayerDateBirthLabel = (TextView) view.findViewById(R.id.textview_player_detail_date_birth_label);
        textViewPlayerNationalityLabel = (TextView) view.findViewById(R.id.textview_player_detail_nationality_label);
        textViewPlayerEndContractLabel = (TextView) view.findViewById(R.id.textview_player_detail_contract_until_label);
        textViewPlayerMarketValueLabel = (TextView) view.findViewById(R.id.textview_player_detail_market_value_label);


//        requestBuilder = Glide.with(getActivity())
//                .using(Glide.buildStreamModelLoader(Uri.class, getActivity()), InputStream.class)
//                .from(Uri.class)
//                .as(SVG.class)
//                .transcode(new SvgDrawableTranscoder(), PictureDrawable.class)
//                .sourceEncoder(new StreamEncoder())
//                .cacheDecoder(new FileToStreamDecoder<SVG>(new SvgDecoder()))
//                .decoder(new SvgDecoder())
//                .listener(new SvgSoftwareLayerSetter<Uri>());

        return view;
    }

    public void getPlayerInfo() {
        Log.i(TAG, "getPlayerInfo() inside method");

//        textViewPlayerName.setText(players.getName());
//        textViewPlayerPosition.setText(players.getPosition());
//
//        if (players.getJerseyNumber() != null)
//            textViewPlayerNumber.setText(players.getJerseyNumber().toString());
//        else
//            textViewPlayerNumber.setText("N/A");
//
//        textViewPlayerDateBirth.setText(players.getDateOfBirth());
//        textViewPlayerNationality.setText(players.getNationality());
//        textViewPlayerEndContract.setText(players.getContractUntil());
//        textViewPlayerMarketValue.setText(players.getMarketValue());

        Log.i(TAG, "Player value " + getmPlayers().getNationality());

        textViewPlayerNameLabel.setVisibility(View.VISIBLE);
        textViewPlayerPositionLabel.setVisibility(View.VISIBLE);
        textViewPlayerNumberLabel.setVisibility(View.VISIBLE);
        textViewPlayerDateBirthLabel.setVisibility(View.VISIBLE);
        textViewPlayerNationalityLabel.setVisibility(View.VISIBLE);
        textViewPlayerEndContractLabel.setVisibility(View.VISIBLE);
        textViewPlayerMarketValueLabel.setVisibility(View.VISIBLE);


    }

//    private void loadNet(String url) {
//
//        Uri uri = Uri.parse(url);
//
//        String extension = ExtensionLinkGetter.getExtensionFromLink(url);
//
//        if((extension.equals("gif")) || (extension.equals("png")) || (extension.equals("jpg"))) {
//            Log.i(TAG, "inside if - " + extension);
//
//            Glide.with(getActivity())
//                    .load(uri)
//                    .override(125, 125)
//                    .into(imageTeamLogo);
//
//        } else {
//
//            requestBuilder.diskCacheStrategy(DiskCacheStrategy.SOURCE)
//                    // SVG cannot be serialized so it's not worth to cache it
//                    .load(uri)
//                    .override(125, 125)
//                    .into(imageTeamLogo);
//
//        }
//
//    }


    public Players getmPlayers() {
        return mPlayers;
    }

    public void setmPlayers(Players mPlayers) {
        this.mPlayers = mPlayers;
    }
}
