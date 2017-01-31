package com.soccer.soccercheck.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.soccer.soccercheck.R;
import com.soccer.soccercheck.listeners.OnFixtureItemSelectedListener;
import com.soccer.soccercheck.listeners.OnItemClickListener;
import com.soccer.soccercheck.model.Fixture;
import com.soccer.soccercheck.model.FixturesData;
import com.soccer.soccercheck.viewHolders.FixturesViewHolder;

import org.joda.time.DateTime;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ederson.js on 01/11/2016.
 */

public class FixturesListAdapter extends RecyclerView.Adapter<FixturesViewHolder> {
    private static final String TAG = "FixturesListAdapter";

    private FixturesData fixturesData;
    private Context context;
    private DateTime time;
    private SimpleDateFormat simpleDateFormat;
    private String teamCode;

    public FixturesListAdapter(FixturesData fixturesData, Context context, String teamCode) {
        this.fixturesData = fixturesData;
        this.context = context;
        simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        this.teamCode = teamCode;
    }

    @Override
    public FixturesViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.component_card_view_fixtures_list, viewGroup, false);
        FixturesViewHolder fixturesViewHolder = new FixturesViewHolder(view);
        view.setTag(fixturesViewHolder);

        return fixturesViewHolder;
    }

    @Override
    public void onBindViewHolder(FixturesViewHolder fixturesViewHolder, int position) {
        Log.i(TAG, "onBindViewHolder() inside method");

        // Gets the position of the item on the List and add the object information
        final Fixture fixture = fixturesData.getFixtures().get(position);
//        fixturesViewHolder.getImageViewAwayTeam().setImageBitmap(car.getCarImageByte());

        time = new DateTime(fixture.getDate());
        // get date in Millis
        long value = time.getMillis();

        // Create an object Date with millis as a parameter to convert date to the common pattern
        Date mDate = new Date(value);

        // Formata a data para o padrão "comum"
        String dateConverted = simpleDateFormat.format(mDate);

        fixturesViewHolder.getTextViewFixtureDate().setText(dateConverted);
        fixturesViewHolder.getTextViewFixtureStatus().setText(fixture.getStatus());

        fixturesViewHolder.getTextViewAwayTeamName().setText(fixture.getAwayTeamName());
        fixturesViewHolder.getTextViewScoreAwayTeam().setText(getScore(fixture.getResult().getGoalsAwayTeam()));

        fixturesViewHolder.getTextViewHomeTeamName().setText(fixture.getHomeTeamName());
        fixturesViewHolder.getTextViewScoreHomeTeam().setText(getScore(fixture.getResult().getGoalsHomeTeam()));

        // Listener to the card view item to show a detail when is clicked
        fixturesViewHolder.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ((OnFixtureItemSelectedListener)context).onFixtureItemSelected(fixture, position, teamCode);
            }
        });

    }

    @Override
    public int getItemCount() {
        return (fixturesData != null) ? fixturesData.getFixtures().size() : 0;
    }

    // Converting the score to String and show it on the view
    private String getScore(Object score) {

        if (score != null) {
            return String.valueOf(score);

        } else {
            return " ";

        }

    }

}
