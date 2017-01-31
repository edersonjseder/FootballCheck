package com.soccer.soccercheck.listeners;

import com.soccer.soccercheck.model.FixturesData;
import com.soccer.soccercheck.model.Team;

import java.util.List;

/**
 * Created by ederson.js on 31/10/2016.
 */

public interface OnPostTaskHomeTeamListener {

    void onTaskHomeTeamCompleted(Team team);

}
