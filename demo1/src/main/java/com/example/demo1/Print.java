package com.example.demo1;

public class Print {
    public String treatmentsA;
    public String treatmentsB;
    public String treatmentsC;
    public String treatmentsD;
    public String treatmentsE;
    public double TSS;
    public double COD;
    public double BOD;
    public double cost;

    public Print(String a,String b,String c,String d,String e,double aa,double bb,double cc,double dd){
        treatmentsA=a;
        treatmentsB=b;
        treatmentsC=c;
        treatmentsD=d;
        treatmentsE=e;
        TSS=aa;
        COD=bb;
        BOD=cc;
        cost=dd;
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

    public String getTreatmentsC() {
        return treatmentsC;
    }

    public String getTreatmentsA() {
        return treatmentsA;
    }

}
