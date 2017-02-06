package com.soccer.soccercheck.factory;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.soccer.soccercheck.helper.DataBaseHelper;
import com.soccer.soccercheck.model.Competition;
import com.soccer.soccercheck.model.FixturesData;
import com.soccer.soccercheck.model.LeagueTable;

import java.sql.SQLException;

/**
 * Created by root on 05/02/17.
 */

public class DaoFactory extends Application {
    private static final String TAG = "DaoFactory";

    private SharedPreferences preferences;
    private DataBaseHelper databaseHelper = null;

    private Dao<Competition, Integer> competitionDAO = null;
    private Dao<LeagueTable, Integer> leagueTableDAO = null;
    private Dao<FixturesData, Integer> fixtureDataDAO = null;

    @Override
    public void onCreate() {
        super.onCreate();
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        databaseHelper = new DataBaseHelper(this);
    }

    public SharedPreferences getPreferences() {return preferences;}

    public Dao<Competition, Integer> getCompetitionDao() throws SQLException {
        if (competitionDAO == null) {
            competitionDAO = databaseHelper.getDao(Competition.class);
        }
        return competitionDAO;
    }

    public Dao<LeagueTable, Integer> getLeagueTableDao() throws SQLException {
        if (leagueTableDAO == null) {
            leagueTableDAO = databaseHelper.getDao(LeagueTable.class);
        }
        return leagueTableDAO;
    }

    public Dao<FixturesData, Integer> getFixturesDataDao() throws SQLException {
        if (fixtureDataDAO == null) {
            fixtureDataDAO = databaseHelper.getDao(FixturesData.class);
        }
        return fixtureDataDAO;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        if (databaseHelper != null) {
            OpenHelperManager.releaseHelper();
            databaseHelper = null;
        }
    }

}
