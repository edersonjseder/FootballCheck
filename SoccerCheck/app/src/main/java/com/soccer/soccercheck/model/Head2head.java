package com.soccer.soccercheck.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ederson.js on 24/01/2017.
 */
public class Head2head implements Serializable {

    private final static long serialVersionUID = -5435975426967288506L;

    @JsonProperty("count")
    private Integer count;

    @JsonProperty("timeFrameStart")
    private String timeFrameStart;

    @JsonProperty("timeFrameEnd")
    private String timeFrameEnd;

    @JsonProperty("homeTeamWins")
    private Integer homeTeamWins;

    @JsonProperty("awayTeamWins")
    private Integer awayTeamWins;

    @JsonProperty("draws")
    private Integer draws;

    @JsonProperty("lastHomeWinHomeTeam")
    private LastHomeWinHomeTeam lastHomeWinHomeTeam;

    @JsonProperty("lastWinHomeTeam")
    private LastWinHomeTeam lastWinHomeTeam;

    @JsonProperty("lastAwayWinAwayTeam")
    private LastAwayWinAwayTeam lastAwayWinAwayTeam;

    @JsonProperty("lastWinAwayTeam")
    private LastWinAwayTeam lastWinAwayTeam;

    @JsonProperty("fixtures")
    private List<Fixture> fixtures = null;

    /** Getters and Setters **/
    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getTimeFrameStart() {
        return timeFrameStart;
    }

    public void setTimeFrameStart(String timeFrameStart) {
        this.timeFrameStart = timeFrameStart;
    }

    public String getTimeFrameEnd() {
        return timeFrameEnd;
    }

    public void setTimeFrameEnd(String timeFrameEnd) {
        this.timeFrameEnd = timeFrameEnd;
    }

    public Integer getHomeTeamWins() {
        return homeTeamWins;
    }

    public void setHomeTeamWins(Integer homeTeamWins) {
        this.homeTeamWins = homeTeamWins;
    }

    public Integer getAwayTeamWins() {
        return awayTeamWins;
    }

    public void setAwayTeamWins(Integer awayTeamWins) {
        this.awayTeamWins = awayTeamWins;
    }

    public Integer getDraws() {
        return draws;
    }

    public void setDraws(Integer draws) {
        this.draws = draws;
    }

    public LastHomeWinHomeTeam getLastHomeWinHomeTeam() {
        return lastHomeWinHomeTeam;
    }

    public void setLastHomeWinHomeTeam(LastHomeWinHomeTeam lastHomeWinHomeTeam) {
        this.lastHomeWinHomeTeam = lastHomeWinHomeTeam;
    }

    public LastWinHomeTeam getLastWinHomeTeam() {
        return lastWinHomeTeam;
    }

    public void setLastWinHomeTeam(LastWinHomeTeam lastWinHomeTeam) {
        this.lastWinHomeTeam = lastWinHomeTeam;
    }

    public LastAwayWinAwayTeam getLastAwayWinAwayTeam() {
        return lastAwayWinAwayTeam;
    }

    public void setLastAwayWinAwayTeam(LastAwayWinAwayTeam lastAwayWinAwayTeam) {
        this.lastAwayWinAwayTeam = lastAwayWinAwayTeam;
    }

    public LastWinAwayTeam getLastWinAwayTeam() {
        return lastWinAwayTeam;
    }

    public void setLastWinAwayTeam(LastWinAwayTeam lastWinAwayTeam) {
        this.lastWinAwayTeam = lastWinAwayTeam;
    }

    public List<Fixture> getFixtures() {
        return fixtures;
    }

    public void setFixtures(List<Fixture> fixtures) {
        this.fixtures = fixtures;
    }
}
