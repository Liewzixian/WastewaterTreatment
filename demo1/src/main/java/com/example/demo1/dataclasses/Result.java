package com.example.demo1.dataclasses;

import java.text.DecimalFormat;

public class Result { //class for linked list to hold result of all possible combinations of treatments (can add more later)
    private final String[] treatments; //array of tech names
    private final double[] TSS; //array of TSS levels after each stage
    private final double[] COD; //array of COD levels after each stage
    private final double[] BOD; //array of BOD levels after each stage
    private final double[] cost; //array of cost after each stage
    private final double[] area; //array of area after each stage
    private final double[] energy; //array of energy after each stage
    DecimalFormat df = new DecimalFormat("#.####"); //decimal format to show results

    public Result(Tech[] tech, PollutionLevels pollutionLevels) { //constructor

        this.treatments = new String[5];
        this.TSS = new double[5];
        this.COD = new double[5];
        this.BOD = new double[5];
        this.cost = new double[5];
        this.area = new double[5];
        this.energy = new double[5];
        double tempTSS = pollutionLevels.getTSS(), tempCOD = pollutionLevels.getCOD(), tempBOD = pollutionLevels.getBOD(), tempCost = 0, tempArea = 0, tempEnergy = 0;

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
    } //getter for tech name array

    public double[] getTSS() { //getter
        return TSS;
    } //getter for TSS array

    public double[] getCOD() { //getter
        return COD;
    } //getter for COD array

    public double[] getBOD() { //getter
        return BOD;
    } //getter for BOD array

    public double[] getCost() { //getter
        return cost;
    } //getter for cost array

    public double[] getArea() {
        return area;
    } //getter for area array

    public double[] getEnergy() {
        return energy;
    } //getter for energy array
    public double getFinalTSS(){
        return Double.parseDouble(df.format(TSS[4]));
    } //getter for final TSS value
    public double getFinalCOD(){
        return Double.parseDouble(df.format(COD[4]));
    } //getter for final COD value
    public double getFinalBOD(){
        return Double.parseDouble(df.format(BOD[4]));
    } //getter for final BOD value
    public double getFinalCost(){
        return Double.parseDouble(df.format(cost[4]));
    } //getter for final cost
    public double getFinalArea(){
        return Double.parseDouble(df.format(area[4]));
    } //getter for final area
    public double getFinalEnergy(){
        return Double.parseDouble(df.format(energy[4]));
    } //getter for final energy
}

