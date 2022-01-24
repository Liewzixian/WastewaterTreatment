package com.example.demo1;

public class Tech { //class for linked list to hold all information on all treatment types
    private double TSS,COD,BOD,area,energy; //removal efficiencies for TSS,BOD,COD and values for area and energy/volume
    private String name; //names for all treatments

    public Tech(String name,double TSS,double COD,double BOD,double area,double energy){
        this.name = name;
        this.TSS = TSS;
        this.COD = COD;
        this.BOD = BOD;
        this.area = area;
        this.energy = energy;
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

    public double getArea() {
        return area;
    }

    public double getEnergy() {
        return energy;
    }

    public String getName() {
        return name;
    }

    public void setTSS(double TSS) {
        this.TSS = TSS;
    }

    public void setCOD(double COD) {
        this.COD = COD;
    }

    public void setBOD(double BOD) {
        this.BOD = BOD;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public void setEnergy(double energy) {
        this.energy = energy;
    }

    public void setName(String name) {
        this.name = name;
    }
}

