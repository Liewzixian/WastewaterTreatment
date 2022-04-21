package com.example.demo1;

import com.example.demo1.dataclasses.Initial;
import com.example.demo1.dataclasses.Tech;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class IO { //class to get wastewater tech and location data
    private final String fileName; //name for wastewater tech location
    public IO(String fileName){
        this.fileName = fileName;
    } //constructor
    public LinkedHashMap<String, LinkedHashMap<String,Tech>> loadData() throws FileNotFoundException { //load wastewater tech

        LinkedHashMap<String, LinkedHashMap<String,Tech>> originalList = new LinkedHashMap<>();

        String[] stages = {"PRELIMINARY","CHEMICAL","BIOLOGICAL","TERTIARY","SLUDGE"};
        for(String stage : stages)
            originalList.put(stage,new LinkedHashMap<>());

        File file = new File(fileName); //load location
        Scanner sc = new Scanner(file);

        String[] hold = new String[8]; //make array of strings with 7 elements

        while(sc.hasNextLine()){ //tokenize string using , and stop when list is empty
            StringTokenizer st = new StringTokenizer(sc.nextLine(),",");

            while (st.hasMoreTokens()) { //temporarily save info of treatment in each loop

                for (int count = 0; count < 8; count++)
                    hold[count] = st.nextToken();

                Tech input = new Tech(hold[0],hold[1],Double.parseDouble(hold[2]),Double.parseDouble(hold[3]),Double.parseDouble(hold[4]),Double.parseDouble(hold[5]),Double.parseDouble(hold[6]),Double.parseDouble(hold[7]));
                originalList.get(hold[0]).putIfAbsent(hold[1],input);
            }
        }
        sc.close();
        return originalList;
    }

    public LinkedHashMap<String,LinkedHashMap<String,Initial>> loadLocations() throws FileNotFoundException { //load location data

        LinkedHashMap<String,LinkedHashMap<String, Initial>> locations = new LinkedHashMap<>();

        File locationData = new File("src/main/resources/com/Treatment/location.txt");
        Scanner sc2 = new Scanner(locationData);

        String[] hold = new String[5]; //make array of strings with 5 elements

        while(sc2.hasNextLine()){ //tokenize string using , and stop when list is empty
            StringTokenizer st2 = new StringTokenizer(sc2.nextLine(),",");

            while (st2.hasMoreTokens()) { //temporarily save info of treatment in each loop

                for (int count = 0; count < 5; count++){
                    hold[count] = st2.nextToken();
                }

                locations.computeIfAbsent(hold[0], k -> new LinkedHashMap<>());
                Initial initial = new Initial(Double.parseDouble(hold[2]),Double.parseDouble(hold[3]),Double.parseDouble(hold[4]));
                locations.get(hold[0]).putIfAbsent(hold[1],initial);
            }
        }
        sc2.close();
        return locations;
    }

    public void saveData(LinkedHashMap<String, LinkedHashMap<String,Tech>> originalList) throws IOException { //save wastewater tech

        PrintWriter writer = new PrintWriter(fileName, StandardCharsets.UTF_8); //save location (can add code to change location)

        for(Map.Entry<String, LinkedHashMap<String, Tech>> full : originalList.entrySet())
            for(Map.Entry<String, Tech> list : full.getValue().entrySet())
                writer.format("%S,%S,%.2f,%.2f,%.2f,%.2f,%.3f,%.2f\n", list.getValue().getType(), list.getValue().getName(), list.getValue().getTSS(), list.getValue().getCOD(), list.getValue().getBOD(),list.getValue().getArea(), list.getValue().getEnergy(),list.getValue().getCost());

        writer.close(); //close writer
    }
}
