package com.soccer.soccercheck.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.soccer.soccercheck.R;
import com.soccer.soccercheck.listeners.OnCompetitionsItemSelectedListener;
import com.soccer.soccercheck.listeners.OnItemClickListener;
import com.soccer.soccercheck.model.Competition;
import com.soccer.soccercheck.viewHolders.CompetitionsViewHolder;
import com.soccer.soccercheck.viewHolders.FixturesViewHolder;

import org.joda.time.DateTime;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by ederson.js on 01/11/2016.
 */

public class CompetitionsListAdapter extends RecyclerView.Adapter<CompetitionsViewHolder> {

    private List<Competition> competitionList;
    private Context context;
    private DateTime time;
    private SimpleDateFormat simpleDateFormat;
    private FixturesViewHolder fixturesViewHolder;
    private View.OnClickListener onClickListener;

    public CompetitionsListAdapter(List<Competition> competitionList, Context context) {
        this.competitionList = competitionList;
        this.context = context;
        simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
    }

    @Override
    public CompetitionsViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_league_table_soccer, viewGroup, false);
        CompetitionsViewHolder mCompetitionsViewHolder = new CompetitionsViewHolder(view);
        view.setTag(mCompetitionsViewHolder);

        return mCompetitionsViewHolder;
    }

    @Override
    public void onBindViewHolder(CompetitionsViewHolder competitionsViewHolder, int position) {

        // Gets the position of the item on the List and add the object information
        final Competition competition = competitionList.get(position);

        competitionsViewHolder.getTextViewCompetitionName().setText(competition.getCaption());
        competitionsViewHolder.getTextViewCompetitionYear().setText(competition.getYear());

        // Listener to the card view item to show a detail when is clicked
        competitionsViewHolder.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ((OnCompetitionsItemSelectedListener)context).onCompetitionsItemSelected(competition, position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return (competitionList != null) ? competitionList.size() : 0;
    }

    private String getScore(Object score) {

        if (score != null) {
            return String.valueOf(score);

        } else {
            return " ";

        }

    }

}
