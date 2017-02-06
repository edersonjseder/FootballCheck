package com.soccer.soccercheck.fragments;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.soccer.soccercheck.R;
import com.soccer.soccercheck.adapters.FixturesListAdapter;
import com.soccer.soccercheck.listeners.OnPostTaskAwayTeamListener;
import com.soccer.soccercheck.listeners.OnPostTaskCompetitionListener;
import com.soccer.soccercheck.listeners.OnPostTaskHomeTeamListener;
import com.soccer.soccercheck.model.Competition;
import com.soccer.soccercheck.model.FixturesData;
import com.soccer.soccercheck.model.Team;
import com.soccer.soccercheck.services.CompetitionsService;
import com.soccer.soccercheck.services.FixturesDataService;
import com.soccer.soccercheck.services.TeamService;
import com.soccer.soccercheck.util.AwayTeamInfo;
import com.soccer.soccercheck.util.CompetitionInfo;
import com.soccer.soccercheck.util.HomeTeamInfo;

import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FixturesListFragment extends Fragment implements OnPostTaskHomeTeamListener,
        OnPostTaskAwayTeamListener, OnPostTaskCompetitionListener {

    private static final String TAG = "FixturesListFragment";
    private static final String TITLE = "Table";

    public static final String MATCHDAY = "matchday";
    public static final String TEAMCODE = "teamCode";

    private RecyclerView recyclerViewFixturesList;
    private FixturesListAdapter fixturesListAdapter;

    private Call<FixturesData> mCallFixturesData;

    private OnPostTaskHomeTeamListener onPostTaskHomeTeamListener;
    private OnPostTaskAwayTeamListener onPostTaskAwayTeamListener;
    private OnPostTaskCompetitionListener onPostTaskCompetitionListener;

    private HomeTeamInfo homeTeamConnect;
    private AwayTeamInfo awayTeamConnect;
    private CompetitionInfo competitionConnect;

    private Team hTeam;
    private Team aTeam;
    private String competitionName;

    public static FixturesListFragment newInstance(Integer id) {
        Log.i(TAG, "newInstance() inside method " + id);

        FixturesListFragment fragment = new FixturesListFragment();

        Bundle args = new Bundle();
        args.putInt("ID", id);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate() inside method");

        Bundle args = getArguments();
        int id = args.getInt("ID");

        Intent intent = getActivity().getIntent();

        int currentMatchDay = intent.getIntExtra(MATCHDAY, -1);

        String code = intent.getStringExtra(TEAMCODE);

        onPostTaskHomeTeamListener = this;
        onPostTaskAwayTeamListener = this;
        onPostTaskCompetitionListener = this;

        Log.i(TAG, "onCreate() inside method " + id);
        Log.i(TAG, "onCreate() inside method - Code:" + code);

        if (savedInstanceState != null)
            return;

        if (savedInstanceState == null) {

            if(code != null) {
                Log.i(TAG, "inside if - Code: " + code);

                getTeamFixturesData(id, code);

            } else {
                Log.i(TAG, "inside else - currentMatchDay: " + currentMatchDay);

                getFixturesData(id, currentMatchDay);

            }

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_fixtures_list_soccer, container, false);

        recyclerViewFixturesList = (RecyclerView) v.findViewById(R.id.id_recycler_view_fixture_list);
        recyclerViewFixturesList.setHasFixedSize(true);
        recyclerViewFixturesList.setLayoutManager(new LinearLayoutManager(getActivity()));

        return v;

    }

    public void getFixturesData(Integer id, Integer currentMatchDay) {
        Log.i(TAG, "getFixturesData() inside method " + id);

        mCallFixturesData = FixturesDataService.Factory.create().retrieveFixturesById(id, currentMatchDay);

        mCallFixturesData.enqueue(new Callback<FixturesData>() {
            @Override
            public void onResponse(Call<FixturesData> call, Response<FixturesData> response) {
                Log.i(TAG, "onResponse() inside method");

                FixturesData mFixturesData = response.body();

                fillingTeamAndCompetitionNameInfo(mFixturesData);

                if(mFixturesData != null) {

                    showFixtures(mFixturesData, null);

                }

            }

            @Override
            public void onFailure(Call<FixturesData> call, Throwable t) {
                Log.e(TAG, "onFailure() inside method");
                t.printStackTrace();
            }
        });

    }

    private void showFixtures(FixturesData fixturesData, String code) {
        Log.i(TAG, "showFixtures() inside method");

        if(fixturesData != null){

            fixturesListAdapter = new FixturesListAdapter(fixturesData, getContext(), code);
            recyclerViewFixturesList.setAdapter(fixturesListAdapter);

        }

    }

    private void fillingTeamAndCompetitionNameInfo(FixturesData fixturesData) {
        Log.i(TAG, "fillingTeamAndCompetitionNameInfo() inside method");


        for (int i = 0; i < fixturesData.getFixtures().size(); i++) {

            Integer idCompetitionFromFixtures = getIdFromLink(fixturesData.getFixtures().get(i).getLinks().getCompetition().getHref());
            Integer idHomeTeam = getIdFromLink(fixturesData.getFixtures().get(i).getLinks().getHomeTeam().getHref());
            Integer idAwayTeam = getIdFromLink(fixturesData.getFixtures().get(i).getLinks().getAwayTeam().getHref());

            competitionConnect = new CompetitionInfo(onPostTaskCompetitionListener, idCompetitionFromFixtures);
            homeTeamConnect = new HomeTeamInfo(onPostTaskHomeTeamListener, idHomeTeam);
            awayTeamConnect = new AwayTeamInfo(onPostTaskAwayTeamListener, idAwayTeam);

            competitionConnect.execute();
            homeTeamConnect.execute();
            awayTeamConnect.execute();

            fixturesData.getFixtures().get(i).setCompetitionName(competitionName);
            fixturesData.getFixtures().get(i).setHomeTeam(hTeam);
            fixturesData.getFixtures().get(i).setAwayTeam(aTeam);
        }

    }

    public void getTeamFixturesData(Integer id, final String code) {
        Log.i(TAG, "getFixturesData() inside method " + id);

        mCallFixturesData = TeamService.Factory.create().fetchTeamFixturesById(id);

        mCallFixturesData.enqueue(new Callback<FixturesData>() {
            @Override
            public void onResponse(Call<FixturesData> call, Response<FixturesData> response) {

                FixturesData mTeamFixturesData = response.body();

                if(mTeamFixturesData != null) {

                    showFixtures(mTeamFixturesData, code);

                }

            }

            @Override
            public void onFailure(Call<FixturesData> call, Throwable t) {
                Log.e(TAG, "onFailure() inside method");
                t.printStackTrace();
            }
        });
    }


/**
    private Team fetchHomeTeamInfo(Integer idHomeTeam) {
        Log.i(TAG, "fetchHomeTeamInfo() inside method");

        final Team[] mTeam = {null};
        mCallTeam = TeamService.Factory.create().getTeam(idHomeTeam);

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {

                    mTeam[0] = mCallTeam.execute().body();

                } catch (IOException e) {
                    e.printStackTrace();

                }

            }
        }).start();
//
//        Executors.newSingleThreadExecutor().submit(new Runnable() {
//            @Override
//            public void run() {
//
//                try {
//
//                    mTeam[0] = mCallTeam.execute().body();
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//
//                }
//
//            }
//        });

        return mTeam[0];

    }**/
/**
    private Team fetchAwayTeamInfo(Integer idAwayTeam) {
        Log.i(TAG, "fetchAwayTeamInfo() inside method");

        final Team[] mTeam = {null};
        mCallTeam = TeamService.Factory.create().getTeam(idAwayTeam);

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {

                    mTeam[0] = mCallTeam.execute().body();

                } catch (IOException e) {
                    e.printStackTrace();

                }

            }
        }).start();

        return mTeam[0];

    } **/

    // gets the id from the url
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

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle(TITLE);
    }

    @Override
    public void onTaskAwayTeamCompleted(Team team) {
        Log.i(TAG, "onTaskAwayTeamCompleted() inside method - Name: " + team.getName());

        aTeam = team;

    }

    @Override
    public void onTaskCompetitionCompleted(String competitionName) {
        Log.i(TAG, "onTaskCompetitionCompleted() inside method - Competition: " + competitionName);

        this.competitionName = competitionName;

    }

    @Override
    public void onTaskHomeTeamCompleted(Team team) {
        Log.i(TAG, "onTaskHomeTeamCompleted() inside method - Name: " + team.getName());

        hTeam = team;

    }
}
