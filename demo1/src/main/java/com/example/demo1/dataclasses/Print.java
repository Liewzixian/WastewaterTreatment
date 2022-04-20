package com.example.demo1.dataclasses;

public class Print {
    private final String treatmentsA;
    private final String treatmentsB;
    private final String treatmentsC;
    private final String treatmentsD;
    private final String treatmentsE;

    private final double TSS;
    private final double COD;
    private final double BOD;
    private final double cost;
    private final double energy;
    private final double areaOfFootprint;

    private final double[] fullTSS;
    private final double[] fullBOD;
    private final double[] fullCOD;

    public Print(String a, String b, String c, String d, String e, double aa, double bb, double cc, double dd, double []cod, double []bod, double []tss, double energy, double areaOfFootprint){
        treatmentsA=a;
        treatmentsB=b;
        treatmentsC=c;
        treatmentsD=d;
        treatmentsE=e;
        TSS=aa;
        COD=bb;
        BOD=cc;
        cost=dd;
        fullCOD=cod;
        fullBOD=bod;
        fullTSS=tss;
        this.energy=energy;
        this.areaOfFootprint=areaOfFootprint;
    }

    public String getTreatmentsB() {
        return treatmentsB;
    }
    public String getTreatmentsD() {
        return treatmentsD;
    }
    public String getTreatmentsE() {
        return treatmentsE;
    }

    public double getTSS() {
        return TSS;
    }

    public double getCOD() {
        return COD;
    }

    public double getBOD() {
        return BOD;
    }

    public double getCost() {
        return cost;
    }
    public String getTreatmentsC(){
        return treatmentsC;
    }
    public String getTreatmentsA(){
        return treatmentsA;
    }

    public double getEnergy(){
        return energy;
    }

    public double getAreaOfFootprint() {return areaOfFootprint;}

    public double[] getFullBOD() {
        return fullBOD;
    }

    public double[] getFullCOD() {
        return fullCOD;
    }

    public double[] getFullTSS() {
        return fullTSS;
    }
}
