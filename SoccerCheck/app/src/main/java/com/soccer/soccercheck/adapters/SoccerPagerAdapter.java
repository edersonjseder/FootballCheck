package com.soccer.soccercheck.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.soccer.soccercheck.fragments.CompetitionInfoFragment;
import com.soccer.soccercheck.fragments.FixturesListFragment;
import com.soccer.soccercheck.fragments.LeagueTableFragment;
import com.soccer.soccercheck.main.SoccerMainActivity;

/**
 * Created by ederson.js on 12/01/2017.
 */

public class SoccerPagerAdapter extends FragmentStatePagerAdapter {
    private static final String TAG = "SoccerPagerAdapter";

    private int numberOfTabs;
    private int idCompetition;

    public SoccerPagerAdapter(FragmentManager fm, int numberOfTabs, int idCompetition) {
        super(fm);
        this.numberOfTabs = numberOfTabs;
        this.idCompetition = idCompetition;
    }

    @Override
    public Fragment getItem(int position) {
        Log.i(TAG, "getItem() inside method " + position);

        switch (position) {
            case 0:
                Log.i(TAG, "getItem() inside switch " + position + " ID " + idCompetition);
                CompetitionInfoFragment tab1 = CompetitionInfoFragment.newInstance(idCompetition);
                return tab1;

            case 1:
                Log.i(TAG, "getItem() inside switch " + position + " ID " + idCompetition);
                LeagueTableFragment tab2 = LeagueTableFragment.newInstance(idCompetition);
                return tab2;

            case 2:
                Log.i(TAG, "getItem() inside switch " + position + " ID " + idCompetition);
                FixturesListFragment tab3 = FixturesListFragment.newInstance(idCompetition);
                return tab3;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }
}
