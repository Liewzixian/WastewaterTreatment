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
public class ResultControl {
    ObservableList<Print> ResultsTable = FXCollections.observableArrayList(); //table to show all combinations of results
    ObservableList<Print> BestTable = FXCollections.observableArrayList(); //table to show the best combination of results
    ArrayList<Result> BestResults = new ArrayList<>(); //array to hold best results
    AdjacencyList adjacencyList; //uniform cost search
    PollutionLevels pollutionLevels; //initial pollution levels
    SharedData sharedData; //data class

    public ResultControl(SharedData sharedData){ //constructor
        this.sharedData = sharedData;
        adjacencyList = new AdjacencyList();
    }

    public void calculateResults(PollutionLevels pollutionLevels){ //generate results table of all combinations
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

    public void UniFormSearch(int i){ //uniform cost search
        Tech[] tech = new Tech[5];
        BestResults.clear();
        LinkedHashMap<String,Tech> BestCombination;

        BestCombination = adjacencyList.UniformCostSearch(i,menu.sharedData);
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

    public double getStandardNum(int standard, int type){ //get standard values
        double[][] standards = {{50,50,20},{100,100,50}};
        return standards[standard][type];
    }

    public void printResults(int standard){ //add results to table
        ResultsTable.clear();
        for(Result print : sharedData.getResults()) {
            if (print.getFinalCOD() <= getStandardNum(standard,0) && print.getFinalBOD() <= getStandardNum(standard,1) && print.getFinalTSS() <= getStandardNum(standard,2))
                ResultsTable.add(new Print(print.getTreatments()[0], print.getTreatments()[1], print.getTreatments()[2], print.getTreatments()[3], print.getTreatments()[4], print.getFinalTSS(), print.getFinalCOD(), print.getFinalBOD(), print.getFinalCost(), print.getCOD(), print.getBOD(), print.getTSS(),print.getFinalEnergy(),print.getFinalArea()));
        }
    }
    public ObservableList<Print> getResultsTable() {
        return ResultsTable;
    }
    public ObservableList<Print> getBestTable() {return BestTable;}
    public ArrayList<Result> getBestResults() {return BestResults;}
}
