package com.company;

import java.util.Comparator;

class CODCompAs implements Comparator<Result> { //comparator to sort final COD in ascending order
    @Override
    public int compare(Result comp1, Result comp2) {
        return Double.compare(comp1.getCOD(), comp2.getCOD());
    }
}
