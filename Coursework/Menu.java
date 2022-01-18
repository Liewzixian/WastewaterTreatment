package Coursework;

import Coursework.DataClasses.Result;
import Coursework.DataClasses.Tech;
import Coursework.PathingAlgorithm.AdjacencyList;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

public class Menu {

    TechControl techControl;
    ResultControl resultControl;

    IO io;
    boolean changed;

    LinkedList<Result> results;
    AdjacencyList adjacencyList;
    TreeMap<Integer,TreeMap<Integer,Tech>> fullList;

    public Menu(String fileName) {

        this.fullList = new TreeMap<>();
        this.results = new LinkedList<>();
        this.adjacencyList = new AdjacencyList(fullList);

        for(int i = 1; i <= 5; i++){
            fullList.put(i, new TreeMap<>());
        }

        io = new IO(fileName);
        techControl = new TechControl(fullList);
        resultControl = new ResultControl(fullList,results);
        changed = false;
    }

    public void load() throws FileNotFoundException {
        io.load(fullList);
        System.out.println("Treatment data loaded to linked list.");
    }

    public void add(int type, Tech newTech){
        techControl.addEntry(type,newTech);
        techControl.renumberList(fullList.get(type),type);
        changed = true;
    }

    public void delete(int type, int code){
        techControl.deleteEntry(type,code);
        techControl.renumberList(fullList.get(type),type);
        changed = true;
    }

    public void change(int type, int code, int choice, String newEntry){
        techControl.changeEntry(type,code,choice,newEntry);
        if(choice==1) {
            techControl.renumberList(fullList.get(type), type);
            techControl.renumberList(fullList.get(Integer.parseInt(newEntry)), Integer.parseInt(newEntry));
        }
        changed = true;
    }

    public void showAllTreatments(){
        String[] treatments = {"Preliminary","Chemical","Biological","Tertiary","Sludge"};
        for(Map.Entry<Integer, TreeMap<Integer,Tech>> full : fullList.entrySet()) {
            System.out.format("\n%S (Type:%d)\n",treatments[full.getKey()-1],full.getKey());
            for (Map.Entry<Integer, Tech> list : full.getValue().entrySet())
                System.out.format("%d,%S,%.2f,%.2f,%.2f,%.2f,%.3f\n", list.getKey(), list.getValue().getName(), list.getValue().getTSS(), list.getValue().getCOD(), list.getValue().getBOD(), list.getValue().getArea(), list.getValue().getEnergy());
        }
        System.out.println();
    }

    public boolean getCode(String choice){
        return resultControl.getCode(choice);
    }

    public void getSpecificResult(double TSS, double COD, double BOD){
        resultControl.getSpecificResult(TSS,COD,BOD);
    }

    public void showAllResults(double TSS, double COD, double BOD){
        results.clear();
        resultControl.calculateResults(TSS,COD,BOD);
        for(Result print : results)
            System.out.format("%-15S %-30S %-30S %-50S %-20S %4.2f %5.2f %5.2f %5.2f\n", print.getT1(), print.getT2(), print.getT3(), print.getT4(), print.getT5(), print.getTSS(),print.getCOD(),print.getBOD(), print.getCost());
        changed = false;
    }

    public boolean checkResults(){
        return (results.size()==0 || changed);
    }

    public void sortResults(int type, int order){
        if(checkResults()) { //if list empty or list changed
            results.clear();
            resultControl.calculateResults(1000, 1000, 1000); //default of 1000
        }
        resultControl.sortResults(type,order);
        for(Result print : results)
            System.out.format("%-15S %-30S %-30S %-50S %-20S %4.2f %5.2f %5.2f %5.2f\n", print.getT1(), print.getT2(), print.getT3(), print.getT4(), print.getT5(), print.getTSS(),print.getCOD(),print.getBOD(), print.getCost());
        changed = false;
    }

    public void uniformCost(double weight){
        adjacencyList.UniformCostSearch(weight);
    }

    public void save() throws IOException {
        io.save(fullList);
        System.out.println("Treatment data saved to text file.");
    }
}
