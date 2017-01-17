package com.soccer.soccercheck.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.soccer.soccercheck.R;
import com.soccer.soccercheck.model.Competition;
import com.soccer.soccercheck.model.Team;
import com.soccer.soccercheck.services.CompetitionsService;
import com.soccer.soccercheck.services.TeamService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by root on 16/01/17.
 */

public class TeamDetailFragment extends Fragment {
    private static final String TAG = "TeamDetailFragment";

    public static final String TEAM = "team";

    private ImageView imageTeamLogo;
    private TextView textViewNameTeam;
    private TextView textViewShortNameTeam;
    private TextView textViewCodeTeam;
    private TextView textViewSquadMarketValueTeam;

    private TextView textViewNameTeamLabel;
    private TextView textViewShortNameTeamLabel;
    private TextView textViewCodeTeamLabel;
    private TextView textViewSquadMarketValueTeamLabel;

    private Call<Team> mCallTeam;

    private Team mTeam;

    public static TeamDetailFragment newInstance(Team team) {
        Log.i(TAG, "newInstance() inside method");

        TeamDetailFragment fragment = new TeamDetailFragment();

        Bundle args = new Bundle();
        args.putSerializable(TEAM, team);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.i(TAG, "onCreate() inside method");

        Bundle args = getArguments();
        mTeam = (Team) args.getSerializable(TEAM);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_team_detail_soccer, container, false);

        imageTeamLogo = (ImageView) v.findViewById(R.id.id_image_team_logo);

        textViewNameTeam = (TextView) v.findViewById(R.id.textview_team_name);
        textViewShortNameTeam = (TextView) v.findViewById(R.id.textview_short_name_team);
        textViewCodeTeam = (TextView) v.findViewById(R.id.textview_code_team);
        textViewSquadMarketValueTeam = (TextView) v.findViewById(R.id.textview_squad_market_value_team);

        textViewNameTeamLabel = (TextView) v.findViewById(R.id.textview_team_name_label);
        textViewShortNameTeamLabel = (TextView) v.findViewById(R.id.textview_short_name_team_label);
        textViewCodeTeamLabel = (TextView) v.findViewById(R.id.textview_code_team_label);
        textViewSquadMarketValueTeamLabel = (TextView) v.findViewById(R.id.textview_squad_market_value_team_label);

        return v;
    }

    public void getCompetitionInfo(Integer id) {

        mCallTeam = TeamService.Factory.create().getTeam(id);

        mCallTeam.enqueue(new Callback<Team>() {
            @Override
            public void onResponse(Call<Team> call, Response<Team> response) {

                mTeam = response.body();

                if(mTeam != null) {

                    showTeamInfo(mTeam);

                }

            }

            @Override
            public void onFailure(Call<Team> call, Throwable t) {
                Log.i(TAG, "onFailure() inside method");
                t.printStackTrace();
            }
        });

    }

    private void showTeamInfo(Team mTeam) {
        Log.i(TAG, "showTeamInfo() inside method");

    }
}
