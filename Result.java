package com.company;



class Result { //class for linked list to hold result of all possible combinations of treatments (can add more later)
    String t1,t2,t3,t4,t5; //names for all 5 types of wastewater treatment
    double TSS,COD,BOD,cost; //results for all calculations

    public Result(String t1,String t2,String t3,String t4,String t5,double TSS,double COD,double BOD,double cost) {
        this.t1 = t1;
        this.t2 = t2;
        this.t3 = t3;
        this.t4 = t4;
        this.t5 = t5;
        this.TSS = TSS;
        this.COD = COD;
        this.BOD = BOD;
        this.cost = cost;
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
