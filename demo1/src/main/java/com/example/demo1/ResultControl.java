package com.example.demo1;

import com.example.demo1.dataclasses.PollutionLevels;
import com.example.demo1.dataclasses.Print;
import com.example.demo1.dataclasses.Result;
import com.example.demo1.dataclasses.Tech;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.example.demo1.LoginController.menu;

/**
 * Public class ResultControl is used to generated all results to be displayed to the user.
 *
 * @author Group 2A
 * @version 1.0
 * @since 21/04/2022
 */
public class ResultControl {
    /**
     * Table to show all combinations of results
     */
    ObservableList<Print> ResultsTable = FXCollections.observableArrayList();
    /**
     * Table to show the best combination of results
     */
    ObservableList<Print> BestTable = FXCollections.observableArrayList();
    /**
     * Array to hold best results
     */
    ArrayList<Result> BestResults = new ArrayList<>();
    /**
     * Adjacency list to perform uniform cost search
     */
    AdjacencyList adjacencyList;
    /**
     * Initial pollution levels
     */
    PollutionLevels pollutionLevels;
    /**
     * Data class holding the user-selected tech
     */
    SharedData sharedData;

    /**
     * This constructor loads the user-selected tech to generate all results and also perform uniform cost search.
     * @param sharedData Data class holding the user-selected tech
     */
    public ResultControl(SharedData sharedData){
        this.sharedData = sharedData;
        adjacencyList = new AdjacencyList();
    }

    /**
     * Generate results table of all tech combinations
     * @param pollutionLevels Initial pollution levels
     */
    public void calculateResults(PollutionLevels pollutionLevels){
        this.pollutionLevels = pollutionLevels;
        Tech[] tech = new Tech[5];
        sharedData.getResults().clear();
        LinkedHashMap<String, LinkedHashMap<String,Tech>> selectedList = sharedData.getSelectedList();

        for(Map.Entry<String, Tech> primary : selectedList.get("PRELIMINARY").entrySet()) { //loop through all 5 stages
            tech[0] = primary.getValue();
            for (Map.Entry<String, Tech> chemical : selectedList.get("CHEMICAL").entrySet()) {
                tech[1] = chemical.getValue();
                for (Map.Entry<String, Tech> biological : selectedList.get("BIOLOGICAL").entrySet()) {
                    tech[2] = biological.getValue();
                    for (Map.Entry<String, Tech> tertiary : selectedList.get("TERTIARY").entrySet()) {
                        tech[3] = tertiary.getValue();
                        for (Map.Entry<String, Tech> sludge : selectedList.get("SLUDGE").entrySet()){
                            tech[4] = sludge.getValue();
                            sharedData.getResults().add(new Result(tech,pollutionLevels));
                        }
                    }
                }
            }
        }
    }

    /**
     * This method performs uniform cost search to get the best combination of tech for a given criteria.
     * @param choice criteria for uniform cost search
     */
    public void UniFormSearch(int choice){
        Tech[] tech = new Tech[5];
        BestResults.clear();
        LinkedHashMap<String,Tech> BestCombination;

        BestCombination = adjacencyList.UniformCostSearch(choice,menu.sharedData);
        tech[0] = BestCombination.get("PRELIMINARY");
        tech[1] = BestCombination.get("CHEMICAL");
        tech[2] = BestCombination.get("BIOLOGICAL");
        tech[3] = BestCombination.get("TERTIARY");
        tech[4] = BestCombination.get("SLUDGE");

        BestResults.add(new Result(tech,pollutionLevels));
        BestTable.clear();
        for(Result print : BestResults)
            BestTable.add(new Print(print.getTreatments()[0], print.getTreatments()[1], print.getTreatments()[2], print.getTreatments()[3], print.getTreatments()[4], print.getFinalTSS(), print.getFinalCOD(), print.getFinalBOD(), print.getFinalCost(), print.getCOD(), print.getBOD(), print.getTSS(), print.getFinalEnergy(), print.getFinalArea()));
    }

    /**
     * This method returns the wastewater standard values to be compared with the results
     * @param standard wastewater standard
     * @param type pollution type to be compared
     * @return a double to be compared with the results
     */
    public double getStandardNum(int standard, int type){
        double[][] standards = {{50,50,20},{100,100,50}};
        return standards[standard][type];
    }

    /**
     * This method adds results to table to be displayed
     * @param standard wastewater standard to be compared
     */
    public void printResults(int standard){
        ResultsTable.clear();
        for(Result print : sharedData.getResults()) {
            if (print.getFinalCOD() <= getStandardNum(standard,0) && print.getFinalBOD() <= getStandardNum(standard,1) && print.getFinalTSS() <= getStandardNum(standard,2))
                ResultsTable.add(new Print(print.getTreatments()[0], print.getTreatments()[1], print.getTreatments()[2], print.getTreatments()[3], print.getTreatments()[4], print.getFinalTSS(), print.getFinalCOD(), print.getFinalBOD(), print.getFinalCost(), print.getCOD(), print.getBOD(), print.getTSS(),print.getFinalEnergy(),print.getFinalArea()));
        }
    }
    /**
     * This method returns results table.
     * @return results table
     */
    public ObservableList<Print> getResultsTable() {
        return ResultsTable;
    }
    /**
     * This method returns best results table.
     * @return best results table
     */
    public ObservableList<Print> getBestTable() {return BestTable;}
    /**
     * This method returns best results array.
     * @return best results array
     */
    public ArrayList<Result> getBestResults() {return BestResults;}
}