package com.example.demo1.dataclasses;

/**
 * Public class Tech is used to store information about the wastewater technology used.
 *
 * @author Group 2A
 * @version 1.0
 * @since 21/04/2022
 */
public class Tech {
    /**
     * Removal efficiency for TSS
     */
    private double TSS;
    /**
     * Removal efficiency for COD
     */
    private double COD;
    /**
     * Removal efficiency for BOD
     */
    private double BOD;
    /**
     * Tech area
     */
    private double area;
    /**
     * Tech energy per volume
     */
    private double energy;
    /**
     * Tech cost
     */
    private double cost;
    /**
     * Tech treatment stage
     */
    private final String type;
    /**
     * Tech name
     */
    private String name;

    /**
     * This constructor creates a Tech object which stores information about the wastewater technology such as cleaning
     * efficiencies, area, energy and cost.
     * @param type Tech treatment stage
     * @param name Tech name
     * @param TSS Tech TSS cleaning efficiency
     * @param COD Tech COD cleaning efficiency
     * @param BOD Tech BOD cleaning efficiency
     * @param area Tech area
     * @param energy Tech energy
     * @param cost Tech cost
     */
    public Tech(String type, String name, double TSS, double COD, double BOD, double area, double energy, double cost){
        this.type = type;
        this.name = name;
        this.TSS = TSS;
        this.COD = COD;
        this.BOD = BOD;
        this.area = area;
        this.energy = energy;
        this.cost = cost;
    }

    /**
     * Getter for TSS cleaning efficiency
     * @return TSS cleaning efficiency
     */
    public double getTSS() { return TSS; }
    /**
     * Getter for COD cleaning efficiency
     * @return COD cleaning efficiency
     */
    public double getCOD() {
        return COD;
    }
    /**
     * Getter for BOD cleaning efficiency
     * @return BOD cleaning efficiency
     */
    public double getBOD() {
        return BOD;
    }
    /**
     * Getter for tech area
     * @return tech area
     */
    public double getArea() {
        return area;
    }
    /**
     * Getter for tech energy
     * @return tech energy
     */
    public double getEnergy() {
        return energy;
    }
    /**
     * Getter for tech treatment stage
     * @return tech treatment stage
     */
    public String getType() { return type; }
    /**
     * Getter for tech name
     * @return tech name
     */
    public String getName() {
        return name;
    }
    /**
     * Getter for tech cost
     * @return tech cost
     */
    public double getCost() {
        return cost;
    }
    /**
     * Setter for TSS cleaning efficiency
     * @param TSS TSS cleaning efficiency
     */
    public void setTSS(double TSS) {
        this.TSS = TSS;
    }
    /**
     * Setter for COD cleaning efficiency
     * @param COD COD cleaning efficiency
     */
    public void setCOD(double COD) {
        this.COD = COD;
    }
    /**
     * Setter for BOD cleaning efficiency
     * @param BOD BOD cleaning efficiency
     */
    public void setBOD(double BOD) {
        this.BOD = BOD;
    }
    /**
     * Setter for tech area
     * @param area tech area
     */
    public void setArea(double area) {
        this.area = area;
    }
    /**
     * Setter for tech energy
     * @param energy tech energy
     */
    public void setEnergy(double energy) {
        this.energy = energy;
    }
    /**
     * Setter for tech name
     * @param name tech name
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Setter for tech cost
     * @param cost tech cost
     */
    public void setCost(double cost) {
        this.cost = cost;
    }
}