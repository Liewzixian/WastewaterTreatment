package Coursework;

import Coursework.DataClasses.Tech;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class IO {

    private final String fileName;

    public IO(String fileName){
        this.fileName = fileName;
    }

    public void load(TreeMap<Integer, TreeMap<Integer,Tech>> fullList) throws FileNotFoundException {

        File file = new File(fileName); //load location
        Scanner sc = new Scanner(file);

        String[] hold = new String[7]; //make array of strings with 8 elements
        int[] index = new int[5];
        int current;

        while(sc.hasNextLine()){ //tokenize string using , and stop when list is empty
            StringTokenizer st = new StringTokenizer(sc.nextLine(),",");

            while (st.hasMoreTokens()) { //temporarily save info of treatment in each loop

                for (int count = 0; count < 7; count++){
                    hold[count] = st.nextToken();
                }

                current = Integer.parseInt(hold[0]);
                index[current-1]++;
                Tech input = new Tech(hold[1],Double.parseDouble(hold[2]),Double.parseDouble(hold[3]),Double.parseDouble(hold[4]),Double.parseDouble(hold[5]),Double.parseDouble(hold[6]));
                fullList.get(current).put(index[current-1],input);
            }
        }
    }

    public void save(TreeMap<Integer, TreeMap<Integer,Tech>> fullList) throws IOException {

        PrintWriter writer = new PrintWriter("D:\\Download\\output.txt", StandardCharsets.UTF_8); //save location (can add code to change location)

        for(Map.Entry<Integer, TreeMap<Integer,Tech>> full : fullList.entrySet())
            for(Map.Entry<Integer, Tech> list : full.getValue().entrySet())
                writer.format("%d,%S,%.2f,%.2f,%.2f,%.2f,%.3f\n", full.getKey(), list.getValue().getName(), list.getValue().getTSS(), list.getValue().getCOD(), list.getValue().getBOD(),list.getValue().getArea(), list.getValue().getEnergy());

        writer.close(); //close writer
    }
}
