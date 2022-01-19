package Coursework;

import Coursework.DataClasses.Tech;

import java.util.Map;
import java.util.TreeMap;

public class TechControl {

    TreeMap<Integer, TreeMap<Integer, Tech>> fullList;

    public TechControl(TreeMap<Integer, TreeMap<Integer,Tech>> fullList){
        this.fullList = fullList;
    }

    public void addEntry(int type, Tech newTech) {
        fullList.get(type).put(fullList.get(type).size() + 1,newTech);
        System.out.println("New entry added");
    }

    public void deleteEntry(int type, int code){
        if(fullList.get(type).get(code) == null || fullList.get(type).size()==1)
            System.out.println("Entry does not exist or is last entry of the list");
        else {
            fullList.get(type).remove(code);
            System.out.println("Entry removed");
        }
    }

    public void changeEntry(int type, int code, int choice, String newEntry){

        Tech temp = fullList.get(type).get(code);

        if(temp!=null) { //if entry exists
            if(choice==1 && fullList.get(type).size()>1) {
                fullList.get(type).remove(code);
                fullList.get(Integer.parseInt(newEntry)).put(fullList.get(Integer.parseInt(newEntry)).size() + 1, temp);
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

            if(choice!=1 || fullList.get(type).size()>1) {
                fullList.get(type).replace(code, temp);
                System.out.println("Entry changed");
            }
            else
                System.out.println("Entry is the last entry of the list");
        }
        else
            System.out.println("Entry does not exist");
    }

    public void renumberList(TreeMap<Integer,Tech> list, int type){

        TreeMap<Integer,Tech> temp = new TreeMap<>();

        int current = 0;
        for (Map.Entry<Integer, Tech> newList : list.entrySet()) {
            current++;
            temp.put(current,newList.getValue());
        }

        fullList.get(type).clear();
        fullList.get(type).putAll(temp);
        temp.clear();
    }

    public void showAllTreatments(){
        String[] treatments = {"Preliminary","Chemical","Biological","Tertiary","Sludge"};
        for(Map.Entry<Integer, TreeMap<Integer,Tech>> full : fullList.entrySet()) {
            System.out.format("\n%S (Type:%d)\n",treatments[full.getKey()-1],full.getKey());
            for (Map.Entry<Integer, Tech> list : full.getValue().entrySet())
                System.out.format("%d,%S,%.2f,%.2f,%.2f,%.2f,%.3f\n", list.getKey(), list.getValue().getName(), list.getValue().getTSS(), list.getValue().getCOD(), list.getValue().getBOD(), list.getValue().getArea(), list.getValue().getEnergy());
        }
    }
}
