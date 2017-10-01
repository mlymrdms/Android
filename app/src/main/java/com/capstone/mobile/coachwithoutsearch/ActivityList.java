package com.capstone.mobile.coachwithoutsearch;


public class ActivityList {

    String actName, actSets;

    public ActivityList(String actName, String actSets) {
        this.actName = actName;
        this.actSets = actSets;
    }

    public String getActName() {
        return actName;
    }

    public void setActName(String actName) {
        this.actName = actName;
    }

    public String getActSets() {
        return actSets;
    }

    public void setActSets(String actSets) {
        this.actSets = actSets;
    }
}
