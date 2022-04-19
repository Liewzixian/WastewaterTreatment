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

    public LinkedHashMap<String, LinkedHashMap<String, Location>> getLocations() { return locations; }
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

        if(selected.isEmpty())
            return originalList;
        else {

            String[] stages = {"PRELIMINARY","CHEMICAL","BIOLOGICAL","TERTIARY","SLUDGE"};
            for(String stage : stages)
                selectedList.put(stage,new LinkedHashMap<>());

            for(Print loop: selected)
                selectedList.get(loop.stage).put(loop.treatments,originalList.get(loop.stage).get(loop.treatments));

            for(Map.Entry<String, LinkedHashMap<String, Tech>> stage : selectedList.entrySet()){
                if(stage.getValue().isEmpty())
                    stage.getValue().put("NULL",new Tech(stage.getKey(),"NULL",0,0,0,0,0,0));
            }
            return selectedList;
        }
    }

    public void reloadData() throws FileNotFoundException {
        originalList = io.loadData();
    }

    public void reloadList(){
        selected.clear();
        unselected.clear();
        for(Map.Entry<String, LinkedHashMap<String, Tech>> loop : originalList.entrySet())
            for(Map.Entry<String, Tech> print : loop.getValue().entrySet())
                unselected.add(new Print(loop.getKey(),print.getKey()));
    }

    public void reload() throws FileNotFoundException {
        reloadData();
        reloadList();
    }
}
