package com.soccer.soccercheck.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ederson.js on 21/11/2016.
 */

public class LeagueTable {

    @JsonProperty("_links")
    private Links links;

    @JsonProperty("leagueCaption")
    private String leagueCaption;

    @JsonProperty("matchday")
    private Integer matchday;

    @JsonProperty("standing")
    private List<Standing> standing = new ArrayList<Standing>();

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

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

    public List<Standing> getStanding() {
        return standing;
    }

    public void setStanding(List<Standing> standing) {
        this.standing = standing;
    }
}
