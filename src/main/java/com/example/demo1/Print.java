package com.example.demo1;

public class Print {
    public String treatmentsA;
    public String treatmentsB;
    public String treatmentsC;
    public String treatmentsD;
    public String treatmentsE;

    public String treatments;
    public double TSS;
    public double COD;
    public double BOD;
    public double cost;
    public double[] fullTSS;
    public double[] fullBOD;
    public double[] fullCOD;


    public String stage;


    public Print(String a,String b,String c,String d,String e,double aa,double bb,double cc,double dd,double []cod,double []bod,double []tss){
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
    }

    public Print(String a , String b){
        stage=a;
        treatments=b;
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

    public String getStage() {
        return stage;
    }

    public double getCost() {
        return cost;
    }

    public String getTreatmentsC() {
        return treatmentsC;
    }

    public String getTreatmentsA() {
        return treatmentsA;
    }

    public String getTreatments() {
        return treatments;
    }


}
