package com.soccer.soccercheck.factory;

import android.content.Context;

import com.soccer.soccercheck.helper.DataBaseHelper;
import com.soccer.soccercheck.model.Competition;
import com.soccer.soccercheck.model.FixturesData;
import com.soccer.soccercheck.model.LeagueTable;

import java.sql.SQLException;

/**
 * Created by ederson.js on 06/02/2017.
 */

public class DatabaseManager {

    static private DatabaseManager instance;

    static public void init(Context context) {
        if (instance == null) {
            instance = new DatabaseManager(context);
        }
    }

    static public DatabaseManager getInstance() {
        return instance;
    }

    private DataBaseHelper helper;

    private DatabaseManager(Context context) {
        helper = new DataBaseHelper(context);
    }

    private DataBaseHelper getHelper() {
        return helper;
    }

    /** Competition Functions **/
    public Competition getCompetition(Integer id) {
        Competition competition = null;

        try {

            competition = getHelper().getCompetitionDao().queryForId(id);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return competition;
    }

    public void saveCompetition(Competition competition) {

        try {

            getHelper().getCompetitionDao().create(competition);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void updateCompetition(Competition competition) {

        try {

            getHelper().getCompetitionDao().update(competition);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    /** Competition Functions **/

    /** LeagueTable Functions **/
    public LeagueTable getLeagueTable(Integer id) {
        LeagueTable leagueTable = null;

        try {

            leagueTable = getHelper().getLeagueTableDao().queryForId(id);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return leagueTable;
    }

    public boolean saveLeagueTable(LeagueTable leagueTable) {

        int rowAffected = 0;

        try {

            rowAffected = getHelper().getLeagueTableDao().create(leagueTable);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return (rowAffected == 0) ? false : true;

    }

    public void updateLeagueTable(LeagueTable leagueTable) {

        try {

            getHelper().getLeagueTableDao().update(leagueTable);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    /** LeagueTable Functions **/

    /** FixturesData Functions **/
    public FixturesData getFixturesData(Integer id) {
        FixturesData fixturesData = null;

        try {

            fixturesData = getHelper().getFixturesDataDao().queryForId(id);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return fixturesData;
    }

    public boolean saveFixturesData(FixturesData fixturesData) {

        int rowAffected = 0;

        try {

            rowAffected = getHelper().getFixturesDataDao().create(fixturesData);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return (rowAffected == 0) ? false : true;
    }

    public void updateFixturesData(FixturesData fixturesData) {

        try {

            getHelper().getFixturesDataDao().update(fixturesData);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    /** FixturesData Functions **/
}
