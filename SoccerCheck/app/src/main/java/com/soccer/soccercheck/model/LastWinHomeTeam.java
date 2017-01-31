package com.soccer.soccercheck.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by ederson.js on 24/01/2017.
 */
public class LastWinHomeTeam implements Serializable {

    private final static long serialVersionUID = 4893155582093550006L;

    @JsonProperty("_links")
    private Links links;

    @JsonProperty("date")
    private String date;

    @JsonProperty("status")
    private String status;

    @JsonProperty("matchday")
    private Integer matchday;

    @JsonProperty("homeTeamName")
    private String homeTeamName;

    @JsonProperty("awayTeamName")
    private String awayTeamName;

    @JsonProperty("result")
    private Result result;

    @JsonProperty("odds")
    private Odds odds;


    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getMatchday() {
        return matchday;
    }

    public void setMatchday(Integer matchday) {
        this.matchday = matchday;
    }

    public String getHomeTeamName() {
        return homeTeamName;
    }

    public void setHomeTeamName(String homeTeamName) {
        this.homeTeamName = homeTeamName;
    }

    public String getAwayTeamName() {
        return awayTeamName;
    }

    public void setAwayTeamName(String awayTeamName) {
        this.awayTeamName = awayTeamName;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public Odds getOdds() {
        return odds;
    }

    public void setOdds(Odds odds) {
        this.odds = odds;
    }
}
