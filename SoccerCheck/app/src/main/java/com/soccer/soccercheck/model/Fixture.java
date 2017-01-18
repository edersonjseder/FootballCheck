package com.soccer.soccercheck.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by root on 19/11/16.
 */
public class Fixture {

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

    @JsonProperty("_links")
    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    @JsonProperty("date")
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty("matchday")
    public Integer getMatchday() {
        return matchday;
    }

    public void setMatchday(Integer matchday) {
        this.matchday = matchday;
    }

    @JsonProperty("homeTeamName")
    public String getHomeTeamName() {
        return homeTeamName;
    }

    public void setHomeTeamName(String homeTeamName) {
        this.homeTeamName = homeTeamName;
    }

    @JsonProperty("awayTeamName")
    public String getAwayTeamName() {
        return awayTeamName;
    }

    public void setAwayTeamName(String awayTeamName) {
        this.awayTeamName = awayTeamName;
    }

    @JsonProperty("result")
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