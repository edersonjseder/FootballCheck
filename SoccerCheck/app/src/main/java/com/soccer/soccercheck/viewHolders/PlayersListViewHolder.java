package com.soccer.soccercheck.viewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.soccer.soccercheck.R;
import com.soccer.soccercheck.listeners.OnItemClickListener;

/**
 * Created by root on 01/11/16.
 */

public class PlayersListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private TextView textViewPlayerJerseyNumber;
    private TextView textViewPlayerName;
    private TextView textViewPlayerPosition;

    private OnItemClickListener onItemClickListener;

    public PlayersListViewHolder(View itemView) {
        super(itemView);

        textViewPlayerJerseyNumber = (TextView) itemView.findViewById(R.id.textview_player_list_number);
        textViewPlayerName = (TextView) itemView.findViewById(R.id.textview_player_list_name);
        textViewPlayerPosition = (TextView) itemView.findViewById(R.id.textview_player_list_position);

        itemView.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        onItemClickListener.onItemClick(v, getLayoutPosition());
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public TextView getTextViewPlayerJerseyNumber() {
        return textViewPlayerJerseyNumber;
    }

    public TextView getTextViewPlayerName() {
        return textViewPlayerName;
    }

    public TextView getTextViewPlayerPosition() {
        return textViewPlayerPosition;
    }

}
