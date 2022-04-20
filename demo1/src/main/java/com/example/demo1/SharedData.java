package com.example.demo1;

import com.example.demo1.dataclasses.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
public class SharedData {
    LinkedHashMap<String, LinkedHashMap<String,Tech>> originalList;
    LinkedHashMap<String, LinkedHashMap<String,Tech>> selectedList;
    LinkedHashMap<String,LinkedHashMap<String, Initial>> locations;
    ArrayList<Selection> selected;
    ArrayList<Selection> unselected;
    ArrayList<String> states;
    ArrayList<Result> results;
    IO io;

    public SharedData(IO io) throws FileNotFoundException {
        this.io = io;

        selectedList = new LinkedHashMap<>();
        locations = io.loadLocations();

        selected= new ArrayList<>();
        unselected = new ArrayList<>();

        states = new ArrayList<>();
        results = new ArrayList<>();

        reloadData();
        loadLocation();
    }

    public LinkedHashMap<String, LinkedHashMap<String, Tech>> getOriginalList() {
        return originalList;
    }
    public ArrayList<String> getStates(){ return states; }
    public ArrayList<Selection> getSelected() {
        return selected;
    }
    public ArrayList<Selection> getUnselected() {
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

            for(Selection loop: selected)
                selectedList.get(loop.getStage()).put(loop.getTreatments(),originalList.get(loop.getStage()).get(loop.getTreatments()));

            for(Map.Entry<String, LinkedHashMap<String, Tech>> stage : selectedList.entrySet()){
                if(stage.getValue().isEmpty())
                    stage.getValue().put("NULL",new Tech(stage.getKey(),"NULL",0,0,0,0,0,0));
            }
            return selectedList;
        }
    }

    public void reloadData() throws FileNotFoundException {
        originalList = io.loadData();

        selected.clear();
        unselected.clear();

        for(Map.Entry<String, LinkedHashMap<String, Tech>> loop : originalList.entrySet())
            for(Map.Entry<String, Tech> print : loop.getValue().entrySet())
                unselected.add(new Selection(loop.getKey(),print.getKey()));
    }

    public void loadLocation() throws FileNotFoundException {
        locations = io.loadLocations();

        for(Map.Entry<String, LinkedHashMap<String, Initial>> state : locations.entrySet())
            if(!states.contains(state.getKey()))
                states.add(state.getKey());
    }

    public ArrayList<String> loadStateArea(String stateName){
        ArrayList<String> areas = new ArrayList<>();
        for(Map.Entry<String, Initial> area : locations.get(stateName).entrySet())
            areas.add(area.getKey());
        return areas;
    }

    public Initial loadAreaData(String stateName, String areaName){
        return locations.get(stateName).get(areaName);
    }
}