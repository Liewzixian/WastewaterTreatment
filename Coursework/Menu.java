package Coursework;

import Coursework.DataClasses.Initial;
import Coursework.DataClasses.Result;
import Coursework.DataClasses.Tech;
import Coursework.PathingAlgorithm.AdjacencyList;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Menu {

    TechControl techControl;
    ResultControl resultControl;

    IO io;
    boolean changed;

    ArrayList<Result> results;
    ArrayList<ArrayList<Tech>> fullList;
    AdjacencyList adjacencyList;

    public Menu(String fileName) {

        this.fullList = new ArrayList<>();
        this.results = new ArrayList<>();
        this.adjacencyList = new AdjacencyList(fullList);

        for(int i = 0; i < 5; i++){
            fullList.add(new ArrayList<>());
        }

        io = new IO(fileName,fullList);
        techControl = new TechControl(fullList);
        resultControl = new ResultControl(fullList,results);
        changed = false;
    }

    public void load() throws FileNotFoundException {
        io.load();
        System.out.println("Treatment data loaded to linked list.");
    }

    public void add(int type, Tech newTech){
        techControl.addEntry(type,newTech);
        changed = true;
    }

    public void delete(int type, int code){
        techControl.deleteEntry(type,code);
        changed = true;
    }

    public void change(int type, int code, int choice, String newEntry){
        techControl.changeEntry(type,code,choice,newEntry);
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
        io.save();
        System.out.println("Treatment data saved to text file.");
    }

    public int getSize(int type){
        return fullList.get(type-1).size();
    }
}
