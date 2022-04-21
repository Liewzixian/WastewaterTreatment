package com.example.demo1;
import com.example.demo1.dataclasses.Tech;

import java.util.*;

public class AdjacencyList { //Adjacency list for uniform cost search
    SharedData sharedData; //data class
    public LinkedHashMap<String,Tech> UniformCostSearch(int choice, SharedData sharedData){ //uniform cost search

        LinkedHashMap<String, LinkedHashMap<String,Tech>> selectedList; //get user choice of tech
        LinkedHashMap<String,Tech> best = new LinkedHashMap<>(); //get the best combination of tech

        this.sharedData = sharedData;
        selectedList = sharedData.getSelectedList(); //get user selected tech list

        int[] index = new int[6]; //array of int to hold index of the final code of each treatment stage
        int[] bestCombination; //array of int to hold path of the least cost

        index[0] = 0; //index of 0
        index[1] = selectedList.get("PRELIMINARY").size(); //index of last element of first stage
        index[2] = index[1] + selectedList.get("CHEMICAL").size(); //index of last element of first to second stage
        index[3] = index[2] + selectedList.get("BIOLOGICAL").size(); //index of last element of first to third stage
        index[4] = index[3] + selectedList.get("TERTIARY").size(); //index of last element of first to fourth stage
        index[5] = index[4] + selectedList.get("SLUDGE").size(); //index of last element of first to fifth stage

        UniformCostSearch uniformCostSearch = new UniformCostSearch(index[5]+2); //class for uniform cost search

        for(int loop = 0; loop <= index[5]; loop++) { //link all tech no each other

            if(loop==0){ //link first node to first stage

                Set<String> keys = selectedList.get("PRELIMINARY").keySet();
                List<String> listKeys = new ArrayList<>(keys);

                for(Map.Entry<String, Tech> list : selectedList.get("PRELIMINARY").entrySet()) {
                    uniformCostSearch.addEdge(loop, listKeys.indexOf(list.getValue().getName())+1, getWeight(list.getValue(),choice));
                }
            }
            else if(loop > index[4]){ //link other stages to each other
                uniformCostSearch.addEdge(loop, index[5] + 1, 300);
            }
            else { //link last stage to last node
                int treatmentType = 1;
                String[] treatments = {"CHEMICAL","BIOLOGICAL","TERTIARY","SLUDGE"};
                while(loop > index[treatmentType])
                    treatmentType++;

                Set<String> keys = selectedList.get(treatments[treatmentType-1]).keySet();
                List<String> listKeys = new ArrayList<>(keys);

                for(Map.Entry<String, Tech> list : selectedList.get(treatments[treatmentType-1]).entrySet()) {
                    uniformCostSearch.addEdge(loop, index[treatmentType] + listKeys.indexOf(list.getValue().getName())+1, getWeight(list.getValue(),choice));
                }
            }
        }

        uniformCostSearch.findBestCombination(0); //uniform cost search
        bestCombination = uniformCostSearch.returnBestCombination(0,index[5]+1); //get best path

        for(int i = 1; i < 6; i++) { //translate the best path to get the best tech combination
            String[] treatments = {"PRELIMINARY","CHEMICAL","BIOLOGICAL","TERTIARY","SLUDGE"};
            Collection<Tech> keys = selectedList.get(treatments[i-1]).values();
            List<Tech> listKeys = new ArrayList<>(keys);
            best.put(treatments[i-1],listKeys.get(bestCombination[i]-index[i-1]-1));
        }
        return best; //return best combination of tech
    }

    public int getWeight(Tech tech, int choice){ //controls which criteria to control uniform cost search
        if(choice == 1){ //get the best overall cleaning efficiency
            return 300 - (int) (tech.getTSS() + tech.getCOD() + tech.getBOD()) * 100;
        }
        else if(choice == 2){ //get the lowest cost
            return (int) (tech.getCost());
        }else if(choice == 3){ //get the lowest energy
            return (int) (tech.getEnergy()*100);
        }else if(choice == 4){ //get the lowest area
            return (int) (tech.getArea()*100);
        }
        return 0;
    }
}
