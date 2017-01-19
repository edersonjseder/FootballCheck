package com.soccer.soccercheck.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ederson.js on 21/11/2016.
 */

public class ChampionsLeagueTable {

    private final static long serialVersionUID = 6559904913700087644L;

    @JsonProperty("leagueCaption")
    private String leagueCaption;

    @JsonProperty("matchday")
    private Integer matchday;

    @JsonProperty("standings")
    private Standings standings;

    public String getLeagueCaption() {
        return leagueCaption;
    }

    public void setLeagueCaption(String leagueCaption) {
        this.leagueCaption = leagueCaption;
    }

    public Integer getMatchday() {
        return matchday;
    }

    public void setMatchday(Integer matchday) {
        this.matchday = matchday;
    }

    public Standings getStandings() {
        return standings;
    }

    public void setStandings(Standings standings) {
        this.standings = standings;
    }
}
