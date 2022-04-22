package com.example.demo1;

import com.example.demo1.dataclasses.PollutionLevels;
import com.example.demo1.dataclasses.Tech;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * Public class IO is used to load all important information to and from a file system.
 *
 * @author Group 2A
 * @version 1.0
 * @since 21/04/2022
 */
public class IO {
    /**
     * Name for wastewater tech text file location
     */
    private final String fileName;

    /**
     * This constructor loads the file location of the wastewater tech text file
     * @param fileName file location of the wastewater tech text file
     */
    public IO(String fileName){
        this.fileName = fileName;
    }

    /**
     * This method loads all the wastewater tech data into the system.
     * @return LinkedHashMap holding all the wastewater tech loaded.
     * @throws FileNotFoundException if the specified text file does not exist.
     */
    public LinkedHashMap<String, LinkedHashMap<String,Tech>> loadData() throws FileNotFoundException { //load wastewater tech

        LinkedHashMap<String, LinkedHashMap<String,Tech>> originalList = new LinkedHashMap<>(); //hold all wastewater tech

        String[] stages = {"PRELIMINARY","CHEMICAL","BIOLOGICAL","TERTIARY","SLUDGE"};
        for(String stage : stages)
            originalList.put(stage,new LinkedHashMap<>());

        File file = new File(fileName); //load location
        Scanner sc = new Scanner(file);

        String[] hold = new String[8]; //make array of strings with 8 elements

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

    /**
     * This method loads all the location and pollution data into the system.
     * @return LinkedHashMap holding all the location and pollution data loaded.
     * @throws FileNotFoundException if the specified text file does not exist.
     */
    public LinkedHashMap<String,LinkedHashMap<String,PollutionLevels>> loadLocations() throws FileNotFoundException {

        LinkedHashMap<String,LinkedHashMap<String, PollutionLevels>> locations = new LinkedHashMap<>();

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
                PollutionLevels initial = new PollutionLevels(Double.parseDouble(hold[2]),Double.parseDouble(hold[3]),Double.parseDouble(hold[4]));
                locations.get(hold[0]).putIfAbsent(hold[1],initial);
            }
        }
        sc2.close();
        return locations;
    }

    /**
     * This method saves all the wastewater tech data to the text file.
     * @param originalList list of all available wastewater tech loaded into the system
     * @throws IOException if the specified text file does not exist.
     */
    public void saveData(LinkedHashMap<String, LinkedHashMap<String,Tech>> originalList) throws IOException {

        PrintWriter writer = new PrintWriter(fileName, StandardCharsets.UTF_8); //save location

        for(Map.Entry<String, LinkedHashMap<String, Tech>> full : originalList.entrySet())
            for(Map.Entry<String, Tech> list : full.getValue().entrySet())
                writer.format("%S,%S,%.4f,%.4f,%.4f,%.2f,%.3f,%.2f\n", list.getValue().getType(), list.getValue().getName(), list.getValue().getTSS(), list.getValue().getCOD(), list.getValue().getBOD(),list.getValue().getArea(), list.getValue().getEnergy(),list.getValue().getCost());

        writer.close(); //close writer
    }
}