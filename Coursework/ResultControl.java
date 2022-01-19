package Coursework;

import Coursework.DataClasses.Result;
import Coursework.DataClasses.Tech;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

public class ResultControl {

    TreeMap<Integer, TreeMap<Integer, Tech>> fullList;
    LinkedList<Result> results;

    int c1,c2,c3,c4,c5; //5 numbers to choose 5 different types of treatment
    Tech t1,t2,t3,t4,t5;
    double TSS,COD,BOD,cost;

    Comparator<Result> TssAscending = Comparator.comparing(Result::getTSS);
    Comparator<Result> TssDescending = TssAscending.reversed();

    Comparator<Result> CodAscending = Comparator.comparing(Result::getCOD);
    Comparator<Result> CodDescending = CodAscending.reversed();

    Comparator<Result> BodAscending = Comparator.comparing(Result::getBOD);
    Comparator<Result> BodDescending = BodAscending.reversed();

    Comparator<Result> CostAscending = Comparator.comparing(Result::getCost);
    Comparator<Result> CostDescending = CostAscending.reversed();

    public ResultControl(TreeMap<Integer, TreeMap<Integer,Tech>> fullList, LinkedList<Result> results){
        this.fullList = fullList;
        this.results = results;
    }

    public void calculateResults(double TSS, double COD, double BOD){

        Tech tech1,tech2,tech3,tech4,tech5; //hold 5 elements of each type
        Result newResult; //hold final result

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
                            newResult = new Result(tech1,tech2,tech3,tech4,tech5,TSS,COD,BOD);
                            results.add(newResult);
                        }
                    }
                }
            }
        }
    }

    public void sortResults(int type, int order) {

        if (type == 1) { //sort by TSS

            if (order == 1) //ascending
                results.sort(TssAscending);
            else //descending
                results.sort(TssDescending);
        }
        else if (type == 2) { //sort by COD

            if (order == 1)  //ascending
                results.sort(CodAscending);
            else  //descending
                results.sort(CodDescending);
        }
        else if (type == 3) { //sort by BOD

            if (order == 1)  //ascending
                results.sort(BodAscending);
            else  //descending
                results.sort(BodDescending);
        }
        else { //sort by cost

            if (order == 1)  //ascending
                results.sort(CostAscending);
            else //descending
                results.sort(CostDescending);
        }
    }

    public boolean getCode(String choice){

        c1 = Integer.parseInt(choice.charAt(0) + ""); //takes 1st digit of integer
        c2 = Integer.parseInt(choice.charAt(1) + ""); //takes 2nd digit of integer
        c3 = Integer.parseInt(choice.charAt(2) + ""); //takes 3rd digit of integer
        c4 = Integer.parseInt(choice.charAt(3) + ""); //takes 4th digit of integer
        c5 = Integer.parseInt(choice.charAt(4) + ""); //takes 5th digit of integer

        t1 = fullList.get(1).get(c1);
        t2 = fullList.get(2).get(c2);
        t3 = fullList.get(3).get(c3);
        t4 = fullList.get(4).get(c4);
        t5 = fullList.get(5).get(c5);

        return t1 != null && t2 != null && t3 != null && t4 != null && t5 != null;
    }

    public void getSpecificResult(double initialTSS, double initialCOD, double initialBOD){
        String[] standard = {"A","B"};

        TSS = initialTSS * (1 - t1.getTSS()) * (1 - t2.getTSS()) * (1 - t3.getTSS()) * (1 - t4.getTSS()) * (1 - t5.getTSS());
        COD = initialCOD * (1 - t1.getCOD()) * (1 - t2.getCOD()) * (1 - t3.getCOD()) * (1 - t4.getCOD()) * (1 - t5.getCOD());
        BOD = initialBOD * (1 - t1.getBOD()) * (1 - t2.getBOD()) * (1 - t3.getBOD()) * (1 - t4.getBOD()) * (1 - t5.getBOD());
        cost = (t1.getArea() * t1.getEnergy()) + (t2.getArea() * t2.getEnergy()) + (t3.getArea() * t3.getEnergy()) + (t4.getArea() * t4.getEnergy()) + (t5.getArea() * t5.getEnergy());
        System.out.format("%-15S %-30S %-30S %-50S %-20S %4.2f %5.2f %5.2f %5.2f\n", t1.getName(), t2.getName(), t3.getName(), t4.getName(), t5.getName(), TSS, BOD, COD, cost); //print out results

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
}
