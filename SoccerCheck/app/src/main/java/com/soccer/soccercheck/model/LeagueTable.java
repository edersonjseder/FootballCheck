package com.soccer.soccercheck.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ederson.js on 21/11/2016.
 */
@DatabaseTable(tableName = "league_table")
public class LeagueTable {

    @JsonProperty("_links")
    private Links links;

    @JsonIgnore
    @DatabaseField(id = true, generatedId = true)
    private Integer id;

    @JsonProperty("leagueCaption")
    @DatabaseField(columnName = "league_caption")
    private String leagueCaption;

    @JsonProperty("matchday")
    @DatabaseField
    private Integer matchday;

    @JsonProperty("standing")
    @ForeignCollectionField
    private ForeignCollection<Standing> standing;

    public LeagueTable(){}

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLeagueCaption() {
        return leagueCaption;
    }

    public void setLeagueCaption(String leagueCaption) {
        this.leagueCaption = leagueCaption;
    }

    public Integer getMatchday() {
        return matchday;
    }

    public void setMatchday(Integer matchday) {
        this.matchday = matchday;
    }

    public ForeignCollection<Standing> getStanding() {
        return standing;
    }

    public void setStanding(ForeignCollection<Standing> standing) {
        this.standing = standing;
    }
}
