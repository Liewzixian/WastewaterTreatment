package Coursework;

import Coursework.DataClasses.Tech;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class IO {

    String fileName;

    public IO(String fileName){
        this.fileName = fileName;
    }

    public void load(LinkedList<Tech> loadTech) throws FileNotFoundException {

        File file = new File(fileName); //load location
        Scanner sc = new Scanner(file);

        String[] hold = new String[8]; //make array of strings with 8 elements

        while(sc.hasNextLine()){ //tokenize string using , and stop when list is empty
            StringTokenizer st = new StringTokenizer(sc.nextLine(),",");

            while (st.hasMoreTokens()) { //temporarily save info of treatment in each loop
                for (int count = 0; count<8; count++){
                    hold[count] = st.nextToken();
                }
                Tech input = new Tech(Integer.parseInt(hold[0]),Integer.parseInt(hold[1]),hold[2],Double.parseDouble(hold[3]),Double.parseDouble(hold[4]),Double.parseDouble(hold[5]),Double.parseDouble(hold[6]),Double.parseDouble(hold[7]));
                loadTech.add(input); //change each string to correct type and load in linked list
            }
        }
    }

    public void save(LinkedList<Tech> saveTech) throws IOException {

        PrintWriter writer = new PrintWriter("D:\\Download\\output.txt", StandardCharsets.UTF_8); //save location (can add code to change location)

        for(Tech print : saveTech) { //save line by line with for loop
            writer.format("%d,%d,%S,%.2f,%.2f,%.2f,%.2f,%.3f\n", print.getType(), print.getCode(), print.getName(), print.getTSS(), print.getCOD(), print.getBOD(),print.getArea(), print.getEnergy());
        }
        writer.close(); //close writer
    }
}
