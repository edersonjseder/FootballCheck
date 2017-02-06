package com.soccer.soccercheck.fragments;

import android.app.Dialog;
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
import android.widget.Toast;

import com.bumptech.glide.GenericRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.StreamEncoder;
import com.bumptech.glide.load.resource.file.FileToStreamDecoder;
import com.caverock.androidsvg.SVG;
import com.soccer.soccercheck.R;
import com.soccer.soccercheck.model.Competition;
import com.soccer.soccercheck.model.Fixture;
import com.soccer.soccercheck.model.Team;
import com.soccer.soccercheck.services.CompetitionsService;
import com.soccer.soccercheck.services.TeamService;
import com.soccer.soccercheck.util.ExtensionLinkGetter;
import com.soccer.soccercheck.util.SvgDecoder;
import com.soccer.soccercheck.util.SvgDrawableTranscoder;
import com.soccer.soccercheck.util.SvgSoftwareLayerSetter;

import org.joda.time.DateTime;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ederson.js on 25/01/2017.
 */

public class FixtureDetailFragment extends Fragment {
    private static final String TAG = "FixtureDetailFragment";

    public static final String FIXTURE = "fixture";

    private ImageView imageViewFixtureDetailHomeTeam;
    private ImageView imageViewFixtureDetailAwayTeam;

    private TextView textViewFixtureDetailCompetitionTitle;
    private TextView textViewFixtureDetailTitleMatchDay;
    private TextView textViewFixtureDetailHomeTeamName;
    private TextView textViewFixtureDetailScoreHomeTeam;
    private TextView textViewFixtureDetailAwayTeamName;
    private TextView textViewFixtureDetailScoreAwayteam;
    private TextView textViewFixtureDetailDate;
    private TextView textViewFixtureDetailTime;
    private TextView textViewFixtureDetailStatus;
    private TextView textViewFixtureDetailHomeTeamNameHalftime;
    private TextView textViewFixtureDetailScoreHomeTeamHalftime;
    private TextView textViewFixtureDetailAwayTeamNameHalftime;
    private TextView textViewFixtureDetailScoreAwayTeamHalftime;

    private SimpleDateFormat simpleDateFormat;
    private DateTime fixtureDate;

    private GenericRequestBuilder<Uri, InputStream, SVG, PictureDrawable> requestBuilder;

    public static FixtureDetailFragment newInstance(Fixture fixture) {
        Log.i(TAG, "newInstance() inside method");

        FixtureDetailFragment fragment = new FixtureDetailFragment();

        Bundle args = new Bundle();

        args.putSerializable(FIXTURE, fixture);

        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate() inside method");

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView() inside method");

        View view = inflater.inflate(R.layout.fragment_fixture_detail_soccer, container, false);

        simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

        imageViewFixtureDetailHomeTeam = (ImageView) view.findViewById(R.id.imageview_fixture_detail_home_team);
        imageViewFixtureDetailAwayTeam = (ImageView) view.findViewById(R.id.imageview_fixture_detail_away_team);

        textViewFixtureDetailCompetitionTitle = (TextView) view.findViewById(R.id.textview_fixture_detail_competition_title);
        textViewFixtureDetailTitleMatchDay = (TextView) view.findViewById(R.id.textview_fixture_detail_title_match_day);
        textViewFixtureDetailHomeTeamName = (TextView) view.findViewById(R.id.textview_fixture_detail_home_team_name);
        textViewFixtureDetailScoreHomeTeam = (TextView) view.findViewById(R.id.textview_fixture_detail_score_home_team);
        textViewFixtureDetailAwayTeamName = (TextView) view.findViewById(R.id.textview_fixture_detail_away_team_name);
        textViewFixtureDetailScoreAwayteam = (TextView) view.findViewById(R.id.textview_fixture_detail_score_away_team);
        textViewFixtureDetailDate = (TextView) view.findViewById(R.id.textview_fixture_detail_date);
        textViewFixtureDetailTime = (TextView) view.findViewById(R.id.textview_fixture_detail_time);
        textViewFixtureDetailStatus = (TextView) view.findViewById(R.id.textview_fixture_detail_status);
        textViewFixtureDetailHomeTeamNameHalftime = (TextView) view.findViewById(R.id.textview_fixture_detail_home_team_name_halftime);
        textViewFixtureDetailScoreHomeTeamHalftime = (TextView) view.findViewById(R.id.textview_fixture_detail_score_home_team_halftime);
        textViewFixtureDetailAwayTeamNameHalftime = (TextView) view.findViewById(R.id.textview_fixture_detail_away_team_name_halftime);
        textViewFixtureDetailScoreAwayTeamHalftime = (TextView) view.findViewById(R.id.textview_fixture_detail_score_away_team_halftime);

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

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i(TAG, "onViewCreated() inside method");

        Bundle args = getArguments();
        Fixture fixture = (Fixture) args.getSerializable(FIXTURE);

        getFixtureDetailInfo(fixture);
    }

    private void getFixtureDetailInfo(Fixture fixture) {
        Log.i(TAG, "getFixtureDetailInfo() inside method");

        textViewFixtureDetailTitleMatchDay.setText(fixture.getMatchday().toString());

        textViewFixtureDetailHomeTeamName.setText(fixture.getHomeTeamName());

        // Verifying if the score home team comes null
        if (fixture.getResult().getGoalsHomeTeam() != null)
            textViewFixtureDetailScoreHomeTeam.setText(fixture.getResult().getGoalsHomeTeam().toString());
        else
            textViewFixtureDetailScoreHomeTeam.setText("");

        textViewFixtureDetailAwayTeamName.setText(fixture.getAwayTeamName());

        // Verifying if the score away team comes null
        if (fixture.getResult().getGoalsAwayTeam() != null)
            textViewFixtureDetailScoreAwayteam.setText(fixture.getResult().getGoalsAwayTeam().toString());
        else
            textViewFixtureDetailScoreAwayteam.setText("");

        fixtureDate = new DateTime(fixture.getDate());

        // get date in Millis
        long value = fixtureDate.getMillis();

        // Create an object Date with millis as a parameter to convert date to the common pattern
        Date mDate = new Date(value);

        // Format the date to the standard form
        String dateConverted = simpleDateFormat.format(mDate);

        simpleDateFormat = new SimpleDateFormat("HH:mm");
        String timeConverted = simpleDateFormat.format(mDate.getTime());

        textViewFixtureDetailDate.setText(dateConverted);
        textViewFixtureDetailTime.setText(timeConverted);
        textViewFixtureDetailStatus.setText(fixture.getStatus());

        textViewFixtureDetailHomeTeamNameHalftime.setText(fixture.getHomeTeamName());

        // Verifying if the score home team halftime comes null
        if (fixture.getResult().getHalfTime() != null)
            textViewFixtureDetailScoreHomeTeamHalftime.setText(fixture.getResult().getHalfTime().getGoalsHomeTeam().toString());
        else
            textViewFixtureDetailScoreHomeTeamHalftime.setText("");

        textViewFixtureDetailAwayTeamNameHalftime.setText(fixture.getAwayTeamName());

        // Verifying if the score away team halftime comes null
        if (fixture.getResult().getHalfTime() != null)
            textViewFixtureDetailScoreAwayTeamHalftime.setText(fixture.getResult().getHalfTime().getGoalsAwayTeam().toString());
        else
            textViewFixtureDetailScoreAwayTeamHalftime.setText("");
    }
/**
    public void getHomeTeamImageFromLink(String homeTeamLink) {
        Log.i(TAG, "getHomeTeamImageFromLink() inside method");

        String extension = ExtensionLinkGetter.getExtensionFromLink(homeTeamLink);

        if (homeTeamLink != null) {

            Uri uri = Uri.parse(homeTeamLink);

            if ((extension.equals("gif")) || (extension.equals("png")) || (extension.equals("jpg"))) {
                Log.i(TAG, "inside if - " + extension);

                Glide.with(getContext())
                        .load(uri)
                        .override(30, 30)
                        .into(imageViewFixtureDetailHomeTeam);

            } else {
                Log.i(TAG, "inside else - " + extension);

                requestBuilder.diskCacheStrategy(DiskCacheStrategy.SOURCE)
                        .load(uri)
                        .override(30, 30)
                        .into(imageViewFixtureDetailHomeTeam);

            }
        }

    }**/
/**
    public void getAwayTeamImageFromLink(String awayTeamLink) {
        Log.i(TAG, "getAwayTeamImageFromLink() inside method");

        String extension = ExtensionLinkGetter.getExtensionFromLink(awayTeamLink);

        if (awayTeamLink != null) {

            Uri uri = Uri.parse(awayTeamLink);

            if ((extension.equals("gif")) || (extension.equals("png")) || (extension.equals("jpg"))) {
                Log.i(TAG, "inside if - " + extension);

                Glide.with(getContext())
                        .load(uri)
                        .override(30, 30)
                        .into(imageViewFixtureDetailAwayTeam);

            } else {
                Log.i(TAG, "inside else - " + extension);

                requestBuilder.diskCacheStrategy(DiskCacheStrategy.SOURCE)
                        .load(uri)
                        .override(30, 30)
                        .into(imageViewFixtureDetailAwayTeam);

            }
        }

    } **/
}
