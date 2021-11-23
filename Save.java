package com.company;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;

class Save{ //class to save treatment types to text file (can add code to save result in future)

    public Save(LinkedList<Tech> saveTech) throws IOException {

        PrintWriter writer = new PrintWriter("/Users/ziqin/Desktop/output.txt", StandardCharsets.UTF_8); //save location (can add code to change location)

        for(Tech print : saveTech) { //save line by line with for loop
            writer.format("%d,%d,%S,%.2f,%.2f,%.2f,%.2f,%.3f\n", print.type, print.code, print.name, print.TSS, print.COD, print.BOD,print.area,print.energy);
        }
        writer.close(); //close writer
    }
}