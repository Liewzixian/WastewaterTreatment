package com.example.demo1;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class IO {

    private final String fileName;
    private final ArrayList<ArrayList<Tech>> fullList;

    public IO(String fileName, ArrayList<ArrayList<Tech>> fullList){
        this.fileName = fileName;
        this.fullList = fullList;
    }

    public void load() throws FileNotFoundException {

        File file = new File(fileName); //load location
        Scanner sc = new Scanner(file);

        String[] hold = new String[7]; //make array of strings with 8 elements
        int type;

        while(sc.hasNextLine()){ //tokenize string using , and stop when list is empty
            StringTokenizer st = new StringTokenizer(sc.nextLine(),",");

            while (st.hasMoreTokens()) { //temporarily save info of treatment in each loop

                for (int count = 0; count < 7; count++){
                    hold[count] = st.nextToken();
                }

                type = Integer.parseInt(hold[0]) - 1;
                Tech input = new Tech(hold[1],Double.parseDouble(hold[2]),Double.parseDouble(hold[3]),Double.parseDouble(hold[4]),Double.parseDouble(hold[5]),Double.parseDouble(hold[6]));
                fullList.get(type).add(input);
            }
        }
    }

    public void save() throws IOException {

        PrintWriter writer = new PrintWriter(fileName, StandardCharsets.UTF_8); //save location (can add code to change location)

        for(ArrayList<Tech> full : fullList)
            for(Tech list : full)
                writer.format("%d,%S,%.2f,%.2f,%.2f,%.2f,%.3f\n", fullList.indexOf(full)+1, list.getName(), list.getTSS(), list.getCOD(), list.getBOD(),list.getArea(), list.getEnergy());

        writer.close(); //close writer
    }
}
