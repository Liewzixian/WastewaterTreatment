package com.example.demo1;

public class Location {

    private String state,location;
    private double TSS,COD,BOD;

    public Location(String state, String location, double TSS, double COD, double BOD){
        this.state = state;
        this.location = location;
        this.TSS = TSS;
        this.COD = COD;
        this.BOD = BOD;
    }

    private Location(Initial initial){
        this.state = "CUSTOM";
        this.location = "CUSTOM";
        this.TSS = initial.getTSS();
        this.COD = initial.getCOD();
        this.BOD = initial.getBOD();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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
