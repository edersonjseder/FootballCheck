package com.soccer.soccercheck.util;

import android.os.AsyncTask;
import android.util.Log;

import com.soccer.soccercheck.listeners.OnPostTaskCompetitionListener;
import com.soccer.soccercheck.listeners.OnPostTaskLeagueTableListener;
import com.soccer.soccercheck.model.Competition;
import com.soccer.soccercheck.model.LeagueTable;
import com.soccer.soccercheck.services.CompetitionsService;
import com.soccer.soccercheck.services.LeagueTableService;

import retrofit2.Call;


/**
 * Created by ederson.js on 31/10/2016.
 */

public class LeagueTableInfo extends AsyncTask<String, Void, LeagueTable> {
    private static final String TAG = "LeagueTableInfo";

    private OnPostTaskLeagueTableListener mOnPostTaskListener;
    private Call<LeagueTable> mCallLeagueTable;
    private Integer idCompetition;

    public LeagueTableInfo(OnPostTaskLeagueTableListener mOnPostTaskListener, Integer idCompetition){
        this.mOnPostTaskListener = mOnPostTaskListener;
        this.idCompetition = idCompetition;
    }

    @Override
    protected LeagueTable doInBackground(String... params) {

        LeagueTable leagueTable = null;
        mCallLeagueTable = LeagueTableService.Factory.create().fetchLeagueTable(idCompetition);

        try {

            leagueTable = mCallLeagueTable.execute().body();

        } catch (Exception e) {
            e.printStackTrace();

        }

        return leagueTable;
    }

    @Override
    protected void onPostExecute(LeagueTable leagueTable) {
        Log.i(TAG, "onPostExecute() inside method");

        mOnPostTaskListener.onTaskLeagueTableCompleted(leagueTable);

    }
}
