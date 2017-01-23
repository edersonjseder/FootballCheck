package com.soccer.soccercheck.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ederson.js on 18/01/2017.
 */

public class Standings implements Serializable {

    private final static long serialVersionUID = 6069642759774318733L;

    @JsonProperty("A")
    private List<StandingChampionsLeague> mStandingChampionsLeagueGroupA = new ArrayList<StandingChampionsLeague>();

    @JsonProperty("B")
    private List<StandingChampionsLeague> mStandingChampionsLeagueGroupB = new ArrayList<StandingChampionsLeague>();

    @JsonProperty("C")
    private List<StandingChampionsLeague> mStandingChampionsLeagueGroupC = new ArrayList<StandingChampionsLeague>();

    @JsonProperty("D")
    private List<StandingChampionsLeague> mStandingChampionsLeagueGroupD = new ArrayList<StandingChampionsLeague>();

    @JsonProperty("E")
    private List<StandingChampionsLeague> mStandingChampionsLeagueGroupE = new ArrayList<StandingChampionsLeague>();

    @JsonProperty("F")
    private List<StandingChampionsLeague> mStandingChampionsLeagueGroupF = new ArrayList<StandingChampionsLeague>();

    @JsonProperty("G")
    private List<StandingChampionsLeague> mStandingChampionsLeagueGroupG = new ArrayList<StandingChampionsLeague>();

    @JsonProperty("H")
    private List<StandingChampionsLeague> mStandingChampionsLeagueGroupH = new ArrayList<StandingChampionsLeague>();

    private List<List<StandingChampionsLeague>> groups;


    public List<StandingChampionsLeague> getmStandingChampionsLeagueGroupA() {
        return mStandingChampionsLeagueGroupA;
    }

    public void setmStandingChampionsLeagueGroupA(List<StandingChampionsLeague> mStandingChampionsLeagueGroupA) {
        this.mStandingChampionsLeagueGroupA = mStandingChampionsLeagueGroupA;
    }

    public List<StandingChampionsLeague> getmStandingChampionsLeagueGroupB() {
        return mStandingChampionsLeagueGroupB;
    }

    public void setmStandingChampionsLeagueGroupB(List<StandingChampionsLeague> mStandingChampionsLeagueGroupB) {
        this.mStandingChampionsLeagueGroupB = mStandingChampionsLeagueGroupB;
    }

    public List<StandingChampionsLeague> getmStandingChampionsLeagueGroupC() {
        return mStandingChampionsLeagueGroupC;
    }

    public void setmStandingChampionsLeagueGroupC(List<StandingChampionsLeague> mStandingChampionsLeagueGroupC) {
        this.mStandingChampionsLeagueGroupC = mStandingChampionsLeagueGroupC;
    }

    public List<StandingChampionsLeague> getmStandingChampionsLeagueGroupD() {
        return mStandingChampionsLeagueGroupD;
    }

    public void setmStandingChampionsLeagueGroupD(List<StandingChampionsLeague> mStandingChampionsLeagueGroupD) {
        this.mStandingChampionsLeagueGroupD = mStandingChampionsLeagueGroupD;
    }

    public List<StandingChampionsLeague> getmStandingChampionsLeagueGroupE() {
        return mStandingChampionsLeagueGroupE;
    }

    public void setmStandingChampionsLeagueGroupE(List<StandingChampionsLeague> mStandingChampionsLeagueGroupE) {
        this.mStandingChampionsLeagueGroupE = mStandingChampionsLeagueGroupE;
    }

    public List<StandingChampionsLeague> getmStandingChampionsLeagueGroupF() {
        return mStandingChampionsLeagueGroupF;
    }

    public void setmStandingChampionsLeagueGroupF(List<StandingChampionsLeague> mStandingChampionsLeagueGroupF) {
        this.mStandingChampionsLeagueGroupF = mStandingChampionsLeagueGroupF;
    }

    public List<StandingChampionsLeague> getmStandingChampionsLeagueGroupG() {
        return mStandingChampionsLeagueGroupG;
    }

    public void setmStandingChampionsLeagueGroupG(List<StandingChampionsLeague> mStandingChampionsLeagueGroupG) {
        this.mStandingChampionsLeagueGroupG = mStandingChampionsLeagueGroupG;
    }

    public List<StandingChampionsLeague> getmStandingChampionsLeagueGroupH() {
        return mStandingChampionsLeagueGroupH;
    }

    public void setmStandingChampionsLeagueGroupH(List<StandingChampionsLeague> mStandingChampionsLeagueGroupH) {
        this.mStandingChampionsLeagueGroupH = mStandingChampionsLeagueGroupH;
    }

    public List<List<StandingChampionsLeague>> getGroups() {

        groups = new ArrayList<List<StandingChampionsLeague>>();

        groups.add(mStandingChampionsLeagueGroupA);
        groups.add(mStandingChampionsLeagueGroupB);
        groups.add(mStandingChampionsLeagueGroupC);
        groups.add(mStandingChampionsLeagueGroupD);
        groups.add(mStandingChampionsLeagueGroupE);
        groups.add(mStandingChampionsLeagueGroupF);
        groups.add(mStandingChampionsLeagueGroupG);
        groups.add(mStandingChampionsLeagueGroupH);

        return groups;
    }
}
