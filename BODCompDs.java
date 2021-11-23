package com.company;

import java.util.Comparator;

class BODCompDs implements Comparator<Result> { //comparator to sort final BOD in descending order
    @Override
    public int compare(Result comp1, Result comp2) {
        return Double.compare(comp2.getBOD(), comp1.getBOD());
    }
}
