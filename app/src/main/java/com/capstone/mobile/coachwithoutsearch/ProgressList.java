package com.capstone.mobile.coachwithoutsearch;



public class ProgressList {

    String logid, logdate, propercentage;

    public ProgressList(String logid, String logdate, String propercentage) {
        this.logid = logid;
        this.logdate = logdate;
        this.propercentage = propercentage;
    }

    public String getLogid() {
        return logid;
    }

    public void setLogid(String logid) {
        this.logid = logid;
    }

    public String getLogdate() {
        return logdate;
    }

    public void setLogdate(String logdate) {
        this.logdate = logdate;
    }

    public String getPropercentage() {
        return propercentage;
    }

    public void setPropercentage(String propercentage) {
        this.propercentage = propercentage;
    }
}
