package com.example.demo1;

public class Result { //class for linked list to hold result of all possible combinations of treatments (can add more later)

    private final String[] treatments;
    private final double[] TSS;
    private final double[] COD;
    private final double[] BOD;
    private final double[] cost;
    private final double[] area;
    private final double[] energy;

    public Result(Tech[] tech,Initial initial) {

        this.treatments = new String[5];
        this.TSS = new double[5];
        this.COD = new double[5];
        this.BOD = new double[5];
        this.cost = new double[5];
        this.area = new double[5];
        this.energy = new double[5];
        double tempTSS = initial.getTSS(), tempCOD = initial.getCOD(), tempBOD = initial.getBOD(), tempCost = 0;

        for(int i = 0; i < 5; i++) {
            treatments[i] = tech[i].getName();
            TSS[i] = tempTSS * (1 - tech[i].getTSS());
            COD[i] = tempCOD * (1 - tech[i].getCOD());
            BOD[i] = tempBOD * (1 - tech[i].getBOD());
            area[i] = tech[i].getArea();
            energy[i] = tech[i].getEnergy();
            cost[i] = tempCost + (tech[i].getArea() * tech[i].getEnergy());
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
        return TSS[4];
    }

    public double getFinalCOD(){
        return COD[4];
    }

    public double getFinalBOD(){
        return BOD[4];
    }

    public double getFinalCost(){
        return cost[4];
    }

    public double getFinalArea(){
        return area[4];
    }

    public double getFinalEnergy(){
        return energy[4];
    }
}

