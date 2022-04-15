package com.example.demo1;
import java.util.*;

public class AdjacencyList {

    SharedData sharedData;

    public LinkedHashMap<String,Tech> UniformCostSearch(int choice, SharedData sharedData){

        LinkedHashMap<String, LinkedHashMap<String,Tech>> selectedList;
        LinkedHashMap<String,Tech> best = new LinkedHashMap<>();

        this.sharedData = sharedData;
        selectedList = sharedData.getSelectedList();

        int[] index = new int[6]; //array of int to hold index of the final code of each type
        int[] path;

        index[0] = 0;
        index[1] = selectedList.get("PRELIMINARY").size();
        index[2] = index[1] + selectedList.get("CHEMICAL").size();
        index[3] = index[2] + selectedList.get("BIOLOGICAL").size();
        index[4] = index[3] + selectedList.get("TERTIARY").size();
        index[5] = index[4] + selectedList.get("SLUDGE").size();

        WeightedGraph weightedGraph = new WeightedGraph(index[5]+2);

        for(int loop = 0; loop <= index[5]; loop++) {

            if(loop==0){

                Set<String> keys = selectedList.get("PRELIMINARY").keySet();
                List<String> listKeys = new ArrayList<>(keys);

                for(Map.Entry<String, Tech> list : selectedList.get("PRELIMINARY").entrySet()) {
                    weightedGraph.addEdge(loop, listKeys.indexOf(list.getValue().getName())+1, getWeight(list.getValue(),choice));
                }
            }
            else if(loop > index[4]){
                weightedGraph.addEdge(loop, index[5] + 1, 300);
            }
            else {
                int treatmentType = 1;
                String[] treatments = {"CHEMICAL","BIOLOGICAL","TERTIARY","SLUDGE"};
                while(loop > index[treatmentType])
                    treatmentType++;

                Set<String> keys = selectedList.get(treatments[treatmentType-1]).keySet();
                List<String> listKeys = new ArrayList<>(keys);

                for(Map.Entry<String, Tech> list : selectedList.get(treatments[treatmentType-1]).entrySet()) {
                    weightedGraph.addEdge(loop, index[treatmentType] + listKeys.indexOf(list.getValue().getName())+1, getWeight(list.getValue(),choice));
                }
            }
        }

        weightedGraph.uniformCostSearch(0, index[5]+1);
        path = weightedGraph.printPath(0,index[5]+1);

        for(int i = 1; i < 6; i++) {
            String[] treatments = {"PRELIMINARY","CHEMICAL","BIOLOGICAL","TERTIARY","SLUDGE"};
            Collection<Tech> keys = selectedList.get(treatments[i-1]).values();
            List<Tech> listKeys = new ArrayList<>(keys);
            best.put(treatments[i-1],listKeys.get(path[i]-index[i-1]-1));
        }
        return best;
    }

    public int getWeight(Tech tech, int choice){
        if(choice == 1){
            return 300 - (int) (tech.getTSS() + tech.getCOD() + tech.getBOD()) * 100;
        }
        else if(choice == 2){
            return (int) (tech.getEnergy() * tech.getArea());
        }
        return 0;
    }
}
