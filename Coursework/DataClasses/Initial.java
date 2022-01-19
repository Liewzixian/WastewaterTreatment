package Coursework.DataClasses;

public class Initial {

    private final double TSS;
    private final double COD;
    private final double BOD;

    public Initial(double TSS,double COD,double BOD){
        this.TSS = TSS;
        this.COD = COD;
        this.BOD = BOD;
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
}
