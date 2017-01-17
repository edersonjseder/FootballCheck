package com.soccer.soccercheck.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by ederson.js on 21/11/2016.
 */
public class TeamLink {

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
