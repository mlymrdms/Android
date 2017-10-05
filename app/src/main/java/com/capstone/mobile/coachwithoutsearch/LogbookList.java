package com.capstone.mobile.coachwithoutsearch;


public class LogbookList {

    String custfirstname, custlastname, custlog, custclsname, custsessions, wrkid;

    public LogbookList(String custfirstname, String custlastname, String custlog, String custclsname, String custsessions,
                       String wrkid) {
        this.custfirstname = custfirstname;
        this.custlastname = custlastname;
        this.custlog = custlog;
        this.custclsname = custclsname;
        this.custsessions = custsessions;
        this.wrkid = wrkid;
    }

    public String getCustfirstname() {
        return custfirstname;
    }

    public void setCustfirstname(String custfirstname) {
        this.custfirstname = custfirstname;
    }

    public String getCustlastname() {
        return custlastname;
    }

    public void setCustlastname(String custlastname) {
        this.custlastname = custlastname;
    }

    public String getCustlog() {
        return custlog;
    }

    public void setCustlog(String custlog) {
        this.custlog = custlog;
    }

    public String getCustclsname() {
        return custclsname;
    }

    public String getCustsessions() {
        return custsessions;
    }

    public void setCustsessions(String custsessions) {
        this.custsessions = custsessions;
    }

    public void setCustclsname(String custclsname) {
        this.custclsname = custclsname;
    }

    public String getWrkid() {
        return wrkid;
    }

    public void setWrkid(String wrkid) {
        this.wrkid = wrkid;
    }
}
