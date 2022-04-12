package com.example.demo1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.*;

public class TechControl {

    ObservableList<Print> SelectionTable = FXCollections.observableArrayList();

    public boolean addEntry(String type, Tech newTech) {
        Menu.fullList.get(type).put(newTech.getName(),newTech);
        System.out.println("New entry added");
        return true;
    }

    public boolean deleteEntry(String type, String name){

        if(Menu.fullList.get(type).size()==1) {
            System.out.println("Entry is the last entry of the list");
            return false;
        }
        else {
            Menu.fullList.get(type).remove(name);
            System.out.println("Entry removed");
            return true;
        }
    }

    public boolean changeEntry(String type, String name, int choice, String newEntry){

        String[] treatments = {"PRELIMINARY","CHEMICAL","BIOLOGICAL","TERTIARY","SLUDGE"};

        Tech temp = Menu.fullList.get(type).get(name);
        boolean changed = false;

        if(choice==1 && Menu.fullList.get(type).size()>1) {
            Menu.fullList.get(type).remove(name);
            Menu.fullList.get(treatments[Integer.parseInt(newEntry)-1]).put(temp.getName(),temp);
            changed = true;
        }
        else if(choice==2)
            temp.setName(newEntry); //change old name to new
        else if(choice==3)
            temp.setTSS(Double.parseDouble(newEntry)); //change old value to new
        else if(choice==4)
            temp.setCOD(Double.parseDouble(newEntry)); //change old value to new
        else if(choice==5)
            temp.setBOD(Double.parseDouble(newEntry)); //change old value to new
        else if(choice==6)
            temp.setArea(Double.parseDouble(newEntry)); //change old value to new
        else if(choice==7)
            temp.setEnergy(Double.parseDouble(newEntry)); //change old value to new

        if(choice!=1 || changed) {
            Menu.fullList.get(type).replace(name,temp);
            System.out.println("Entry changed");
            return true;
        }
        else {
            System.out.println("Entry is the last entry of the list");
            return false;
        }
    }

    public void showAllTreatments(){
        for(Map.Entry<String, LinkedHashMap<String, Tech>> full : Menu.fullList.entrySet()) {
            for(Map.Entry<String, Tech> list : full.getValue().entrySet())
                 SelectionTable.add(new Print (list.getValue().getType(),list.getKey()));
        }
    }

    public void Clear(){
        Menu.fullList.clear();
    }

    public ObservableList<Print> getSelectionTable() {
        return SelectionTable;
    }

}
