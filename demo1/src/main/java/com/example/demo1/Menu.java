package com.example.demo1;


import javafx.collections.ObservableList;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Menu {

    TechControl techControl;
    ResultControl resultControl;

    IO io;
    boolean changed;

    ArrayList<Result> results;
    LinkedHashMap<String,LinkedHashMap<String,Location>> locations;
    AdjacencyList adjacencyList;

    static LinkedHashMap<String,LinkedHashMap<String,Tech>> fullList;

    public Menu(String fileName) {
        fullList = new LinkedHashMap<>();
        this.locations = new LinkedHashMap<>();
        this.results = new ArrayList<>();
        this.adjacencyList = new AdjacencyList(fullList);

        io = new IO(fileName,locations);
        techControl = new TechControl();
        changed = false;
    }

    public void load() throws FileNotFoundException {
        io.load();
        System.out.println("Treatment data loaded to linked list.");
    }

    public void add(String type, Tech newTech){
        changed = techControl.addEntry(type,newTech);
    }

    public void delete(String type, String name){
        changed = techControl.deleteEntry(type,name);
    }

    public void change(String type, String code, int choice, String newEntry){
        changed = techControl.changeEntry(type,code,choice,newEntry);
    }

    public void showAllTreatments(){
        techControl.showAllTreatments();
    }

    public boolean getCode(String choice){
        return resultControl.getCode(choice);
    }

    public void getSpecificResult(Initial initial){
        resultControl.getSpecificResult(initial);
    }

    public void showAllResults(Initial initial, int standard){
        resultControl = new ResultControl(results);
        resultControl.calculateResults(initial);
        resultControl.printResults(standard);
        changed = false;
    }

    public ObservableList<Print> getResultsTable() {
        return resultControl.getResultsTable();
    }

    public void uniformCost(int choice){
        adjacencyList.UniformCostSearch(choice);
    }

    public void save() throws IOException {
        io.save();
        System.out.println("Treatment data saved to text file.");
    }

    public void clear(){
        techControl.Clear();
    }

    public ObservableList<Print> getSelectionTable() {
        return techControl.getSelectionTable();
    }
}
