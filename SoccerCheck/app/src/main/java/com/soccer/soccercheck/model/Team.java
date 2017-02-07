package com.soccer.soccercheck.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by ederson.js on 21/11/2016.
 */
@DatabaseTable(tableName = "team")
public class Team implements Serializable {

    @JsonProperty("_links")
    private Links links;

    @JsonIgnore
    @DatabaseField(id = true, generatedId = true)
    private Integer id;

    @JsonProperty("name")
    @DatabaseField
    private String name;

    @JsonProperty("code")
    @DatabaseField
    private String code;

    @JsonProperty("shortName")
    @DatabaseField(columnName = "short_name")
    private String shortName;

    @JsonProperty("squadMarketValue")
    @DatabaseField(columnName = "squad_market_value")
    private String squadMarketValue; // Team market value

    @JsonProperty("crestUrl")
    @DatabaseField(columnName = "cres_url")
    private String crestUrl; // Team Logo

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getSquadMarketValue() {
        return squadMarketValue;
    }

    public void setSquadMarketValue(String squadMarketValue) {
        this.squadMarketValue = squadMarketValue;
    }

    public String getCrestUrl() {
        return crestUrl;
    }

    public void setCrestUrl(String crestUrl) {
        this.crestUrl = crestUrl;
    }
}
