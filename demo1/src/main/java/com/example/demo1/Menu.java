package com.example.demo1;

import com.example.demo1.dataclasses.*;
import javafx.collections.ObservableList;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Menu {

    ResultControl resultControl;
    SharedData sharedData;
    IO io;
    ArrayList<Result> results;
    LinkedHashMap<String,LinkedHashMap<String,Location>> locations;
    AdjacencyList adjacencyList;

    public Menu(String fileName) throws FileNotFoundException {
        locations = new LinkedHashMap<>();
        results = new ArrayList<>();
        adjacencyList = new AdjacencyList();

        io = new IO(fileName);
        sharedData = new SharedData(io);
    }

    public void load() throws FileNotFoundException {
        io.loadData();
        System.out.println("Treatment data loaded to linked list.");
    }

    public void add(String type, Tech newTech){
        sharedData.getOriginalList().get(type).put(newTech.getName(),newTech);
    }

    public void showAllResults(Initial initial, int standard){
        resultControl = new ResultControl(sharedData);
        resultControl.calculateResults(initial);
        resultControl.printResults(standard);
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
