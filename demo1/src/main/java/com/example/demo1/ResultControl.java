package com.example.demo1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.text.DecimalFormat;
import java.util.*;

import static com.example.demo1.LoginController.menu;

public class ResultControl {
    ObservableList<Print> ResultsTable =FXCollections.observableArrayList() ;
    ObservableList<Print> BestTable =FXCollections.observableArrayList() ;

    ArrayList<Result> BestResults=new ArrayList<>();
    Tech[] tech;
    int standard;
    Initial initial;
    SharedData sharedData;

    public ResultControl(SharedData sharedData){
        this.sharedData = sharedData;
        this.tech = new Tech[5];
    }

    public void calculateResults(Initial initial){
        this.initial=initial;
        Tech[] tech = new Tech[5];
        sharedData.getResults().clear();
        LinkedHashMap<String, LinkedHashMap<String,Tech>> selectedList = sharedData.getSelectedList();

        for(Map.Entry<String, Tech> primary : selectedList.get("PRELIMINARY").entrySet()) {
            tech[0] = primary.getValue();
            for (Map.Entry<String, Tech> chemical : selectedList.get("CHEMICAL").entrySet()) {
                tech[1] = chemical.getValue();
                for (Map.Entry<String, Tech> biological : selectedList.get("BIOLOGICAL").entrySet()) {
                    tech[2] = biological.getValue();
                    for (Map.Entry<String, Tech> tertiary : selectedList.get("TERTIARY").entrySet()) {
                        tech[3] = tertiary.getValue();
                        for (Map.Entry<String, Tech> sludge : selectedList.get("SLUDGE").entrySet()){
                            tech[4] = sludge.getValue();
                            sharedData.getResults().add(new Result(tech,initial));
                        }
                    }
                }
            }
        }
    }

    public void UniFormSearch(int i){
        Tech[] tech = new Tech[5];
        BestResults.clear();
        LinkedHashMap<String,Tech> BestCombination;

        BestCombination=menu.uniformCost(i);
        tech[0]=BestCombination.get("PRELIMINARY");
        tech[1]=BestCombination.get("CHEMICAL");
        tech[2]=BestCombination.get("BIOLOGICAL");
        tech[3]=BestCombination.get("TERTIARY");
        tech[4]=BestCombination.get("SLUDGE");

        BestResults.add(new Result(tech,initial));
        BestTable.clear();
        for(Result print : BestResults)
            BestTable.add(new Print(print.getTreatments()[0], print.getTreatments()[1], print.getTreatments()[2], print.getTreatments()[3], print.getTreatments()[4], print.getFinalTSS(), print.getFinalCOD(), print.getFinalBOD(), print.getFinalCost(), print.getCOD(), print.getBOD(), print.getTSS(), print.getFinalEnergy(), print.getFinalArea()));
    }

    public double getStandardNum(int standard, int type){
        double[][] standards = {{50,50,20},{100,100,50}};
        return standards[standard][type];
    }

    public void printResults(int standard){
        this.standard=standard;
        ResultsTable.clear();
        for(Result print : sharedData.getResults()) {
            if (print.getFinalTSS() <= getStandardNum(standard,0) && print.getFinalCOD() <= getStandardNum(standard,1) && print.getFinalBOD() <= getStandardNum(standard,2))
                ResultsTable.add(new Print(print.getTreatments()[0], print.getTreatments()[1], print.getTreatments()[2], print.getTreatments()[3], print.getTreatments()[4], print.getFinalTSS(), print.getFinalCOD(), print.getFinalBOD(), print.getFinalCost(), print.getCOD(), print.getBOD(), print.getTSS(),print.getFinalEnergy(),print.getFinalArea()));
        }
    }

    public ObservableList<Print> getResultsTable() {
        return ResultsTable;
    }
    public ObservableList<Print> getBestTable() {return BestTable;}
    public ArrayList<Result> getBestResults() {return BestResults;}
}
