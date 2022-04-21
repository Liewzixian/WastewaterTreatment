package com.example.demo1;

import com.example.demo1.dataclasses.PollutionLevels;
import com.example.demo1.dataclasses.Print;
import com.example.demo1.dataclasses.Result;
import javafx.collections.ObservableList;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Public class Menu serves as central point for the program, holding the SharedData class which contains important
 * dynamic information.
 *
 * @author Group 2A
 * @version 1.0
 * @since 21/04/2022
 */
public class Menu {
    /**
     * Class to generate results to be shown in table
     */
    ResultControl resultControl;
    /**
     * Data class to hold important dynamic information
     */
    SharedData sharedData;
    /**
     * Input/Output file system to save and load data
     */
    IO io;

    /**
     * This constructor creates a SharedData object to hold important dynamic information and an IO system to save
     * and load data
     * @param fileName path to list of all wastewater tech
     * @throws FileNotFoundException if text file specified does not exist
     */
    public Menu(String fileName) throws FileNotFoundException { //main menu
        io = new IO(fileName);
        sharedData = new SharedData(io);
    }

    /**
     * This method adds all generated results to table to be displayed.
     * @param pollutionLevels Initial pollution levels
     * @param standard wastewater standard chosen
     */
    public void addResultsToTable(PollutionLevels pollutionLevels, int standard){ //add results to table
        resultControl = new ResultControl(sharedData);
        resultControl.calculateResults(pollutionLevels);
        resultControl.printResults(standard);
    }

    /**
     * This method returns the results table
     * @return results table
     */
    public ObservableList<Print> getResultsTable() {
        return resultControl.getResultsTable();
    }

    /**
     * This method returns the best results table
     * @return best results table
     */
    public ObservableList<Print> getBestTable() {return resultControl.getBestTable();}

    /**
     * This method performs the uniform cost search.
     * @param choice wastewater criteria to be assessed
     */
    public void UniformSearch(int choice){ resultControl.UniFormSearch(choice);}

    /**
     * This method returns best results array.
     * @return best results array
     */
    public ArrayList<Result> getBestResults() {return resultControl.getBestResults();}
}
