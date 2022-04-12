package com.example.demo1;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class IO {

    private final String fileName;
    private final LinkedHashMap<String,LinkedHashMap<String,Location>> locations;

    public IO(String fileName,LinkedHashMap<String,LinkedHashMap<String,Location>> locations){
        this.fileName = fileName;
        this.locations = locations;
    }

    public void load() throws FileNotFoundException {

        File file = new File(fileName); //load location
        Scanner sc = new Scanner(file);

        String[] hold = new String[7]; //make array of strings with 8 elements

        while(sc.hasNextLine()){ //tokenize string using , and stop when list is empty
            StringTokenizer st = new StringTokenizer(sc.nextLine(),",");

            while (st.hasMoreTokens()) { //temporarily save info of treatment in each loop

                for (int count = 0; count < 7; count++){
                    hold[count] = st.nextToken();
                }

                Menu.fullList.computeIfAbsent(hold[0], k -> new LinkedHashMap<>());
                Tech input = new Tech(hold[0],hold[1],Double.parseDouble(hold[2]),Double.parseDouble(hold[3]),Double.parseDouble(hold[4]),Double.parseDouble(hold[5]),Double.parseDouble(hold[6]));
                Menu.fullList.get(hold[0]).putIfAbsent(hold[1],input);
            }
        }

        File locationData = new File("src/main/resources/com/Treatment/location.txt");
        Scanner sc2 = new Scanner(locationData);

        while(sc2.hasNextLine()){ //tokenize string using , and stop when list is empty
            StringTokenizer st2 = new StringTokenizer(sc2.nextLine(),",");

            while (st2.hasMoreTokens()) { //temporarily save info of treatment in each loop

                for (int count = 0; count < 5; count++){
                    hold[count] = st2.nextToken();
                }

                locations.computeIfAbsent(hold[0], k -> new LinkedHashMap<>());
                Location location = new Location(hold[0],hold[1],Double.parseDouble(hold[2]),Double.parseDouble(hold[3]),Double.parseDouble(hold[4]));
                locations.get(hold[0]).putIfAbsent(hold[1],location);
            }
        }
        sc.close();
        sc2.close();
    }

    public void save() throws IOException {

        PrintWriter writer = new PrintWriter(fileName, StandardCharsets.UTF_8); //save location (can add code to change location)

        for(Map.Entry<String, LinkedHashMap<String, Tech>> full : Menu.fullList.entrySet())
            for(Map.Entry<String, Tech> list : full.getValue().entrySet())
                writer.format("%S,%S,%.2f,%.2f,%.2f,%.2f,%.3f\n", list.getValue().getType(), list.getValue().getName(), list.getValue().getTSS(), list.getValue().getCOD(), list.getValue().getBOD(),list.getValue().getArea(), list.getValue().getEnergy());

        PrintWriter writer2 = new PrintWriter("src/main/resources/com/Treatment/location.txt", StandardCharsets.UTF_8); //save location (can add code to change location)

        for(Map.Entry<String, LinkedHashMap<String, Location>> full : locations.entrySet())
            for(Map.Entry<String, Location> list : full.getValue().entrySet())
                writer2.format("%S,%S,%.2f,%.2f,%.2f\n", list.getValue().getState(), list.getValue().getLocation(), list.getValue().getTSS(), list.getValue().getCOD(),list.getValue().getBOD());

        writer.close(); //close writer
        writer2.close();
    }
}
