package Coursework.DataClasses;

public class Result { //class for linked list to hold result of all possible combinations of treatments (can add more later)
    private final String t1;
    private final String t2;
    private final String t3;
    private final String t4;
    private final String t5;
    private final double TSS;
    private final double COD;
    private final double BOD;
    private final double cost; //results for all calculations

    public Result(Tech t1,Tech t2,Tech t3,Tech t4,Tech t5,double TSS,double COD,double BOD) {
        this.t1 = t1.getName();
        this.t2 = t2.getName();
        this.t3 = t3.getName();
        this.t4 = t4.getName();
        this.t5 = t5.getName();
        this.TSS = TSS * (1-t1.getTSS()) * (1-t2.getTSS()) * (1-t3.getTSS()) * (1-t4.getTSS()) * (1-t5.getTSS());
        this.COD = COD * (1-t1.getCOD()) * (1-t2.getCOD()) * (1-t3.getCOD()) * (1-t4.getCOD()) * (1-t5.getCOD());
        this.BOD = BOD * (1-t1.getBOD()) * (1-t2.getBOD()) * (1-t3.getBOD()) * (1-t4.getBOD()) * (1-t5.getBOD());
        this.cost = (t1.getArea()*t1.getEnergy()) + (t2.getArea()*t2.getEnergy()) + (t3.getArea()*t3.getEnergy()) + (t4.getArea()*t4.getEnergy()) + (t5.getArea()*t5.getEnergy());
    }

    public String getT1() {
        return t1;
    }

    public String getT2() {
        return t2;
    }

    public String getT3() {
        return t3;
    }

    public String getT4() {
        return t4;
    }

    public String getT5() {
        return t5;
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
