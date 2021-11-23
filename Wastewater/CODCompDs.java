package com.company;

import java.util.Comparator;

class CODCompDs implements Comparator<Result> { //comparator to sort final COD in descending order
    @Override
    public int compare(Result comp1, Result comp2) {
        return Double.compare(comp2.getCOD(), comp1.getCOD());
    }
}
