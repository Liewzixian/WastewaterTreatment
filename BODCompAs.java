package com.company;

import java.util.Comparator;

class BODCompAs implements Comparator<Result> { //comparator to sort final BOD in ascending order
    @Override
    public int compare(Result comp1, Result comp2) {
        return Double.compare(comp1.getBOD(), comp2.getBOD());
    }
}
