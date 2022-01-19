package Coursework;

import Coursework.DataClasses.Tech;

import java.util.ArrayList;

public class TechControl {

    ArrayList<ArrayList<Tech>> fullList;

    public TechControl(ArrayList<ArrayList<Tech>> fullList){
        this.fullList = fullList;
    }

    public void addEntry(int type, Tech newTech) {
        fullList.get(type-1).add(newTech);
        System.out.println("New entry added");
    }

    public void deleteEntry(int type, int code){
        if(fullList.get(type-1).size()==1)
            System.out.println("Entry is the last entry of the list");
        else {
            fullList.get(type-1).remove(code-1);
            System.out.println("Entry removed");
        }
    }

    public void changeEntry(int type, int code, int choice, String newEntry){

        Tech temp = fullList.get(type-1).get(code-1);

        if(choice==1 && fullList.get(type-1).size()>1) {
            fullList.get(type-1).remove(code-1);
            fullList.get(Integer.parseInt(newEntry)-1).add(temp);
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

        if(choice!=1 || fullList.get(type-1).size()>1) {
            fullList.get(type-1).set(code-1,temp);
            System.out.println("Entry changed");
        }
        else
            System.out.println("Entry is the last entry of the list");
    }

    public void showAllTreatments(){
        String[] treatments = {"Preliminary","Chemical","Biological","Tertiary","Sludge"};
        for(ArrayList<Tech> full : fullList) {
            System.out.format("\n%S (Type:%d)\n",treatments[fullList.indexOf(full)],fullList.indexOf(full)+1);
            for (Tech list : full)
                System.out.format("%d,%S,%.2f,%.2f,%.2f,%.2f,%.3f\n", full.indexOf(list)+1, list.getName(), list.getTSS(), list.getCOD(), list.getBOD(), list.getArea(), list.getEnergy());
        }
    }
}
