package com.soccer.soccercheck.util;

import android.app.Dialog;
import android.os.AsyncTask;
import android.util.Log;

import com.soccer.soccercheck.listeners.OnPostTaskAwayTeamListener;
import com.soccer.soccercheck.model.Team;
import com.soccer.soccercheck.services.TeamService;

import retrofit2.Call;


/**
 * Created by ederson.js on 31/10/2016.
 */

public class AwayTeamInfo extends AsyncTask<String, Void, Team> {
    private static final String TAG = "AwayTeamInfo";

    private OnPostTaskAwayTeamListener mOnPostTaskListener;
    private Call<Team> mCallTeam;
    private Dialog progress;
    private Integer idAwayTeam;

    public AwayTeamInfo(OnPostTaskAwayTeamListener mOnPostTaskListener, Integer idAwayTeam){
        this.mOnPostTaskListener = mOnPostTaskListener;
        this.idAwayTeam = idAwayTeam;
    }

    @Override
    protected Team doInBackground(String... params) {

        Team mTeam = null;
        mCallTeam = TeamService.Factory.create().getTeam(idAwayTeam);

        try {

            mTeam = mCallTeam.execute().body();

        } catch (Exception e) {
            e.printStackTrace();
            mTeam = null;

        }

        return mTeam;
    }

    @Override
    protected void onPostExecute(Team team) {
        Log.i(TAG, "onPostExecute() inside method");

        mOnPostTaskListener.onTaskAwayTeamCompleted(team);

    }
}
