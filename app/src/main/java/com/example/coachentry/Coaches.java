package com.example.coachentry;

public class Coaches {
    String coachesId;
    String coachesNumber;
    String coachesType;
    String rlycode;


    public Coaches() {
           }

    public Coaches(String coachesId, String coachesNumber, String coachesType, String rlycode ) {
        this.coachesId = coachesId;
        this.coachesNumber = coachesNumber;
        this.coachesType = coachesType;
        this.rlycode = rlycode;


    }

    public String getCoachesId() {
        return coachesId;
    }

    public String getCoachesNumber() {
        return coachesNumber;
    }

    public String getCoachesType() { return coachesType; }

    public String getRlycode(){return rlycode;}



}



