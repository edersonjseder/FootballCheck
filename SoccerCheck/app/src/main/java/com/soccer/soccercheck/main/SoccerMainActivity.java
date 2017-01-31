package com.soccer.soccercheck.main;

import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

import com.soccer.soccercheck.R;
import com.soccer.soccercheck.fragments.FixtureDetailFragment;
import com.soccer.soccercheck.fragments.FixturesListFragment;
import com.soccer.soccercheck.fragments.PlayerDetailFragment;
import com.soccer.soccercheck.fragments.PlayerListFragment;
import com.soccer.soccercheck.fragments.SoccerTabFragment;
import com.soccer.soccercheck.fragments.TeamDetailFragment;
import com.soccer.soccercheck.listeners.OnFixtureItemSelectedListener;
import com.soccer.soccercheck.listeners.OnPlayerSelectedListener;
import com.soccer.soccercheck.listeners.OnSendCurrentMatchDayListener;
import com.soccer.soccercheck.listeners.OnShowFixtureListListener;
import com.soccer.soccercheck.listeners.OnShowPlayerListListener;
import com.soccer.soccercheck.listeners.OnTeamSelectedListener;
import com.soccer.soccercheck.model.Fixture;
import com.soccer.soccercheck.model.Players;
import com.soccer.soccercheck.model.Standing;
import com.soccer.soccercheck.model.Team;
import com.soccer.soccercheck.path.FBPath;
import com.soccer.soccercheck.util.AlertDialogManager;
import com.soccer.soccercheck.util.ConnectionDetector;

public class SoccerMainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnSendCurrentMatchDayListener,
        OnTeamSelectedListener, OnShowPlayerListListener, OnPlayerSelectedListener, OnShowFixtureListListener,
        OnFixtureItemSelectedListener {

    private static final String TAG = "SoccerMainActivity";

    ConnectionDetector detector;
    AlertDialogManager dialogManager;

    private SoccerTabFragment mSoccerTabFragment;
    private FragmentTransaction transaction;

    /** View Components **/
    private Toolbar toolbar;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;

    private TeamDetailFragment mTeamDetailFragment;

    private PlayerListFragment mPlayerListFragment;
    private PlayerDetailFragment mPlayerDetailFragment;

    private FixturesListFragment mFixturesListFragment;
    private FixtureDetailFragment mFixtureDetailFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main_soccer);

        dialogManager = new AlertDialogManager();
        detector = new ConnectionDetector(getApplicationContext());

        // Check for internet connection
        if (!detector.isConnectingToInternet()) {
            // Internet Connection is not present
            dialogManager.showAlertDialog(SoccerMainActivity.this, "Internet Connection Error",
                    "Please connect to working Internet connection", false);
            // stop executing code by return
            return;
        }

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(this);

        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        // return if Activity is being restored, no need to recreate GUI
        if (savedInstanceState != null)
            return;

    }


    private void startTabsSoccerFragment(Integer idCompetition) {

        mSoccerTabFragment = SoccerTabFragment.newInstance(idCompetition);

        try {

            transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.id_content_fragments, mSoccerTabFragment);
            transaction.addToBackStack("Back");
            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_football, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_champions_league) {
            startTabsSoccerFragment(Integer.parseInt(FBPath.CL));

        } else if (id == R.id.nav_premiere_league) {
            startTabsSoccerFragment(Integer.parseInt(FBPath.PL));

        } else if (id == R.id.nav_bundesliga) {
            startTabsSoccerFragment(Integer.parseInt(FBPath.BL1));

        } else if (id == R.id.nav_la_liga) {
            startTabsSoccerFragment(Integer.parseInt(FBPath.PD));

        } else if (id == R.id.nav_serie_a) {
            startTabsSoccerFragment(Integer.parseInt(FBPath.SA));

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /** Method called from CompetitionInfoFragment that receives the matchDay information
     *  to be used to open FixtureListFragment on tab Fixtures **/
    @Override
    public void onMatchDaySelected(int matchDay) {

        getIntent().putExtra(FixturesListFragment.MATCHDAY, matchDay);

    }

    @Override
    public void onTeamSelected(Standing standing, int position) {

        mTeamDetailFragment = TeamDetailFragment.newInstance(standing);

        try {
            transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.id_content_fragments, mTeamDetailFragment);
            transaction.hide(mSoccerTabFragment);
            transaction.addToBackStack(mSoccerTabFragment.getClass().getName());
            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void showPlayerList(Integer idTeam) {
        Log.i(TAG, "showPlayerList() inside method ID: " + idTeam);

        mPlayerListFragment = PlayerListFragment.newInstance(idTeam);

        try {
            transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.id_content_fragments, mPlayerListFragment);
            transaction.hide(mTeamDetailFragment);
            transaction.addToBackStack(mTeamDetailFragment.getClass().getName());
            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onPlayerSelected(Players players, int position) {
        Log.i(TAG, "onPlayerSelected() inside method " + position);

        mPlayerDetailFragment = PlayerDetailFragment.newInstance(players);

        try {
            transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.id_content_fragments, mPlayerDetailFragment);
            transaction.hide(mPlayerListFragment);
            transaction.addToBackStack(mPlayerListFragment.getClass().getName());
            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showFixtureList(Integer idTeam, String teamCode) {
        Log.i(TAG, "showFixtureList() inside method " + idTeam + ":" + teamCode);

        mFixturesListFragment = FixturesListFragment.newInstance(idTeam);

        getIntent().putExtra(FixturesListFragment.TEAMCODE, teamCode);

        try {
            transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.id_content_fragments, mFixturesListFragment);
            transaction.hide(mTeamDetailFragment);
            transaction.addToBackStack(mTeamDetailFragment.getClass().getName());
            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onFixtureItemSelected(Fixture fixture, int position, String teamCode) {
        Log.i(TAG, "onFixtureItemSelected() inside method");

        mFixtureDetailFragment = FixtureDetailFragment.newInstance(fixture);

        try {
            transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.id_content_fragments, mFixtureDetailFragment);

            if (teamCode != null){

                transaction.hide(mFixturesListFragment);
                transaction.addToBackStack(mFixturesListFragment.getClass().getName());

            } else {

                transaction.hide(mSoccerTabFragment);
                transaction.addToBackStack(mSoccerTabFragment.getClass().getName());

            }

            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
