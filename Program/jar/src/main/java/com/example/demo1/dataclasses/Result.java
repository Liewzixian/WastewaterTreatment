package com.example.demo1.dataclasses;

import java.text.DecimalFormat;

/**
 * Public class Result is used to store the results generated by the program to be displayed to the user.
 *
 * @author Group 2A
 * @version 1.0
 * @since 21/04/2022
 */
public class Result {
    /**
     * Array of tech names
     */
    private final String[] treatments;
    /**
     * Array of TSS levels after each stage
     */
    private final double[] TSS;
    /**
     * Array of COD levels after each stage
     */
    private final double[] COD;
    /**
     * Array of BOD levels after each stage
     */
    private final double[] BOD;
    /**
     * Array of cost after each stage
     */
    private final double[] cost;
    /**
     * Array of area after each stage
     */
    private final double[] area;
    /**
     * Array of energy after each stage
     */
    private final double[] energy;
    /**
     * Decimal format to show results
     */
    DecimalFormat df = new DecimalFormat("#.####");

    /**
     * This constructor generates the results to be displayed to the user based on initial pollution levels.
     * @param tech Array of tech used
     * @param pollutionLevels Initial pollution levels
     */
    public Result(Tech[] tech, PollutionLevels pollutionLevels) {

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

    /**
     * Getter for tech name array
     * @return tech name array
     */
    public String[] getTreatments() {
        return treatments;
    }
    /**
     * Getter for tech TSS array
     * @return TSS array
     */
    public double[] getTSS() {
        return TSS;
    }
    /**
     * Getter for tech COD array
     * @return COD array
     */
    public double[] getCOD() {
        return COD;
    }
    /**
     * Getter for tech BOD array
     * @return BOD array
     */
    public double[] getBOD() {
        return BOD;
    }
    /**
     * Getter for tech cost array
     * @return cost array
     */
    public double[] getCost() {
        return cost;
    }
    /**
     * Getter for tech area array
     * @return area array
     */
    public double[] getArea() {
        return area;
    }
    /**
     * Getter for tech energy array
     * @return energy array
     */
    public double[] getEnergy() {
        return energy;
    }
    /**
     * Getter for final TSS value
     * @return final TSS value
     */
    public double getFinalTSS(){
        return Double.parseDouble(df.format(TSS[4]));
    }
    /**
     * Getter for final COD value
     * @return final COD value
     */
    public double getFinalCOD(){
        return Double.parseDouble(df.format(COD[4]));
    }
    /**
     * Getter for final BOD value
     * @return final BOD value
     */
    public double getFinalBOD(){
        return Double.parseDouble(df.format(BOD[4]));
    }
    /**
     * Getter for final cost value
     * @return final cost value
     */
    public double getFinalCost(){
        return Double.parseDouble(df.format(cost[4]));
    }
    /**
     * Getter for final area value
     * @return final area value
     */
    public double getFinalArea(){
        return Double.parseDouble(df.format(area[4]));
    }
    /**
     * Getter for final energy value
     * @return final energy value
     */
    public double getFinalEnergy(){
        return Double.parseDouble(df.format(energy[4]));
    }
}