package com.company;

import java.util.Comparator;

class TSSCompAs implements Comparator<Result> { //comparator to sort final TSS in ascending order
    @Override
    public int compare(Result comp1, Result comp2) {
        return Double.compare(comp1.getTSS(), comp2.getTSS());
    }
}
