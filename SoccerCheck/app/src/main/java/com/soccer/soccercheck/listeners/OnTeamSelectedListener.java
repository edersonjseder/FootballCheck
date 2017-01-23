package com.soccer.soccercheck.listeners;

import com.soccer.soccercheck.model.Standing;

/**
 * Created by root on 06/11/16.
 */

public interface OnTeamSelectedListener {

    // called when user selects a team
    void onTeamSelected(Standing standing, int position);

}
