package com.soccer.soccercheck.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ederson.js on 20/01/2017.
 */

public class PlayerList implements Serializable {

    private final static long serialVersionUID = -3101788632156133309L;

    @JsonProperty("_links")
    private Links links;

    @JsonProperty("count")
    private Integer count;

    @JsonProperty("players")
    private List<Players> players = null;

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<Players> getPlayers() {
        return players;
    }

    public void setPlayers(List<Players> players) {
        this.players = players;
    }
}
