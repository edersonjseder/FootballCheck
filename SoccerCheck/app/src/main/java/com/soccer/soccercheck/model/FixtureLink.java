package com.soccer.soccercheck.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by root on 19/11/16.
 */
public class FixtureLink {

    @JsonProperty("href")
    private String href;


    @JsonProperty("href")
    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

}