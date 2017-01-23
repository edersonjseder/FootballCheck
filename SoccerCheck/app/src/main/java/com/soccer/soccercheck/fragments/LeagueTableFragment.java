package com.soccer.soccercheck.fragments;

import android.graphics.Color;
import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.GenericRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.StreamEncoder;
import com.bumptech.glide.load.resource.file.FileToStreamDecoder;
import com.caverock.androidsvg.SVG;
import com.soccer.soccercheck.R;
import com.soccer.soccercheck.adapters.ChampionsLeagueTableAdapter;
import com.soccer.soccercheck.adapters.LeagueTableAdapter;
import com.soccer.soccercheck.model.ChampionsLeagueTable;
import com.soccer.soccercheck.model.LeagueTable;
import com.soccer.soccercheck.model.StandingChampionsLeague;
import com.soccer.soccercheck.path.FBPath;
import com.soccer.soccercheck.services.LeagueTableService;
import com.soccer.soccercheck.util.SeparatorDecoration;
import com.soccer.soccercheck.util.SvgDecoder;
import com.soccer.soccercheck.util.SvgDrawableTranscoder;
import com.soccer.soccercheck.util.SvgSoftwareLayerSetter;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LeagueTableFragment extends Fragment {
    private static final String TAG = "LeagueTableFragment";
    private static final String TITLE = "Table";

    private RecyclerView recyclerViewLeagueTable;
    private LeagueTableAdapter leagueTableAdapter;

    private ChampionsLeagueTableAdapter championsLeagueTableAdapter;

    private Call<LeagueTable> mCallLeagueTable;
    private Call<ChampionsLeagueTable> mCallChampionsLeagueTable;

    private int idCompetition;

    private GenericRequestBuilder<Uri, InputStream, SVG, PictureDrawable> requestBuilder;

    public static LeagueTableFragment newInstance(Integer idCompetition) {
        Log.i(TAG, "newInstance() inside method " + idCompetition);

        LeagueTableFragment fragment = new LeagueTableFragment();

        Bundle args = new Bundle();
        args.putInt("IDCOMPETITION", idCompetition);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate() inside method");

        Bundle args = getArguments();
        idCompetition = args.getInt("IDCOMPETITION");

        Log.i(TAG, "onCreate() inside method " + idCompetition);

        if (savedInstanceState != null)
            return;

        if (savedInstanceState == null) {
            if (idCompetition == Integer.parseInt(FBPath.CL)) {

                getChampionsLeagueTable(idCompetition);

            } else {

                getLeagueTable(idCompetition);

            }

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view;

        if(idCompetition == Integer.parseInt(FBPath.CL)) {

            view = inflater.inflate(R.layout.fragment_champions_league_table_soccer, container, false);

            recyclerViewLeagueTable = (RecyclerView) view.findViewById(R.id.recycler_view_champions_league_table_group);
            recyclerViewLeagueTable.setHasFixedSize(true);
            recyclerViewLeagueTable.setLayoutManager(new LinearLayoutManager(getActivity()));

            SeparatorDecoration decoration = new SeparatorDecoration(getContext(), Color.BLUE, 1.5f);

            recyclerViewLeagueTable.addItemDecoration(decoration);

            requestBuilder = Glide.with(getActivity())
                    .using(Glide.buildStreamModelLoader(Uri.class, getActivity()), InputStream.class)
                    .from(Uri.class)
                    .as(SVG.class)
                    .transcode(new SvgDrawableTranscoder(), PictureDrawable.class)
                    .sourceEncoder(new StreamEncoder())
                    .cacheDecoder(new FileToStreamDecoder<SVG>(new SvgDecoder()))
                    .decoder(new SvgDecoder())
                    .listener(new SvgSoftwareLayerSetter<Uri>());

        } else {

            view = inflater.inflate(R.layout.fragment_league_table_soccer, container, false);

            recyclerViewLeagueTable = (RecyclerView) view.findViewById(R.id.recycler_view_league_table_id);
            recyclerViewLeagueTable.setHasFixedSize(true);
            recyclerViewLeagueTable.setLayoutManager(new LinearLayoutManager(getActivity()));

            requestBuilder = Glide.with(getActivity())
                    .using(Glide.buildStreamModelLoader(Uri.class, getActivity()), InputStream.class)
                    .from(Uri.class)
                    .as(SVG.class)
                    .transcode(new SvgDrawableTranscoder(), PictureDrawable.class)
                    .sourceEncoder(new StreamEncoder())
                    .cacheDecoder(new FileToStreamDecoder<SVG>(new SvgDecoder()))
                    .decoder(new SvgDecoder())
                    .listener(new SvgSoftwareLayerSetter<Uri>());

        }

        return view;

    }

    public void getLeagueTable(Integer id) {
        Log.i(TAG, "getFixturesData() inside method " + id);

        mCallLeagueTable = LeagueTableService.Factory.create().fetchLeagueTable(id);

        mCallLeagueTable.enqueue(new Callback<LeagueTable>() {
            @Override
            public void onResponse(Call<LeagueTable> call, Response<LeagueTable> response) {

                LeagueTable mLeagueTable = response.body();

                if(mLeagueTable != null) {

                    showLeagueTable(mLeagueTable);

                }

            }

            @Override
            public void onFailure(Call<LeagueTable> call, Throwable t) {
                Log.i(TAG, "onFailure() inside method");
                t.printStackTrace();
            }
        });

    }

    public void getChampionsLeagueTable(Integer id) {
        Log.i(TAG, "getChampionsLeagueTable() inside method " + id);

        mCallChampionsLeagueTable = LeagueTableService.Factory.create().fetchChampionsLeagueTable(id);

        mCallChampionsLeagueTable.enqueue(new Callback<ChampionsLeagueTable>() {
            @Override
            public void onResponse(Call<ChampionsLeagueTable> call, Response<ChampionsLeagueTable> response) {

                ChampionsLeagueTable championsLeagueTable = response.body();

                if(championsLeagueTable != null) {

                    showChampionsLeagueTable(championsLeagueTable);

                }

            }

            @Override
            public void onFailure(Call<ChampionsLeagueTable> call, Throwable t) {

            }
        });

    }

    private void showLeagueTable(LeagueTable leagueTable) {
        Log.i(TAG, "showLeagueTable() inside method");

        if(leagueTable != null){

            leagueTableAdapter = new LeagueTableAdapter(leagueTable, getContext(), requestBuilder);
            recyclerViewLeagueTable.setAdapter(leagueTableAdapter);

        }

    }

    private void showChampionsLeagueTable(ChampionsLeagueTable championsLeagueTable) {
        Log.i(TAG, "showChampionsLeagueTable() inside method");

        if(championsLeagueTable != null){

            List<StandingChampionsLeague> championsLeagueGroupList = new ArrayList<StandingChampionsLeague>();

            for (int i = 0; i < championsLeagueTable.getStandings().getGroups().size(); i++) {

                for (int j = 0; j < championsLeagueTable.getStandings().getGroups().get(i).size(); j++) {

                    championsLeagueGroupList.add(championsLeagueTable.getStandings().getGroups().get(i).get(j));

                }
            }

            championsLeagueTableAdapter = new ChampionsLeagueTableAdapter(championsLeagueGroupList, getContext(), requestBuilder);
            recyclerViewLeagueTable.setAdapter(championsLeagueTableAdapter);

        }

    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle(TITLE);
    }
}
