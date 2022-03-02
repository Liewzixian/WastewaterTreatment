package com.example.demo1;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class TechControl {

    ArrayList<ArrayList<Tech>> fullList;

    ObservableList<Print> SelectionTable = FXCollections.observableArrayList() ;
     int stage;
    public TechControl(ArrayList<ArrayList<Tech>> fullList){
        this.fullList = fullList;
    }

    public boolean addEntry(int type, Tech newTech) {
        fullList.get(type-1).add(newTech);
        System.out.println("New entry added");
        return true;
    }

    public boolean deleteEntry(int type, int code){
        if(fullList.get(type-1).size()==1) {
            System.out.println("Entry is the last entry of the list");
            return false;
        }
        else {
            fullList.get(type-1).remove(code-1);
            System.out.println("Entry removed");
            return true;
        }
    }

    public boolean changeEntry(int type, int code, int choice, String newEntry){

        Tech temp = fullList.get(type-1).get(code-1);
        boolean changed = false;

        if(choice==1 && fullList.get(type-1).size()>1) {
            fullList.get(type-1).remove(code-1);
            fullList.get(Integer.parseInt(newEntry)-1).add(temp);
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
            fullList.get(type-1).set(code-1,temp);
            System.out.println("Entry changed");
            return true;
        }
        else {
            System.out.println("Entry is the last entry of the list");
            return false;
        }
    }

    public void showAllTreatments(){
        String[] treatments = {"Preliminary","Chemical","Biological","Tertiary","Sludge"};
        for(ArrayList<Tech> full : fullList) {
            stage=fullList.indexOf(full)+1;
            for (Tech list : full)
                 SelectionTable.add(new Print (stage,list.getName()));
        }
    }

    public void Clear(){
        fullList.clear();
    }
    public ObservableList<Print> getSelectionTable() {
        return SelectionTable;
    }


}
