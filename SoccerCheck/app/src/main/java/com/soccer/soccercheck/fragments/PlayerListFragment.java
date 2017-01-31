package com.soccer.soccercheck.fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.soccer.soccercheck.R;
import com.soccer.soccercheck.adapters.PlayerListAdapter;
import com.soccer.soccercheck.model.PlayerList;
import com.soccer.soccercheck.services.PlayersService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ederson.js on 20/01/2017.
 */

public class PlayerListFragment extends Fragment {
    private static final String TAG = "PlayerListFragment";

    public static final String IDTEAM = "idTeam";

    private RecyclerView recyclerViewPlayerList;
    private PlayerListAdapter playerListAdapter;

    private Call<PlayerList> mCallPlayerList;

    private Dialog progress;

    public static PlayerListFragment newInstance(Integer idTeam) {
        Log.i(TAG, "newInstance() inside method " + idTeam);

        PlayerListFragment fragment = new PlayerListFragment();

        Bundle args = new Bundle();
        args.putInt(IDTEAM, idTeam);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate() inside method");

        Bundle args = getArguments();
        int idTeam = args.getInt(IDTEAM);

        progress = new Dialog(getContext(), R.style.CustomProgressBar);
        progress.setContentView(R.layout.component_progress_bar);
        progress.setTitle("Loading...");

        Log.i(TAG, "IDTEAM " + idTeam);

        if (savedInstanceState != null)
            return;

        if (savedInstanceState == null) {
            getPlayerList(idTeam);

        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        progress.show();

        View view = inflater.inflate(R.layout.fragment_player_list_soccer, container, false);

        recyclerViewPlayerList = (RecyclerView) view.findViewById(R.id.id_recycler_view_player_list);
        recyclerViewPlayerList.setHasFixedSize(true);
        recyclerViewPlayerList.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }

    public void getPlayerList(Integer id) {
        Log.i(TAG, "getPlayerList() inside method " + id);

        mCallPlayerList = PlayersService.Factory.create().getPlayersList(id);

        mCallPlayerList.enqueue(new Callback<PlayerList>() {
            @Override
            public void onResponse(Call<PlayerList> call, Response<PlayerList> response) {

                PlayerList playerList = response.body();

                if (playerList != null) {

                    showPlayerList(playerList);

                }

                if (progress.isShowing()){
                    progress.dismiss();
                }
            }

            @Override
            public void onFailure(Call<PlayerList> call, Throwable t) {
                Log.e(TAG, "onFailure() inside method");
                t.printStackTrace();

                if (progress.isShowing()){
                    progress.dismiss();
                }
            }
        });
    }

    public void showPlayerList(PlayerList playerList) {
        Log.i(TAG, "showPlayerList() inside method");

        if(playerList != null){

            playerListAdapter = new PlayerListAdapter(playerList, getContext());
            recyclerViewPlayerList.setAdapter(playerListAdapter);

        }
    }
}
