package com.example.demo1.dataclasses;

public class Initial {
    private final double TSS; //initial TSS pollution levels
    private final double COD; //initial COD pollution levels
    private final double BOD; //initial BOD pollution levels
    public Initial(double COD, double BOD, double TSS){ //constructor
        this.COD = COD;
        this.BOD = BOD;
        this.TSS = TSS;
    }

    public double getTSS() {
        return TSS;
    } //getter for TSS

    public double getCOD() {
        return COD;
    } //getter for COD

    public double getBOD() {
        return BOD;
    } //getter for BOD
}

