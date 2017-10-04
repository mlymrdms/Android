package com.capstone.mobile.coachwithoutsearch;


public class LogbookList {

    String custfirstname, custlastname, custlog, custclsname;

    public LogbookList(String custfirstname, String custlastname, String custlog, String custclsname) {
        this.custfirstname = custfirstname;
        this.custlastname = custlastname;
        this.custlog = custlog;
        this.custclsname = custclsname;
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

    public void setCustclsname(String custclsname) {
        this.custclsname = custclsname;
    }
}
