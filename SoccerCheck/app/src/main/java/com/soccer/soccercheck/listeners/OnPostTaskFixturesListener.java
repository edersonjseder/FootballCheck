package com.soccer.soccercheck.listeners;

import com.soccer.soccercheck.model.FixturesData;
import com.soccer.soccercheck.model.LeagueTable;

/**
 * Created by ederson.js on 31/10/2016.
 */

public interface OnPostTaskFixturesListener {

    void onTaskFixturesCompleted(FixturesData fixturesData);

}
