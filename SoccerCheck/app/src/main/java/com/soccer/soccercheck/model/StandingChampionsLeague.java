package com.soccer.soccercheck.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by ederson.js on 21/11/2016.
 */

public class StandingChampionsLeague implements Serializable {

    private final static long serialVersionUID = 2349255423661401949L;

    @JsonProperty("group")
    private String group;

    @JsonProperty("rank")
    private Integer rank;

    @JsonProperty("team")
    private String team;

    @JsonProperty("teamId")
    private Integer teamId;

    @JsonProperty("playedGames")
    private Integer playedGames;

    @JsonProperty("crestURI")
    private String crestURI;

    @JsonProperty("points")
    private Integer points;

    @JsonProperty("goals")
    private Integer goals;

    @JsonProperty("goalsAgainst")
    private Integer goalsAgainst;

    @JsonProperty("goalDifference")
    private Integer goalDifference;

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public Integer getPlayedGames() {
        return playedGames;
    }

    public void setPlayedGames(Integer playedGames) {
        this.playedGames = playedGames;
    }

    public String getCrestURI() {
        return crestURI;
    }

    public void setCrestURI(String crestURI) {
        this.crestURI = crestURI;
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
}
