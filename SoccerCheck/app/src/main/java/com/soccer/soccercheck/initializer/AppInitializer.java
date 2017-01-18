package com.soccer.soccercheck.initializer;

import android.app.Application;
import android.content.Context;

import com.soccer.soccercheck.services.FixturesDataService;
import com.soccer.soccercheck.services.TeamService;


/**
 * Created by ederson.js on 29/03/2016.
 */
public class AppInitializer extends Application {

    private FixturesDataService fixturesDataService;
    private TeamService teamService;

    @Override
    public void onCreate() {
        super.onCreate();
       // JodaTimeAndroid.init(this);
    }

    public static AppInitializer get(Context context) {
        return (AppInitializer) context.getApplicationContext();
    }

    public FixturesDataService getFixturesDataService() {
        if (fixturesDataService == null) {
            fixturesDataService = FixturesDataService.Factory.create();
        }
        return fixturesDataService;
    }

    public TeamService getTeamService() {
        if (teamService == null) {
            teamService = TeamService.Factory.create();
        }
        return teamService;
    }
}
