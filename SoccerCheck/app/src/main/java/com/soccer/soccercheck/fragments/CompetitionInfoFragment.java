package com.soccer.soccercheck.fragments;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.soccer.soccercheck.R;
import com.soccer.soccercheck.factory.DaoFactory;
import com.soccer.soccercheck.factory.DatabaseManager;
import com.soccer.soccercheck.helper.DataBaseHelper;
import com.soccer.soccercheck.listeners.OnPostTaskCompetitionListener;
import com.soccer.soccercheck.listeners.OnPostTaskFixturesListener;
import com.soccer.soccercheck.listeners.OnPostTaskLeagueTableListener;
import com.soccer.soccercheck.listeners.OnSendCurrentMatchDayListener;
import com.soccer.soccercheck.main.SoccerMainActivity;
import com.soccer.soccercheck.model.Competition;
import com.soccer.soccercheck.model.FixturesData;
import com.soccer.soccercheck.model.LeagueTable;
import com.soccer.soccercheck.path.FBPath;
import com.soccer.soccercheck.services.CompetitionsService;
import com.soccer.soccercheck.services.LeagueTableService;
import com.soccer.soccercheck.util.CompetitionInfo;
import com.soccer.soccercheck.util.FixturesInfo;
import com.soccer.soccercheck.util.LeagueTableInfo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class CompetitionInfoFragment extends Fragment implements OnPostTaskLeagueTableListener, OnPostTaskFixturesListener {
    private static final String TAG = "CompetitionInfoFragment";
    private static final String TITLE = "Competition";

    /** Fields View **/
    private ImageView imageCompetitionLogo;
    private TextView competitionName;
    private TextView numberTeamsLabel;
    private TextView numberMatchDaysLabel;
    private TextView numberGamesLabel;
    private TextView numberTeams;
    private TextView numberMatchDays;
    private TextView numberGames;

    private OnPostTaskLeagueTableListener onPostTaskLeagueTableListener;
    private OnPostTaskFixturesListener onPostTaskFixturesListener;

//    private Call<Competition> mCallCompetition;
    private Observable<Competition> mCallCompetition;
    private Call<LeagueTable> mCallLeagueTable;
    private Competition mCompetition;
    private LeagueTableInfo leagueTableConnect;
    private FixturesInfo fixturesConnect;
    private LeagueTable leagueTable;
    private FixturesData fixturesData;

    private DaoFactory daoFactory;

    private Dialog progress;

    // Container Activity must implement this interface
    private OnSendCurrentMatchDayListener onCallBack;

    public static CompetitionInfoFragment newInstance(Integer idCompetition) {

        CompetitionInfoFragment fragment = new CompetitionInfoFragment();

        Bundle args = new Bundle();
        args.putInt("IDCOMPETITION", idCompetition);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate() inside method");

        daoFactory = (DaoFactory) getActivity().getApplication();

        boolean isDbEmpty = true;

        Bundle args = getArguments();
        int idCompetition = args.getInt("IDCOMPETITION");

        Log.i(TAG, "onCreate() ID " + idCompetition);

        if (DatabaseManager.getInstance().getCompetition(idCompetition) != null) {
            Log.i(TAG, "onCreate() inside IF");
            isDbEmpty = false;
        }

        if (savedInstanceState != null)
            return;

        if (savedInstanceState == null) {
            getCompetitionInfo(idCompetition, isDbEmpty);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_competitions_soccer, container, false);

        imageCompetitionLogo = (ImageView) v.findViewById(R.id.id_image_logo);
        competitionName = (TextView) v.findViewById(R.id.competition_name);
        numberTeams = (TextView) v.findViewById(R.id.text_view_number_of_teams);
        numberMatchDays = (TextView) v.findViewById(R.id.text_view_number_of_match_days);
        numberGames = (TextView) v.findViewById(R.id.text_view_number_games);

        numberTeamsLabel = (TextView) v.findViewById(R.id.text_view_number_of_teams_label);
        numberMatchDaysLabel = (TextView) v.findViewById(R.id.text_view_number_of_match_days_label);
        numberGamesLabel = (TextView) v.findViewById(R.id.text_view_number_games_label);

        return v;

    }

    public void getCompetitionInfo(final Integer id, boolean isDbEmpty) {
        Log.i(TAG, "getCompetitionInfo() inside method");

        if (isDbEmpty) {
            Log.i(TAG, "INSIDE IF");

            onPostTaskLeagueTableListener = this;
            onPostTaskFixturesListener = this;

            mCallCompetition = CompetitionsService.Factory.create().fetchRxCompetition(id);

            progress = new Dialog(getContext(), R.style.CustomProgressBar);
            progress.setContentView(R.layout.component_progress_bar);
            progress.setTitle("Loading...");
            progress.show();

            mCallCompetition.subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .map(competition -> {

                        mCompetition = competition;

                        return mCompetition;

                    }).map(mCompetition -> {

                leagueTableConnect = new LeagueTableInfo(onPostTaskLeagueTableListener, id);
                leagueTableConnect.execute();

                return mCompetition;

            }).map(mCompetition -> {

                fixturesConnect = new FixturesInfo(onPostTaskFixturesListener, id);
                fixturesConnect.execute();

                return mCompetition;

            }).map(mCompetition -> {

                DatabaseManager.getInstance().saveLeagueTable(leagueTable);
                DatabaseManager.getInstance().saveFixturesData(fixturesData);
                DatabaseManager.getInstance().saveCompetition(mCompetition);

                return mCompetition;

            }).subscribe(competitions -> showCompetitionsInfo(competitions));



//        mCallCompetition.enqueue(new Callback<Competition>() {
//            @Override
//            public void onResponse(Call<Competition> call, Response<Competition> response) {
//
//                mCompetition = response.body();
//
//                if(mCompetition != null) {
//
//                    showCompetitionsInfo(mCompetition);
//
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<Competition> call, Throwable t) {
//                Log.i(TAG, "onFailure() inside method");
//                t.printStackTrace();
//
//                if (progress.isShowing()){
//                    progress.dismiss();
//                }
//            }
//        });

        } else {
            Log.i(TAG, "INSIDE ELSE");

            Competition competition = DatabaseManager.getInstance().getCompetition(id);

            showCompetitionsInfo(competition);

        }


    }

    private void showCompetitionsInfo(Competition competition) {
        Log.i(TAG, "showCompetitionsInfo() inside method");

        if(competition != null){

            numberTeamsLabel.setVisibility(View.VISIBLE);
            numberMatchDaysLabel.setVisibility(View.VISIBLE);
            numberGamesLabel.setVisibility(View.VISIBLE);

            competitionName.setText(competition.getCaption());
            numberTeams.setText(String.valueOf(competition.getNumberOfTeams()));
            numberMatchDays.setText(String.valueOf(competition.getNumberOfMatchdays()));
            numberGames.setText(String.valueOf(competition.getNumberOfGames()));

            // Verify which championship info is coming
            if(competition.getLeague().equals(FBPath.CHAMPIONSLEAGUE)) {
                Log.i(TAG, "showCompetitionsInfo() competition.getLeague(): " + competition.getLeague() + " FBPath.CL: " + FBPath.CL);

                imageCompetitionLogo.setImageResource(R.drawable.champions_league);

            } else if (competition.getLeague().equals(FBPath.PREMIERELEAGUE)) {
                Log.i(TAG, "showCompetitionsInfo() competition.getLeague(): " + competition.getLeague() + " FBPath.PL: " + FBPath.PL);

                imageCompetitionLogo.setImageResource(R.drawable.premier_league);

            } else if (competition.getLeague().equals(FBPath.BUNDESLIGA)) {
                Log.i(TAG, "showCompetitionsInfo() competition.getLeague(): " + competition.getLeague() + " FBPath.BL1: " + FBPath.BL1);

                imageCompetitionLogo.setImageResource(R.drawable.bundesliga_logo_);

            } else if (competition.getLeague().equals(FBPath.PRIMERADIVISION)) {
                Log.i(TAG, "showCompetitionsInfo() competition.getLeague(): " + competition.getLeague() + " FBPath.PD: " + FBPath.PD);

                imageCompetitionLogo.setImageResource(R.drawable.la_liga);

            } else if (competition.getLeague().equals(FBPath.SERIEA)) {
                Log.i(TAG, "showCompetitionsInfo() competition.getLeague(): " + competition.getLeague() + " FBPath.SA: " + FBPath.SA);

                imageCompetitionLogo.setImageResource(R.drawable.serie_a_logo);

            }

            // code that passes the matchDay parameter to the activity
            onCallBack.onMatchDaySelected(competition.getCurrentMatchday());

        }

    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle(TITLE);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            // This code will make the communication to the activity to pass the matchDay parameter
            onCallBack = (OnSendCurrentMatchDayListener) context;

        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement OnSendCurrentMatchDayListener");
        }
    }

    @Override
    public void onTaskLeagueTableCompleted(LeagueTable leagueTable) {
        Log.i(TAG, "onTaskLeagueTableCompleted() inside method - PARAMETER: " + leagueTable.getLeagueCaption() + " & LOCAL: " + this.leagueTable);
        this.leagueTable = leagueTable;

        Log.i(TAG, "onTaskLeagueTableCompleted() inside method - LOCAL: " + this.leagueTable);

        this.mCompetition.setLeagueTable(this.leagueTable);

    }

    @Override
    public void onTaskFixturesCompleted(FixturesData fixturesData) {
        Log.i(TAG, "onTaskFixturesCompleted() inside method - PARAMETER: " + fixturesData.getCount() + " & LOCAL: " + this.fixturesData);
        this.fixturesData = fixturesData;

        Log.i(TAG, "onTaskFixturesCompleted() inside method - LOCAL: " + this.fixturesData);

        this.mCompetition.setFixturesData(this.fixturesData);

        if (progress.isShowing()){
            Log.i(TAG, "INSIDE IF - PROGRESSBAR");
            progress.dismiss();
        }

    }
}
