package com.soccer.soccercheck.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by ederson.js on 13/01/2017.
 */

public class Odds implements Serializable {

    @JsonProperty("homeWin")
    private Integer homeWin;

    @JsonProperty("draw")
    private Integer draw;

    @JsonProperty("awayWin")
    private Integer awayWin;

    public Integer getHomeWin() {
        return homeWin;
    }

    public void setHomeWin(Integer homeWin) {
        this.homeWin = homeWin;
    }

    public Integer getDraw() {
        return draw;
    }

    public void setDraw(Integer draw) {
        this.draw = draw;
    }

    public Integer getAwayWin() {
        return awayWin;
    }

    public void setAwayWin(Integer awayWin) {
        this.awayWin = awayWin;
    }
}
