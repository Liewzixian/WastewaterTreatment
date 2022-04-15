package com.example.demo1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.*;

import static com.example.demo1.LoginController.menu;

public class ResultControl {

    ArrayList<ArrayList<Comparator<Result>>> comparators;
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
        this.comparators = new ArrayList<>(4);

        Comparator<Result> TssAscending = Comparator.comparing(Result::getFinalTSS);
        Comparator<Result> CodAscending = Comparator.comparing(Result::getFinalCOD);
        Comparator<Result> BodAscending = Comparator.comparing(Result::getFinalBOD);
        Comparator<Result> CostAscending = Comparator.comparing(Result::getFinalCost);

        for(int i = 0; i < 4; i++)
            comparators.add(new ArrayList<>(2));

        comparators.get(0).add(TssAscending);
        comparators.get(0).add(TssAscending.reversed());
        comparators.get(1).add(CodAscending);
        comparators.get(1).add(CodAscending.reversed());
        comparators.get(2).add(BodAscending);
        comparators.get(2).add(BodAscending.reversed());
        comparators.get(3).add(CostAscending);
        comparators.get(3).add(CostAscending.reversed());
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
        for(Result print : BestResults) {
            if (standard == 3 || (print.getFinalTSS() <= getStandardNum(standard,0) && print.getFinalCOD() <= getStandardNum(standard,1) && print.getFinalBOD() <= getStandardNum(standard,2)))
                BestTable.add(new Print(print.getTreatments()[0], print.getTreatments()[1], print.getTreatments()[2], print.getTreatments()[3], print.getTreatments()[4], print.getFinalTSS(), print.getFinalCOD(), print.getFinalBOD(), print.getFinalCost(), print.getCOD(), print.getBOD(), print.getTSS()));
        }

    }

    public void sortResults(int type, int order) {
        sharedData.getResults().sort(comparators.get(type-1).get(order-1));
    }

    public boolean getCode(String choice){
        int[] code = new int[5];
        String[] treatments = {"PRELIMINARY","CHEMICAL","BIOLOGICAL","TERTIARY","SLUDGE"};
        try {
            for (int i = 0; i < 5; i++) {
                Set<String> keys = sharedData.getOriginalList().get(treatments[i]).keySet();
                List<String> listKeys = new ArrayList<>(keys);
                code[i] = Integer.parseInt(choice.charAt(i) + "");
                tech[i] = sharedData.getOriginalList().get(treatments[i]).get(listKeys.get(code[i]-1));
            }
        }
        catch (IndexOutOfBoundsException e){
            return false;
        }
        return true;
    }

    public void getSpecificResult(Initial initial){
        String[] standard = {"A","B"};
        double TSS = initial.getTSS(), COD = initial.getCOD(), BOD = initial.getBOD(), cost = 0;

        for(int i = 0; i < 5; i++){
            TSS = TSS * (1 - tech[i].getTSS());
            COD = COD * (1 - tech[i].getCOD());
            BOD = BOD * (1 - tech[i].getBOD());
            cost = cost + (tech[i].getArea() * tech[i].getEnergy());
        }
        System.out.format("%-15S %-30S %-30S %-50S %-20S %4.2f %5.2f %5.2f %5.2f\n", tech[0].getName(), tech[1].getName(), tech[2].getName(), tech[3].getName(), tech[4].getName(), TSS, BOD, COD, cost); //print out results

        for(int i = 1; i <= 2; i++)
            if(TSS <= getStandardNum(i,0) && COD <= getStandardNum(i,1) && BOD <= getStandardNum(i,2))
                System.out.println("Standard " + standard[i-1] + " satisfied.");
            else
                System.out.println("Standard " + standard[i-1] + " not satisfied.");
    }

    public void getSortResult(int type, int order, int standard){
        String[] sortType = {"TSS","BOD","COD","Cost"};
        String[] sortOrder = {"Ascending","Descending"};
        String[] sortStandard = {"Standard A","Standard B","No Standard"};
        System.out.format("Results sorted by " + sortType[type-1] + " in " + sortOrder[order-1] + " under " + sortStandard[standard-1]);
    }

    public double getStandardNum(int standard, int type){
        double[][] standards = {{1,10,10},{1,10,10},{0,0,0}};
        return standards[standard-1][type];
    }

    public void printResults(int standard){
        this.standard=standard;
        ResultsTable.clear();
        for(Result print : sharedData.getResults()) {
            if (standard == 3 || (print.getFinalTSS() <= getStandardNum(standard,0) && print.getFinalCOD() <= getStandardNum(standard,1) && print.getFinalBOD() <= getStandardNum(standard,2)))
                ResultsTable.add(new Print(print.getTreatments()[0], print.getTreatments()[1], print.getTreatments()[2], print.getTreatments()[3], print.getTreatments()[4], print.getFinalTSS(), print.getFinalCOD(), print.getFinalBOD(), print.getFinalCost(), print.getCOD(), print.getBOD(), print.getTSS()));
        }
    }

    public ObservableList<Print> getResultsTable() {
        return ResultsTable;
    }
    public ObservableList<Print> getBestTable() {return BestTable;}
    public ArrayList<Result> getBestResults() {return BestResults;}
}
