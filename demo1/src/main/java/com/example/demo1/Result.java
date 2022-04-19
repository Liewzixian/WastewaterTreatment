package com.example.demo1;

import java.text.DecimalFormat;

public class Result { //class for linked list to hold result of all possible combinations of treatments (can add more later)

    private final String[] treatments;
    private final double[] TSS;
    private final double[] COD;
    private final double[] BOD;
    private final double[] cost;
    private final double[] area;
    private final double[] energy;
    DecimalFormat df = new DecimalFormat("#.####");

    public Result(Tech[] tech,Initial initial) {

        this.treatments = new String[5];
        this.TSS = new double[5];
        this.COD = new double[5];
        this.BOD = new double[5];
        this.cost = new double[5];
        this.area = new double[5];
        this.energy = new double[5];
        double tempTSS = initial.getTSS(), tempCOD = initial.getCOD(), tempBOD = initial.getBOD(), tempCost = 0, tempArea = 0, tempEnergy = 0;

        for(int i = 0; i < 5; i++) {
            treatments[i] = tech[i].getName();
            tempTSS = tempTSS * (1 - tech[i].getTSS());
            TSS[i] = tempTSS;
            tempCOD = tempCOD * (1 - tech[i].getCOD());
            COD[i] = tempCOD;
            tempBOD = tempBOD * (1 - tech[i].getBOD());
            BOD[i] = tempBOD;
            tempCost = tempCost + tech[i].getCost();
            cost[i] = tempCost;
            tempArea = tempArea + tech[i].getArea();
            area[i] = tempArea;
            tempEnergy = tempEnergy + tech[i].getEnergy();
            energy[i] = tempEnergy/(i+1);
        }
    }

    public String[] getTreatments() {
        return treatments;
    }

    public double[] getTSS() { //getter
        return TSS;
    }

    public double[] getCOD() { //getter
        return COD;
    }

    public double[] getBOD() { //getter
        return BOD;
    }

    public double[] getCost() { //getter
        return cost;
    }

    public double[] getArea() {
        return area;
    }

    public double[] getEnergy() {
        return energy;
    }

    public double getFinalTSS(){
        return Double.parseDouble(df.format(TSS[4]));
    }

    public double getFinalCOD(){
        return Double.parseDouble(df.format(COD[4]));
    }

    public double getFinalBOD(){
        return Double.parseDouble(df.format(BOD[4]));
    }

    public double getFinalCost(){
        return Double.parseDouble(df.format(cost[4]));
    }

    public double getFinalArea(){
        return Double.parseDouble(df.format(area[4]));
    }

    public double getFinalEnergy(){
        return Double.parseDouble(df.format(energy[4]));
    }
}

