package com.company;

import java.util.Comparator;

class CodeComp implements Comparator<Tech> { //comparator to sort linked list of treatment by type then by code in ascending order
    @Override
    public int compare(Tech comp1, Tech comp2) {
        if(comp1.getType() == comp2.getType()){
            return Integer.compare(comp1.getCode(), comp2.getCode());
        }
        else if (comp1.getType() > comp2.getType()){
            return 1;
        }
        else {
            return -1;
        }
    }
}
