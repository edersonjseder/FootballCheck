package com.soccer.soccercheck.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by root on 19/11/16.
 */
@DatabaseTable(tableName = "fixture")
public class Fixture implements Serializable {

    @JsonProperty("_links")
    private Links links;

    @JsonIgnore
    @DatabaseField(id = true, generatedId = true)
    private Integer id;

    @JsonProperty("date")
    @DatabaseField
    private String date;

    @JsonProperty("status")
    @DatabaseField
    private String status;

    @JsonProperty("matchday")
    @DatabaseField
    private Integer matchday;

    @JsonProperty("homeTeamName")
    @DatabaseField(columnName = "home_team_name")
    private String homeTeamName;

    @JsonProperty("awayTeamName")
    @DatabaseField(columnName = "away_team_name")
    private String awayTeamName;

    @JsonProperty("result")
    @DatabaseField(columnName = "result_id", foreign = true, foreignAutoCreate = true, foreignAutoRefresh = true)
    private Result result;

    @JsonProperty("odds")
    @DatabaseField(columnName = "odds_id", foreign = true, foreignAutoCreate = true, foreignAutoRefresh = true)
    private Odds odds;

    @JsonIgnore
    @DatabaseField(columnName = "team_id", foreign = true, foreignAutoCreate = true, foreignAutoRefresh = true)
    private Team homeTeam;

    @JsonIgnore
    @DatabaseField(columnName = "team_id", foreign = true, foreignAutoCreate = true, foreignAutoRefresh = true)
    private Team awayTeam;

    @JsonIgnore
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private FixturesData fixturesData;

    @JsonIgnore
    private String competitionName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public FixturesData getFixturesData() {
        return fixturesData;
    }

    public void setFixturesData(FixturesData fixturesData) {
        this.fixturesData = fixturesData;
    }

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

    public Team getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
    }

    public String getCompetitionName() {
        return competitionName;
    }

    public void setCompetitionName(String competitionName) {
        this.competitionName = competitionName;
    }
}