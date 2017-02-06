package com.soccer.soccercheck.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ederson.js on 18/01/2017.
 */

public class TeamData implements Serializable {

    private final static long serialVersionUID = 6069642759774318733L;

    @JsonProperty("count")
    private int count;

    @JsonProperty("H")
    private List<Team> teamArrayList = new ArrayList<Team>();

}
