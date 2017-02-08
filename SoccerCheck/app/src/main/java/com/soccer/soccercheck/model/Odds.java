package com.soccer.soccercheck.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by ederson.js on 13/01/2017.
 */
@DatabaseTable(tableName = "odds")
public class Odds implements Serializable {

    @JsonIgnore
    @DatabaseField(id = true, generatedId = true)
    private Integer id;

    @JsonProperty("homeWin")
    @DatabaseField(columnName = "home_win")
    private Integer homeWin;

    @JsonProperty("draw")
    @DatabaseField
    private Integer draw;

    @JsonProperty("awayWin")
    @DatabaseField(columnName = "away_win")
    private Integer awayWin;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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
