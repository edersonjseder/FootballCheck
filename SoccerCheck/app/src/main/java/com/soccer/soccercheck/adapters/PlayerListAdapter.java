package com.soccer.soccercheck.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.soccer.soccercheck.R;
import com.soccer.soccercheck.listeners.OnItemClickListener;
import com.soccer.soccercheck.listeners.OnPlayerSelectedListener;
import com.soccer.soccercheck.model.PlayerList;
import com.soccer.soccercheck.model.Players;
import com.soccer.soccercheck.viewHolders.PlayersListViewHolder;

import java.util.List;

/**
 * Created by ederson.js on 01/11/2016.
 */

public class PlayerListAdapter extends RecyclerView.Adapter<PlayersListViewHolder> {

    private List<Players> playersList;
    private Context context;
    private Players mPlayers;

    public PlayerListAdapter(PlayerList playerList, Context context) {
        this.playersList = playerList.getPlayers();
        this.context = context;
    }

    @Override
    public PlayersListViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.component_card_view_player_list, viewGroup, false);
        PlayersListViewHolder playersListViewHolder = new PlayersListViewHolder(view);
        view.setTag(playersListViewHolder);

        return playersListViewHolder;
    }

    @Override
    public void onBindViewHolder(PlayersListViewHolder playersListViewHolder, int position) {

        // Gets the position of the item on the List and add the object information
        mPlayers = playersList.get(position);

        Integer playerNumber = mPlayers.getJerseyNumber();

        if(playerNumber != null){

            playersListViewHolder.getTextViewPlayerJerseyNumber().setText(playerNumber.toString());

        } else {

            playersListViewHolder.getTextViewPlayerJerseyNumber().setText("N/A");

        }

        playersListViewHolder.getTextViewPlayerName().setText(mPlayers.getName());
        playersListViewHolder.getTextViewPlayerPosition().setText(mPlayers.getPosition());

        // Listener to the card view item to show a detail when is clicked
        playersListViewHolder.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ((OnPlayerSelectedListener)context).onPlayerSelected(mPlayers, position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return (playersList != null) ? playersList.size() : 0;
    }

}
