package Coursework.DataClasses;

public class Tech { //class for linked list to hold all information on all treatment types
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

    public void setType(int type) {
        this.type = type;
    }

    public void setCode(int code) {
        this.code = code;
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
