package com.example.communitycleanup.DataTransfer;

public class LogAnIssue {
    public String postcode;
    public String description;
    public String userEmail;

    public LogAnIssue(String d){
        this.description = d;
        this.userEmail = userEmail;
    }

    void postCode(String p){
       this.postcode = p;
    }

    void IssueDescription(String d){
        this.description = d;
    }

    void submit(String p, String d){
        this.postcode = p;
        this.description = d;
    }



}
