package com.capstone.mobile.coachwithoutsearch;


public class ClassList {

    String clsCustName, clsMemStatus, clsValid, clsSessions;

    public ClassList(String clsCustName, String clsMemStatus, String clsValid, String clsSessions) {
        this.clsCustName = clsCustName;
        this.clsMemStatus = clsMemStatus;
        this.clsValid = clsValid;
        this.clsSessions = clsSessions;
    }

    public String getClsCustName() {
        return clsCustName;
    }

    public void setClsCustName(String clsCustName) {
        this.clsCustName = clsCustName;
    }

    public String getClsMemStatus() {
        return clsMemStatus;
    }

    public void setClsMemStatus(String clsMemStatus) {
        this.clsMemStatus = clsMemStatus;
    }

    public String getClsValid() {
        return clsValid;
    }

    public void setClsValid(String clsValid) {
        this.clsValid = clsValid;
    }

    public String getClsSessions() {
        return clsSessions;
    }

    public void setClsSessions(String clsSessions) {
        this.clsSessions = clsSessions;
    }
}
