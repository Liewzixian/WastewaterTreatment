package com.company;


class Tech { //class for linked list to hold all information on all treatment types
    int type,code; //type is from 1-5 and covers all 5 stages while code is the unique identifier given by the system to differentiate between methods in same type
    double TSS,COD,BOD,area,energy; //removal efficiencies for TSS,BOD,COD and values for area and energy/volume
    String name; //names for all treatments

    public Tech(int type,int code,String name,double TSS,double COD,double BOD,double area,double energy){
        this.type = type;
        this.code = code;
        this.name = name;
        this.TSS = TSS;
        this.COD = COD;
        this.BOD = BOD;
        this.area = area;
        this.energy = energy;
    }

    public int getType(){
        return type;
    } //getter

    public int getCode(){
        return code;
    } //getter
}