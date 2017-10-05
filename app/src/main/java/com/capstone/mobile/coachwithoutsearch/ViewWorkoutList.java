package com.capstone.mobile.coachwithoutsearch;

/**
 * Created by Emily Marie Adams on 10/5/2017.
 */

public class ViewWorkoutList {

    String acpid, actname, wrasets, actstatus;

    public ViewWorkoutList(String acpid, String actname, String wrasets, String actstatus) {
        this.acpid = acpid;
        this.actname = actname;
        this.wrasets = wrasets;
        this.actstatus = actstatus;
    }

    public String getAcpid() {
        return acpid;
    }

    public void setAcpid(String acpid) {
        this.acpid = acpid;
    }

    public String getActname() {
        return actname;
    }

    public void setActname(String actname) {
        this.actname = actname;
    }

    public String getWrasets() {
        return wrasets;
    }

    public void setWrasets(String wrasets) {
        this.wrasets = wrasets;
    }

    public String getActstatus() {
        return actstatus;
    }

    public void setActstatus(String actstatus) {
        this.actstatus = actstatus;
    }
}
