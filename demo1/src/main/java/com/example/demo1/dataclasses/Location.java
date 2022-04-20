package com.example.demo1.dataclasses;

public class Location {

    private final String state;
    private final String location;
    private double TSS,COD,BOD;

    public Location(String state, String location, double TSS, double COD, double BOD){
        this.state = state;
        this.location = location;
        this.TSS = TSS;
        this.COD = COD;
        this.BOD = BOD;
    }
    public String getState() {
        return state;
    }
    public String getLocation() {
        return location;
    }

    public double getTSS() {
        return TSS;
    }

    public void setTSS(double TSS) {
        this.TSS = TSS;
    }

    public double getCOD() {
        return COD;
    }

    public void setCOD(double COD) {
        this.COD = COD;
    }

    public double getBOD() {
        return BOD;
    }

    public void setBOD(double BOD) {
        this.BOD = BOD;
    }
}
