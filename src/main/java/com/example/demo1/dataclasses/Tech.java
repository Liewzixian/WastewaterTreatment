package com.example.demo1.dataclasses;

public class Tech { //class for linked list to hold all information on all treatment types
    private double TSS,COD,BOD,area,energy,cost; //removal efficiencies for TSS,BOD,COD and values for area,energy and cost
    private String type,name; //names for all treatments and their stages

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

    public double getTSS() { return TSS; } //getter for TSS

    public double getCOD() {
        return COD;
    } //getter for COD

    public double getBOD() {
        return BOD;
    } //getter for BOD

    public double getArea() {
        return area;
    } //getter for area

    public double getEnergy() {
        return energy;
    } //getter for energy
    public String getType() { return type; } //getter for treatment stage

    public String getName() {
        return name;
    } //getter for treatment tech name

    public double getCost() {
        return cost;
    } //getter for cost

    public void setTSS(double TSS) {
        this.TSS = TSS;
    } //setter for TSS

    public void setCOD(double COD) {
        this.COD = COD;
    } //setter for COD

    public void setBOD(double BOD) {
        this.BOD = BOD;
    } //setter for BOD

    public void setArea(double area) {
        this.area = area;
    } //setter for area

    public void setEnergy(double energy) {
        this.energy = energy;
    } //setter for energy

    public void setName(String name) {
        this.name = name;
    } //setter for treatment tech name

    public void setCost(double cost) {
        this.cost = cost;
    } //setter for treatment cost
}

