package com.soccer.soccercheck.util;

import android.os.AsyncTask;
import android.util.Log;

import com.soccer.soccercheck.listeners.OnPostTaskHomeTeamListener;
import com.soccer.soccercheck.model.Team;
import com.soccer.soccercheck.services.TeamService;

import retrofit2.Call;


/**
 * Created by ederson.js on 31/10/2016.
 */

public class HomeTeamInfo extends AsyncTask<String, Void, Team> {
    private static final String TAG = "HomeTeamInfo";

    private OnPostTaskHomeTeamListener mOnPostTaskListener;
    private Call<Team> mCallTeam;
    private Integer idHomeTeam;

    public HomeTeamInfo(OnPostTaskHomeTeamListener mOnPostTaskListener, Integer idHomeTeam){
        this.mOnPostTaskListener = mOnPostTaskListener;
        this.idHomeTeam = idHomeTeam;
    }

    @Override
    protected Team doInBackground(String... params) {

        Team mTeam = null;
        mCallTeam = TeamService.Factory.create().getTeam(idHomeTeam);

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

        mOnPostTaskListener.onTaskHomeTeamCompleted(team);

    }
}
