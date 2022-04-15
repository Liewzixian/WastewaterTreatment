package com.example.demo1;


import javafx.collections.ObservableList;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Menu {

    TechControl techControl;
    ResultControl resultControl;

    SharedData sharedData;

    IO io;
    boolean changed;

    ArrayList<Result> results;
    LinkedHashMap<String,LinkedHashMap<String,Location>> locations;
    AdjacencyList adjacencyList;

    //static LinkedHashMap<String,LinkedHashMap<String,Tech>> fullList;

    public Menu(String fileName) throws FileNotFoundException {
        locations = new LinkedHashMap<>();
        results = new ArrayList<>();
        adjacencyList = new AdjacencyList();

        io = new IO(fileName);
        sharedData = new SharedData(io);
        techControl = new TechControl(sharedData);
        changed = false;
    }

    public void load() throws FileNotFoundException {
        io.loadData();
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

    public boolean getCode(String choice){
        return resultControl.getCode(choice);
    }

    public void getSpecificResult(Initial initial){
        resultControl.getSpecificResult(initial);
    }

    public void showAllResults(Initial initial, int standard){
        resultControl = new ResultControl(sharedData);
        resultControl.calculateResults(initial);
        resultControl.printResults(standard);
        changed = false;
    }

    public ObservableList<Print> getResultsTable() {
        return resultControl.getResultsTable();
    }
    public ObservableList<Print> getBestTable() {return resultControl.getBestTable();}
    public  void UniformSearch(int i){resultControl.UniFormSearch(i);}

    public LinkedHashMap<String,Tech> uniformCost(int choice){
        return adjacencyList.UniformCostSearch(choice,sharedData);
    }

    public void save() throws IOException {
        io.saveData(sharedData.getOriginalList());
        System.out.println("Treatment data saved to text file.");
    }

    public ArrayList<Result> getBestResults() {return resultControl.getBestResults();}
}
