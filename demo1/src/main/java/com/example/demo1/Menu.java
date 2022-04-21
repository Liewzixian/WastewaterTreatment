package com.example.demo1;

import com.example.demo1.dataclasses.PollutionLevels;
import com.example.demo1.dataclasses.Print;
import com.example.demo1.dataclasses.Result;
import javafx.collections.ObservableList;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Menu {
    ResultControl resultControl; //generate results
    SharedData sharedData; //data class
    IO io; //input/output

    public Menu(String fileName) throws FileNotFoundException { //main menu
        io = new IO(fileName);
        sharedData = new SharedData(io);
    }
    public void addResultsToTable(PollutionLevels pollutionLevels, int standard){ //add results to table
        resultControl = new ResultControl(sharedData);
        resultControl.calculateResults(pollutionLevels);
        resultControl.printResults(standard);
    }
    public ObservableList<Print> getResultsTable() {
        return resultControl.getResultsTable();
    } //return results table
    public ObservableList<Print> getBestTable() {return resultControl.getBestTable();} //return best results table
    public void UniformSearch(int i){ resultControl.UniFormSearch(i);} //uniform cost search
    public ArrayList<Result> getBestResults() {return resultControl.getBestResults();} //return best results array
}
