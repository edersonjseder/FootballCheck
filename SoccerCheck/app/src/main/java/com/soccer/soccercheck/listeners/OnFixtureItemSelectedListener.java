package com.soccer.soccercheck.listeners;

import com.soccer.soccercheck.model.Fixture;

/**
 * Created by root on 06/11/16.
 */

public interface OnFixtureItemSelectedListener {

    // called when user selects a contact
    public void onFixtureItemSelected(Fixture fixture, int position);

}
