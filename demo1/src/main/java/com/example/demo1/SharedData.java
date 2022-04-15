package com.example.demo1;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class SharedData {
    LinkedHashMap<String, LinkedHashMap<String,Tech>> originalList;
    LinkedHashMap<String, LinkedHashMap<String,Tech>> selectedList;
    LinkedHashMap<String,LinkedHashMap<String,Location>> locations;
    ArrayList<Print> selected;
    ArrayList<Print> unselected;
    ArrayList<Result> results;
    IO io;

    public SharedData(IO io) throws FileNotFoundException {
        this.io = io;
        originalList = io.loadData();
        selectedList = new LinkedHashMap<>();
        locations = io.loadLocations();
        selected= new ArrayList<>();
        unselected = new ArrayList<>();
        results = new ArrayList<>();
        reloadList();
    }

    public LinkedHashMap<String, LinkedHashMap<String, Tech>> getOriginalList() {
        return originalList;
    }

    public LinkedHashMap<String, LinkedHashMap<String, Location>> getLocations() {
        return locations;
    }
    public ArrayList<Print> getSelected() {
        return selected;
    }

    public ArrayList<Print> getUnselected() {
        return unselected;
    }

    public ArrayList<Result> getResults() {
        return results;
    }

    public LinkedHashMap<String, LinkedHashMap<String,Tech>> getSelectedList(){

        selectedList.clear();

        for(Print loop: selected) {
            selectedList.computeIfAbsent(loop.stage, k -> new LinkedHashMap<>());
            selectedList.get(loop.stage).put(loop.treatments,originalList.get(loop.stage).get(loop.treatments));
        }

        return selected.isEmpty() ? originalList : selectedList;
    }

    public void reloadData() throws FileNotFoundException {
        originalList = io.loadData();
    }

    public void reloadList(){
        unselected.clear();
        for(Map.Entry<String, LinkedHashMap<String, Tech>> loop : originalList.entrySet())
            for(Map.Entry<String, Tech> print : loop.getValue().entrySet())
                unselected.add(new Print(loop.getKey(),print.getKey()));
    }
}
