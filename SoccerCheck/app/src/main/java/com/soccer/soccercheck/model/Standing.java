package com.soccer.soccercheck.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by ederson.js on 21/11/2016.
 */
@DatabaseTable(tableName = "standing")
public class Standing implements Serializable {

    @JsonProperty("_links")
    private Links links;

    @JsonIgnore
    @DatabaseField(id = true, generatedId = true)
    private Integer id;

    @JsonProperty("position")
    @DatabaseField
    private Integer position;

    @JsonProperty("teamName")
    @DatabaseField(columnName = "team_name")
    private String teamName;

    @JsonProperty("crestURI")
    @DatabaseField(columnName = "crest_uri")
    private String crestURI;

    @JsonProperty("playedGames")
    @DatabaseField(columnName = "played_games")
    private Integer playedGames;

    @JsonProperty("points")
    @DatabaseField
    private Integer points;

    @JsonProperty("goals")
    @DatabaseField
    private Integer goals;

    @JsonProperty("goalsAgainst")
    @DatabaseField(columnName = "goals_against")
    private Integer goalsAgainst;

    @JsonProperty("goalDifference")
    @DatabaseField(columnName = "goal_difference")
    private Integer goalDifference;

    @JsonProperty("wins")
    @DatabaseField
    private Integer wins;

    @JsonProperty("draws")
    @DatabaseField
    private Integer draws;

    @JsonProperty("losses")
    @DatabaseField
    private Integer losses;

    @JsonProperty("home")
    @DatabaseField(columnName = "home_id", foreign = true, foreignAutoCreate = true, foreignAutoRefresh = true)
    private Home home;

    @JsonProperty("away")
    @DatabaseField(columnName = "away_id", foreign = true, foreignAutoCreate = true, foreignAutoRefresh = true)
    private Away away;

    @JsonIgnore
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private LeagueTable leagueTable;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LeagueTable getLeagueTable() {
        return leagueTable;
    }

    public void setLeagueTable(LeagueTable leagueTable) {
        this.leagueTable = leagueTable;
    }

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
