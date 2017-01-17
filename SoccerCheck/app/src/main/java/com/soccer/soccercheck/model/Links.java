package com.soccer.soccercheck.model;

import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * Created by root on 19/11/16.
 */
public class Links {

    @JsonProperty("self")
    private Self self;

    @JsonProperty("competition")
    private CompetitionLink competition;

    @JsonProperty("homeTeam")
    private HomeTeam homeTeam;

    @JsonProperty("awayTeam")
    private AwayTeam awayTeam;

    @JsonProperty("fixtures")
    private FixtureLink fixtures;

    @JsonProperty("players")
    private Players players;

    @JsonProperty("teams")
    private TeamLink teams;

    @JsonProperty("team")
    private TeamLink team;

    @JsonProperty("leagueTable")
    private LeagueTableLink leagueTable;

    @JsonProperty("self")
    public Self getSelf() {
        return self;
    }

    public void setSelf(Self self) {
        this.self = self;
    }

    @JsonProperty("competition")
    public CompetitionLink getCompetition() {
        return competition;
    }

    public void setCompetition(CompetitionLink competition) {
        this.competition = competition;
    }

    @JsonProperty("homeTeam")
    public HomeTeam getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(HomeTeam homeTeam) {
        this.homeTeam = homeTeam;
    }

    @JsonProperty("awayTeam")
    public AwayTeam getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(AwayTeam awayTeam) {
        this.awayTeam = awayTeam;
    }

    public FixtureLink getFixtures() {
        return fixtures;
    }

    public void setFixtures(FixtureLink fixtures) {
        this.fixtures = fixtures;
    }

    public Players getPlayers() {
        return players;
    }

    public void setPlayers(Players players) {
        this.players = players;
    }

    public TeamLink getTeams() {
        return teams;
    }

    public void setTeams(TeamLink teams) {
        this.teams = teams;
    }

    public TeamLink getTeam() {
        return team;
    }

    public void setTeam(TeamLink team) {
        this.team = team;
    }

    public LeagueTableLink getLeagueTable() {
        return leagueTable;
    }

    public void setLeagueTable(LeagueTableLink leagueTable) {
        this.leagueTable = leagueTable;
    }
}
