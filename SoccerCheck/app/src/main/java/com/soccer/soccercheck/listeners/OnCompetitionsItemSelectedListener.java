package com.soccer.soccercheck.listeners;

import com.soccer.soccercheck.model.Competition;

/**
 * Created by root on 06/11/16.
 */

public interface OnCompetitionsItemSelectedListener {

    // called when user selects a contact
    public void onCompetitionsItemSelected(Competition competition, int position);

}
