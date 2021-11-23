package com.company;

import java.util.Comparator;

class TSSCompDs implements Comparator<Result> { //comparator to sort final TSS in descending order
    @Override
    public int compare(Result comp1, Result comp2) {
        return Double.compare(comp2.getTSS(), comp1.getTSS());
    }
}
