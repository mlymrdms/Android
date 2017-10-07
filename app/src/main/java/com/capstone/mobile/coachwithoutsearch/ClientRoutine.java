package com.capstone.mobile.coachwithoutsearch;



public class ClientRoutine {

    String actname, acpstatus, wrasets;

    public ClientRoutine(String actname, String acpstatus, String wrasets) {
        this.actname = actname;
        this.acpstatus = acpstatus;
        this.wrasets = wrasets;
    }

    public String getActname() {
        return actname;
    }

    public void setActname(String actname) {
        this.actname = actname;
    }

    public String getAcpstatus() {
        return acpstatus;
    }

    public void setAcpstatus(String acpstatus) {
        this.acpstatus = acpstatus;
    }

    public String getWrasets() {
        return wrasets;
    }

    public void setWrasets(String wrasets) {
        this.wrasets = wrasets;
    }
}
