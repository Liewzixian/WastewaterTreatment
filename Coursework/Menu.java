package Coursework;

import Coursework.DataClasses.Initial;
import Coursework.DataClasses.Result;
import Coursework.DataClasses.Tech;
import Coursework.PathingAlgorithm.AdjacencyList;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
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
        techControl.showAllTreatments();
    }

    public boolean getCode(String choice){
        return resultControl.getCode(choice);
    }

    public void getSpecificResult(Initial initial){
        resultControl.getSpecificResult(initial);
    }

    public void showAllResults(Initial initial, int standard){
        resultControl.calculateResults(initial);
        resultControl.printResults(standard);
        changed = false;
    }

    public void sortResults(int type, int order, int standard){
        if(results.size()==0 || changed)
            resultControl.calculateResults(new Initial(1000, 1000, 1000)); //default of 1000
        resultControl.sortResults(type,order);
        resultControl.printResults(standard);
        resultControl.getSortResult(type,order,standard);
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
