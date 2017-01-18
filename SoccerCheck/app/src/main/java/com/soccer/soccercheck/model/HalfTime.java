package com.soccer.soccercheck.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by root on 19/11/16.
 */

public class HalfTime {

    @JsonProperty("goalsHomeTeam")
    private Object goalsHomeTeam;

    @JsonProperty("goalsAwayTeam")
    private Object goalsAwayTeam;

    public Object getGoalsHomeTeam() {
        return goalsHomeTeam;
    }

    public void setGoalsHomeTeam(Object goalsHomeTeam) {
        this.goalsHomeTeam = goalsHomeTeam;
    }

    public Object getGoalsAwayTeam() {
        return goalsAwayTeam;
    }

    public void setGoalsAwayTeam(Object goalsAwayTeam) {
        this.goalsAwayTeam = goalsAwayTeam;
    }
}
