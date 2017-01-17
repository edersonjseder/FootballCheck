package com.soccer.soccercheck.viewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.soccer.soccercheck.R;
import com.soccer.soccercheck.listeners.OnItemClickListener;


/**
 * Created by root on 01/11/16.
 */

public class CompetitionsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private TextView textViewCompetitionName;
    private TextView textViewCompetitionYear;
    private OnItemClickListener onItemClickListener;

    public CompetitionsViewHolder(View itemView) {
        super(itemView);

//        textViewCompetitionName = (TextView) itemView.findViewById(R.id.textview_position);
//        textViewCompetitionYear = (TextView) itemView.findViewById(R.id.textview_competition_year);

        itemView.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        onItemClickListener.onItemClick(v, getLayoutPosition());
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public TextView getTextViewCompetitionName() {
        return textViewCompetitionName;
    }

    public void setTextViewCompetitionName(TextView textViewCompetitionName) {
        this.textViewCompetitionName = textViewCompetitionName;
    }

    public TextView getTextViewCompetitionYear() {
        return textViewCompetitionYear;
    }

    public void setTextViewCompetitionYear(TextView textViewCompetitionYear) {
        this.textViewCompetitionYear = textViewCompetitionYear;
    }
}
