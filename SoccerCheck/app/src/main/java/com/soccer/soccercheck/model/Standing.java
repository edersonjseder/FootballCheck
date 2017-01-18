package com.soccer.soccercheck.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by ederson.js on 21/11/2016.
 */

public class Standing implements Serializable {

    @JsonProperty("_links")
    private Links links;

    @JsonProperty("position")
    private Integer position;

    @JsonProperty("teamName")
    private String teamName;

    @JsonProperty("crestURI")
    private String crestURI;

    @JsonProperty("playedGames")
    private Integer playedGames;

    @JsonProperty("points")
    private Integer points;

    @JsonProperty("goals")
    private Integer goals;

    @JsonProperty("goalsAgainst")
    private Integer goalsAgainst;

    @JsonProperty("goalDifference")
    private Integer goalDifference;

    @JsonProperty("wins")
    private Integer wins;

    @JsonProperty("draws")
    private Integer draws;

    @JsonProperty("losses")
    private Integer losses;

    @JsonProperty("home")
    private Home home;

    @JsonProperty("away")
    private Away away;

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getCrestURI() {
        return crestURI;
    }

    public void setCrestURI(String crestURI) {
        this.crestURI = crestURI;
    }

    public Integer getPlayedGames() {
        return playedGames;
    }

    public void setPlayedGames(Integer playedGames) {
        this.playedGames = playedGames;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Integer getGoals() {
        return goals;
    }

    public void setGoals(Integer goals) {
        this.goals = goals;
    }

    public Integer getGoalsAgainst() {
        return goalsAgainst;
    }

    public void setGoalsAgainst(Integer goalsAgainst) {
        this.goalsAgainst = goalsAgainst;
    }

    public Integer getGoalDifference() {
        return goalDifference;
    }

    public void setGoalDifference(Integer goalDifference) {
        this.goalDifference = goalDifference;
    }

    public Integer getWins() {
        return wins;
    }

    public void setWins(Integer wins) {
        this.wins = wins;
    }

    public Integer getDraws() {
        return draws;
    }

    public void setDraws(Integer draws) {
        this.draws = draws;
    }

    public Integer getLosses() {
        return losses;
    }

    public void setLosses(Integer losses) {
        this.losses = losses;
    }

    public Home getHome() {
        return home;
    }

    public void setHome(Home home) {
        this.home = home;
    }

    public Away getAway() {
        return away;
    }

    public void setAway(Away away) {
        this.away = away;
    }
}
