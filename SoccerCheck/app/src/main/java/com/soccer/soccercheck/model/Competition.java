package com.soccer.soccercheck.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 19/11/16.
 */
@DatabaseTable(tableName = "competition")
public class Competition implements Serializable{

    @JsonProperty("_links")
    private Links links;

    @JsonProperty("id")
    @DatabaseField(id = true)
    private Integer id;

    @JsonProperty("caption")
    @DatabaseField
    private String caption;

    @JsonProperty("league")
    @DatabaseField
    private String league;

    @JsonProperty("year")
    @DatabaseField
    private String year;

    @JsonProperty("currentMatchday")
    @DatabaseField(columnName = "current_matchday")
    private Integer currentMatchday;

    @JsonProperty("numberOfMatchdays")
    @DatabaseField(columnName = "number_matchdays")
    private Integer numberOfMatchdays;

    @JsonProperty("numberOfTeams")
    @DatabaseField(columnName = "number_teams")
    private int numberOfTeams;

    @JsonProperty("numberOfGames")
    @DatabaseField(columnName = "number_games")
    private int numberOfGames;

    @JsonProperty("lastUpdated")
    @DatabaseField(columnName = "last_updated")
    private String lastUpdated;

    public Competition(){}

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

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getLeague() {
        return league;
    }

    public void setLeague(String league) {
        this.league = league;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Integer getCurrentMatchday() {
        return currentMatchday;
    }

    public void setCurrentMatchday(Integer currentMatchday) {
        this.currentMatchday = currentMatchday;
    }

    public Integer getNumberOfMatchdays() {
        return numberOfMatchdays;
    }

    public void setNumberOfMatchdays(Integer numberOfMatchdays) {
        this.numberOfMatchdays = numberOfMatchdays;
    }

    public Integer getNumberOfTeams() {
        return numberOfTeams;
    }

    public void setNumberOfTeams(Integer numberOfTeams) {
        this.numberOfTeams = numberOfTeams;
    }

    public Integer getNumberOfGames() {
        return numberOfGames;
    }

    public void setNumberOfGames(Integer numberOfGames) {
        this.numberOfGames = numberOfGames;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}