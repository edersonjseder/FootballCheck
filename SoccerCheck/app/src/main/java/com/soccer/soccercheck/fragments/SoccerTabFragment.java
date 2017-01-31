package com.soccer.soccercheck.fragments;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.soccer.soccercheck.R;
import com.soccer.soccercheck.adapters.SoccerPagerAdapter;

/**
 * Created by ederson.js on 12/01/2017.
 */

public class SoccerTabFragment extends Fragment {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private SoccerPagerAdapter pagerAdapter;
    private Dialog progress;

    public static SoccerTabFragment newInstance(Integer idCompetition) {

        SoccerTabFragment fragment = new SoccerTabFragment();

        Bundle args = new Bundle();
        args.putInt("IDCOMPETITION", idCompetition);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        progress = new Dialog(getContext(), R.style.CustomProgressBar);
        progress.setContentView(R.layout.component_progress_bar);
        progress.setTitle("Loading...");

        if (savedInstanceState != null)
            return;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        progress.show();

        View v = inflater.inflate(R.layout.fragment_tab_layout_soccer, container, false);

        Bundle args = getArguments();
        int idCompetition = args.getInt("IDCOMPETITION");

        tabLayout = (TabLayout) v.findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText(R.string.title_tab_competition));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.title_tab_league_table));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.title_tab_fixtures_list));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        viewPager = (ViewPager) v.findViewById(R.id.pager);

        pagerAdapter = new SoccerPagerAdapter(getFragmentManager(), tabLayout.getTabCount(), idCompetition, progress);

        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return v;

    }
}
