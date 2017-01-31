package com.soccer.soccercheck.util;

import android.os.AsyncTask;
import android.util.Log;

import com.soccer.soccercheck.listeners.OnPostTaskCompetitionListener;
import com.soccer.soccercheck.model.Competition;
import com.soccer.soccercheck.services.CompetitionsService;

import retrofit2.Call;


/**
 * Created by ederson.js on 31/10/2016.
 */

public class CompetitionInfo extends AsyncTask<String, Void, String> {
    private static final String TAG = "CompetitionInfo";

    private OnPostTaskCompetitionListener mOnPostTaskListener;
    private Call<Competition> mCallCompetition;
    private Integer idCompetition;

    public CompetitionInfo(OnPostTaskCompetitionListener mOnPostTaskListener, Integer idCompetition){
        this.mOnPostTaskListener = mOnPostTaskListener;
        this.idCompetition = idCompetition;
    }

    @Override
    protected String doInBackground(String... params) {

        String competitionName = null;
        mCallCompetition = CompetitionsService.Factory.create().fetchCompetition(idCompetition);

        try {

            Competition mCompetition = mCallCompetition.execute().body();
            competitionName = mCompetition.getCaption();

        } catch (Exception e) {
            e.printStackTrace();
            competitionName = null;

        }

        return competitionName;
    }

    @Override
    protected void onPostExecute(String competitionName) {
        Log.i(TAG, "onPostExecute() inside method");

        mOnPostTaskListener.onTaskCompetitionCompleted(competitionName);

    }
}
