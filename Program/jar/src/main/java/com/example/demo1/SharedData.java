package com.example.demo1;

import com.example.demo1.dataclasses.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Public class SharedData is used to hold information such as the list of all available tech, list of user-selected
 * tech, location and pollution data and results.
 *
 * @author Group 2A
 * @version 1.0
 * @since 21/04/2022
 */
public class SharedData {
    /**
     * List of all available wastewater tech
     */
    LinkedHashMap<String, LinkedHashMap<String,Tech>> originalList;
    /**
     * List of user-selected tech
     */
    LinkedHashMap<String, LinkedHashMap<String,Tech>> selectedList;
    /**
     * List of location and pollution data
     */
    LinkedHashMap<String,LinkedHashMap<String, PollutionLevels>> locations;
    /**
     * ArrayList of user-selected tech
     */
    ArrayList<Selection> selected;
    /**
     * ArrayList of user-ignored tech
     */
    ArrayList<Selection> unselected;
    /**
     * ArrayList of state names
     */
    ArrayList<String> states;
    /**
     * ArrayList of area names
     */
    ArrayList<Result> results;
    /**
     * input/output file system
     */
    IO io;

    /**
     * This constructor is used to create an object to hold all dynamic information such as the list of all available
     * tech, list of user-selected tech, location and pollution data and results.
     * @param io IO object to load and save data to file system
     * @throws FileNotFoundException if text file specified does not exist
     */
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

    /**
     * Getter for list of all available tech
     * @return list of all available tech
     */
    public LinkedHashMap<String, LinkedHashMap<String, Tech>> getOriginalList() {
        return originalList;
    }
    /**
     * Getter for all Malaysian state names
     * @return all Malaysian state names
     */
    public ArrayList<String> getStates(){ return states; }
    /**
     * Getter for user-selected tech list
     * @return user-selected tech list
     */
    public ArrayList<Selection> getSelected() {
        return selected;
    }
    /**
     * Getter for user-ignored tech list
     * @return user-ignored tech list
     */
    public ArrayList<Selection> getUnselected() {
        return unselected;
    }
    /**
     * Getter for list of results
     * @return list of results
     */
    public ArrayList<Result> getResults() {
        return results;
    }
    /**
     * Getter for list of user-selected tech in a LinkedHashMap format
     * @return list of user-selected tech in a LinkedHashMap format
     */
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

    /**
     * Reload list of all wastewater tech from IO
     */
    public void reloadData() throws FileNotFoundException {
        originalList = io.loadData();

        selected.clear();
        unselected.clear();

        for(Map.Entry<String, LinkedHashMap<String, Tech>> loop : originalList.entrySet())
            for(Map.Entry<String, Tech> print : loop.getValue().entrySet())
                unselected.add(new Selection(loop.getKey(),print.getKey()));
    }

    /**
     * Load list of location and pollution levels from IO
     * @throws FileNotFoundException if text file specified does not exist
     */
    public void loadLocation() throws FileNotFoundException {
        locations = io.loadLocations();

        for(Map.Entry<String, LinkedHashMap<String, PollutionLevels>> state : locations.entrySet())
            if(!states.contains(state.getKey()))
                states.add(state.getKey());
    }

    /**
     * Load list of areas in the state specified
     * @param stateName state where the areas are located
     * @return list of area names
     */
    public ArrayList<String> loadStateArea(String stateName){
        ArrayList<String> areas = new ArrayList<>();
        for(Map.Entry<String, PollutionLevels> area : locations.get(stateName).entrySet())
            areas.add(area.getKey());
        return areas;
    }

    /**
     * Return area pollution levels
     * @param stateName state name where pollution is measured
     * @param areaName area name where pollution is measured
     * @return pollution levels of the area
     */
    public PollutionLevels loadAreaData(String stateName, String areaName){
        return locations.get(stateName).get(areaName);
    }

    /**
     * Save list of wastewater tech to file using IO
     * @throws IOException if text file specified does not exist
     */
    public void saveData() throws IOException {
        io.saveData(getOriginalList());
    }
}