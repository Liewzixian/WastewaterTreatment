package com.company;

import java.util.Comparator;

class CostCompDs implements Comparator<Result> { //comparator to sort final cost in descending order
    @Override
    public int compare(Result comp1, Result comp2) {
        return Double.compare(comp2.getCost(), comp1.getCost());
    }
}
