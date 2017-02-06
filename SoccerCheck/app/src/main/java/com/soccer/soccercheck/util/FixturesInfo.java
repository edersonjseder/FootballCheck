package com.soccer.soccercheck.util;

import android.os.AsyncTask;
import android.util.Log;

import com.soccer.soccercheck.listeners.OnPostTaskFixturesListener;
import com.soccer.soccercheck.listeners.OnPostTaskLeagueTableListener;
import com.soccer.soccercheck.model.FixturesData;
import com.soccer.soccercheck.model.LeagueTable;
import com.soccer.soccercheck.services.FixturesDataService;
import com.soccer.soccercheck.services.LeagueTableService;

import retrofit2.Call;


/**
 * Created by ederson.js on 31/10/2016.
 */

public class FixturesInfo extends AsyncTask<String, Void, FixturesData> {
    private static final String TAG = "FixturesInfo";

    private OnPostTaskFixturesListener mOnPostTaskListener;
    private Call<FixturesData> mCallFixturesData;
    private Integer idCompetition;

    public FixturesInfo(OnPostTaskFixturesListener mOnPostTaskListener, Integer idCompetition){
        this.mOnPostTaskListener = mOnPostTaskListener;
        this.idCompetition = idCompetition;
    }

    @Override
    protected FixturesData doInBackground(String... params) {

        FixturesData fixturesData = null;
        mCallFixturesData = FixturesDataService.Factory.create().fetchFixturesById(idCompetition);

        try {

            fixturesData = mCallFixturesData.execute().body();

        } catch (Exception e) {
            e.printStackTrace();

        }

        return fixturesData;
    }

    @Override
    protected void onPostExecute(FixturesData fixturesData) {
        Log.i(TAG, "onPostExecute() inside method");

        mOnPostTaskListener.onTaskFixturesCompleted(fixturesData);

    }
}
