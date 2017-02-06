package com.soccer.soccercheck.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.soccer.soccercheck.model.Competition;
import com.soccer.soccercheck.model.FixturesData;
import com.soccer.soccercheck.model.LeagueTable;

import java.sql.SQLException;

/**
 * Created by root on 05/02/17.
 */

public class DataBaseHelper extends OrmLiteSqliteOpenHelper {
    private static final String TAG = "DataBaseHelper";

    private static final String DATABASE_NAME = "db_mobtur.db";
    private static final int DATABASE_VERSION = 1;

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        Log.i(TAG, "onCreate() inside method");

        try {
            Log.i(DataBaseHelper.class.getName(), "onCreate");

            TableUtils.createTable(connectionSource, Competition.class);
            TableUtils.createTable(connectionSource, LeagueTable.class);
            TableUtils.createTable(connectionSource, FixturesData.class);

        } catch (SQLException e) {
            Log.e(DataBaseHelper.class.getName(), "Can't create database", e);

            throw new RuntimeException(e);

        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        Log.i(TAG, "onUpgrade() inside method");

        try {
            Log.i(DataBaseHelper.class.getName(), "onUpgrade");

            TableUtils.dropTable(connectionSource, Competition.class, true);
            TableUtils.dropTable(connectionSource, LeagueTable.class, true);
            TableUtils.dropTable(connectionSource, FixturesData.class, true);

        } catch (SQLException e) {
            Log.e(DataBaseHelper.class.getName(), "Can't drop database", e);

            throw new RuntimeException(e);

        }

    }

    @Override
    public void close() {
        super.close();
    }
}
