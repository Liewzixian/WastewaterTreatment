package Coursework;

import Coursework.DataClasses.Initial;
import Coursework.DataClasses.Result;
import Coursework.DataClasses.Tech;

import java.util.*;

public class ResultControl {

    ArrayList<ArrayList<Comparator<Result>>> comparators;
    TreeMap<Integer, TreeMap<Integer, Tech>> fullList;
    LinkedList<Result> results;
    Tech[] tech;

    public ResultControl(TreeMap<Integer, TreeMap<Integer,Tech>> fullList, LinkedList<Result> results){
        this.fullList = fullList;
        this.results = results;
        this.tech = new Tech[5];
        this.comparators = new ArrayList<>(4);

        Comparator<Result> TssAscending = Comparator.comparing(Result::getTSS);
        Comparator<Result> CodAscending = Comparator.comparing(Result::getCOD);
        Comparator<Result> BodAscending = Comparator.comparing(Result::getBOD);
        Comparator<Result> CostAscending = Comparator.comparing(Result::getCost);

        for(int i = 0; i < 4; i++)
            comparators.add(new ArrayList<>());

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

        Tech tech1,tech2,tech3,tech4,tech5; //hold 5 elements of each type
        Result newResult; //hold final result

        results.clear();
        for(Map.Entry<Integer, Tech> primary : fullList.get(1).entrySet()) {
            tech1 = primary.getValue();
            for (Map.Entry<Integer, Tech> chemical : fullList.get(2).entrySet()) {
                tech2 = chemical.getValue();
                for (Map.Entry<Integer, Tech> biological : fullList.get(3).entrySet()) {
                    tech3 = biological.getValue();
                    for (Map.Entry<Integer, Tech> tertiary : fullList.get(4).entrySet()) {
                        tech4 = tertiary.getValue();
                        for (Map.Entry<Integer, Tech> sludge : fullList.get(5).entrySet()) {
                            tech5 = sludge.getValue();
                            newResult = new Result(tech1,tech2,tech3,tech4,tech5,initial.getTSS(),initial.getCOD(),initial.getBOD());
                            results.add(newResult);
                        }
                    }
                }
            }
        }
    }

    public void sortResults(int type, int order) {
        results.sort(comparators.get(type-1).get(order-1));
    }

    public boolean getCode(String choice){
        int[] code = new int[5];
        for(int i = 0; i < 5; i++){
            code[i] = Integer.parseInt(choice.charAt(i) + "");
            tech[i] = fullList.get(i+1).get(code[i]);
        }
        return tech[0] != null && tech[1] != null && tech[2] != null && tech[3] != null && tech[4] != null;
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
        String[] sortStandard = {"No Standard","Standard A","Standard B"};
        System.out.format("Results sorted by " + sortType[type-1] + " in " + sortOrder[order-1] + " under " + sortStandard[standard]);
    }

    public double getStandardNum(int standard, int type){
        double[][] standards = {{0,0,0},{1,10,10},{1,10,10}};
        return standards[standard][type];
    }

    public void printResults(int standard){
        for(Result print : results) {
            if (standard == 0 || (print.getTSS() <= getStandardNum(standard,0) && print.getCOD() <= getStandardNum(standard,1) && print.getBOD() <= getStandardNum(standard,2)))
                System.out.format("%-15S %-30S %-30S %-50S %-20S %4.2f %5.2f %5.2f %5.2f\n", print.getT1(), print.getT2(), print.getT3(), print.getT4(), print.getT5(), print.getTSS(), print.getCOD(), print.getBOD(), print.getCost());
        }
    }
}
