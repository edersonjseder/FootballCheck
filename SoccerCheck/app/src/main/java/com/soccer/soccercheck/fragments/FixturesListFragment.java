package com.soccer.soccercheck.fragments;

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
import com.soccer.soccercheck.adapters.LeagueTableAdapter;
import com.soccer.soccercheck.main.SoccerMainActivity;
import com.soccer.soccercheck.model.FixturesData;
import com.soccer.soccercheck.services.FixturesDataService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FixturesListFragment extends Fragment {
    private static final String TAG = "FixturesListFragment";
    private static final String TITLE = "Table";

    public static final String MATCHDAY = "matchday";

    private RecyclerView recyclerViewFixturesList;
    private FixturesListAdapter fixturesListAdapter;

    private Call<FixturesData> mCallFixturesData;
    private FixturesData mFixturesData;

    public static FixturesListFragment newInstance(Integer idCompetition) {
        Log.i(TAG, "newInstance() inside method " + idCompetition);

        FixturesListFragment fragment = new FixturesListFragment();

        Bundle args = new Bundle();
        args.putInt("IDCOMPETITION", idCompetition);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate() inside method");

        Bundle args = getArguments();
        int idCompetition = args.getInt("IDCOMPETITION");

        Intent intent = getActivity().getIntent();

        int currentMatchDay = intent.getIntExtra(MATCHDAY, -1);

        Log.i(TAG, "onCreate() inside method " + idCompetition);

        if (savedInstanceState != null)
            return;

        if (savedInstanceState == null) {
            getLeagueTable(idCompetition, currentMatchDay);

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

    public void getLeagueTable(Integer id, Integer currentMatchDay) {
        Log.i(TAG, "getLeagueTable() inside method " + id);

        mCallFixturesData = FixturesDataService.Factory.create().retrieveFixturesById(id, currentMatchDay);

        mCallFixturesData.enqueue(new Callback<FixturesData>() {
            @Override
            public void onResponse(Call<FixturesData> call, Response<FixturesData> response) {

                mFixturesData = response.body();

                if(mFixturesData != null) {

                    showLeagueTable(mFixturesData);

                }

            }

            @Override
            public void onFailure(Call<FixturesData> call, Throwable t) {
                Log.i(TAG, "onFailure() inside method");
                t.printStackTrace();
            }
        });

    }

    private void showLeagueTable(FixturesData fixturesData) {
        Log.i(TAG, "showLeagueTable() inside method");

        if(fixturesData != null){

            fixturesListAdapter = new FixturesListAdapter(fixturesData, getContext());
            recyclerViewFixturesList.setAdapter(fixturesListAdapter);

        }

    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle(TITLE);
    }

}
