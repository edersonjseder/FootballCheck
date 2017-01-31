package com.soccer.soccercheck.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by ederson.js on 24/01/2017.
 */

public class FixtureDetail implements Serializable {

    private final static long serialVersionUID = -2141883752329416640L;

    @JsonProperty("fixture")
    private Fixture fixture;

    @JsonProperty("head2head")
    private Head2head head2head;

    /** Getters and Setters **/
    public Fixture getFixture() {
        return fixture;
    }

    public void setFixture(Fixture fixture) {
        this.fixture = fixture;
    }

    public Head2head getHead2head() {
        return head2head;
    }

    public void setHead2head(Head2head head2head) {
        this.head2head = head2head;
    }
}
