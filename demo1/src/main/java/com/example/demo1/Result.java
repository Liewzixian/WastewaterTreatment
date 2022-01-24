package com.example.demo1;

public class Result { //class for linked list to hold result of all possible combinations of treatments (can add more later)

    private final String[] treatments;
    private final double TSS;
    private final double COD;
    private final double BOD;
    private final double cost;


    public Result(Tech[] tech,Initial initial) {
        this.treatments = new String[5];
        double tempTSS = initial.getTSS(), tempCOD = initial.getCOD(), tempBOD = initial.getBOD(), tempCost = 0;

        for(int i = 0; i < 5; i++) {
            treatments[i] = tech[i].getName();
            tempTSS = tempTSS * (1 - tech[i].getTSS());
            tempCOD = tempCOD * (1 - tech[i].getCOD());
            tempBOD = tempBOD * (1 - tech[i].getBOD());
            tempCost = tempCost + (tech[i].getArea() * tech[i].getEnergy());
        }

        this.TSS = tempTSS;
        this.COD = tempCOD;
        this.BOD = tempBOD;
        this.cost = tempCost;
    }


    public String[] getTreatments() {
        return treatments;
    }

    public double getTSS() { //getter
        return TSS;
    }

    public double getCOD() { //getter
        return COD;
    }

    public double getBOD() { //getter
        return BOD;
    }

    public double getCost() { //getter
        return cost;
    }
}

