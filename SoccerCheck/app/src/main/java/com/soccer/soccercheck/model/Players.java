package com.soccer.soccercheck.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by ederson.js on 21/11/2016.
 */

public class Players {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("position")
    private String position;

    @JsonProperty("jerseyNumber")
    private Integer jerseyNumber;

    @JsonProperty("dateOfBirth")
    private String dateOfBirth;

    @JsonProperty("nationality")
    private String nationality;

    @JsonProperty("contractUntil")
    private String contractUntil;

    @JsonProperty("marketValue")
    private String marketValue;

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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Integer getJerseyNumber() {
        return jerseyNumber;
    }

    public void setJerseyNumber(Integer jerseyNumber) {
        this.jerseyNumber = jerseyNumber;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getContractUntil() {
        return contractUntil;
    }

    public void setContractUntil(String contractUntil) {
        this.contractUntil = contractUntil;
    }

    public String getMarketValue() {
        return marketValue;
    }

    public void setMarketValue(String marketValue) {
        this.marketValue = marketValue;
    }
}
