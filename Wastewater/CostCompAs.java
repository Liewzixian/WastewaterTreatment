package com.company;

import java.util.Comparator;

class CostCompAs implements Comparator<Result> { //comparator to sort final cost in ascending order
    @Override
    public int compare(Result comp1, Result comp2) {
        return Double.compare(comp1.getCost(), comp2.getCost());
    }
}
