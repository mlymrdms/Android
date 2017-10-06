package com.capstone.mobile.coachwithoutsearch;

/**
 * Created by Emily Marie Adams on 10/6/2017.
 */

public class LogsList {

    String logdate, logtime, logclass, fname, lname;

    public LogsList(String logdate, String logtime, String logclass, String fname, String lname) {
        this.logdate = logdate;
        this.logtime = logtime;
        this.logclass = logclass;
        this.fname = fname;
        this.lname = lname;

    }

    public String getLogdate() {
        return logdate;
    }

    public void setLogdate(String logdate) {
        this.logdate = logdate;
    }

    public String getLogtime() {
        return logtime;
    }

    public void setLogtime(String logtime) {
        this.logtime = logtime;
    }

    public String getLogclass() {
        return logclass;
    }

    public void setLogclass(String logclass) {
        this.logclass = logclass;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }
}
