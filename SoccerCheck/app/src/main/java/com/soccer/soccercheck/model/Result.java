package com.soccer.soccercheck.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by root on 19/11/16.
 */
public class Result {

    @JsonProperty("goalsHomeTeam")
    private Object goalsHomeTeam;

    @JsonProperty("goalsAwayTeam")
    private Object goalsAwayTeam;

    @JsonProperty("halfTime")
    private HalfTime halfTime;

    public HalfTime getHalfTime() {
        return halfTime;
    }

    public void setHalfTime(HalfTime halfTime) {
        this.halfTime = halfTime;
    }

    @JsonProperty("goalsHomeTeam")
    public Object getGoalsHomeTeam() {
        return goalsHomeTeam;
    }

    public void setGoalsHomeTeam(Object goalsHomeTeam) {
        this.goalsHomeTeam = goalsHomeTeam;
    }

    @JsonProperty("goalsAwayTeam")
    public Object getGoalsAwayTeam() {
        return goalsAwayTeam;
    }

    public void setGoalsAwayTeam(Object goalsAwayTeam) {
        this.goalsAwayTeam = goalsAwayTeam;
    }

}
