package com.soccer.soccercheck.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.soccer.soccercheck.R;
import com.soccer.soccercheck.adapters.LeagueTableAdapter;
import com.soccer.soccercheck.main.SoccerMainActivity;
import com.soccer.soccercheck.model.LeagueTable;
import com.soccer.soccercheck.services.LeagueTableService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LeagueTableFragment extends Fragment {
    private static final String TAG = "LeagueTableFragment";
    private static final String TITLE = "Table";

    private RecyclerView recyclerViewLeagueTable;
    private LeagueTableAdapter leagueTableAdapter;

    private Call<LeagueTable> mCallLeagueTable;
    private LeagueTable mLeagueTable;

    public static LeagueTableFragment newInstance(Integer idCompetition) {
        Log.i(TAG, "newInstance() inside method " + idCompetition);

        LeagueTableFragment fragment = new LeagueTableFragment();

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

        Log.i(TAG, "onCreate() inside method " + idCompetition);

        if (savedInstanceState != null)
            return;

        if (savedInstanceState == null) {
            getLeagueTable(idCompetition);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_league_table_soccer, container, false);

        recyclerViewLeagueTable = (RecyclerView) v.findViewById(R.id.recycler_view_league_table_id);
        recyclerViewLeagueTable.setHasFixedSize(true);
        recyclerViewLeagueTable.setLayoutManager(new LinearLayoutManager(getActivity()));

        return v;

    }

    public void getLeagueTable(Integer id) {
        Log.i(TAG, "getLeagueTable() inside method " + id);

        mCallLeagueTable = LeagueTableService.Factory.create().fetchLeagueTable(id);

        mCallLeagueTable.enqueue(new Callback<LeagueTable>() {
            @Override
            public void onResponse(Call<LeagueTable> call, Response<LeagueTable> response) {

                mLeagueTable = response.body();

                if(mLeagueTable != null) {

                    showLeagueTable(mLeagueTable);

                }

            }

            @Override
            public void onFailure(Call<LeagueTable> call, Throwable t) {
                Log.i(TAG, "onFailure() inside method");
                t.printStackTrace();
            }
        });

    }

    private void showLeagueTable(LeagueTable leagueTable) {
        Log.i(TAG, "showLeagueTable() inside method");

        if(leagueTable != null){

            leagueTableAdapter = new LeagueTableAdapter(leagueTable, getContext());
            recyclerViewLeagueTable.setAdapter(leagueTableAdapter);

        }

    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle(TITLE);
    }
}
