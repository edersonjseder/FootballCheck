package com.soccer.soccercheck.listeners;

import com.soccer.soccercheck.model.Fixture;
import com.soccer.soccercheck.model.Team;

/**
 * Created by root on 06/11/16.
 */

public interface OnTeamSelectedListener {

    // called when user selects a team
    public void onTeamSelected(Team team, int position);

}
