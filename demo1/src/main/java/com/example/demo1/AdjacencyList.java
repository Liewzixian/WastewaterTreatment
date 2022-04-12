package com.example.demo1;
import java.util.*;

public class AdjacencyList {

    public LinkedHashMap<String,LinkedHashMap<String,Tech>> UniformCostSearch(int choice){

        LinkedHashMap<String,LinkedHashMap<String,Tech>> best = new LinkedHashMap<>();

        int[] index = new int[6]; //array of int to hold index of the final code of each type
        int[] path;

        index[0] = 0;
        index[1] = Menu.fullList.get("PRELIMINARY").size();
        index[2] = index[1] + Menu.fullList.get("CHEMICAL").size();
        index[3] = index[2] + Menu.fullList.get("BIOLOGICAL").size();
        index[4] = index[3] + Menu.fullList.get("TERTIARY").size();
        index[5] = index[4] + Menu.fullList.get("SLUDGE").size();

        WeightedGraph weightedGraph = new WeightedGraph(index[5]+2);

        for(int loop = 0; loop <= index[5]; loop++) {

            if(loop==0){

                Set<String> keys = Menu.fullList.get("PRELIMINARY").keySet();
                List<String> listKeys = new ArrayList<>(keys);

                for(Map.Entry<String, Tech> list : Menu.fullList.get("PRELIMINARY").entrySet()) {
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

                Set<String> keys = Menu.fullList.get(treatments[treatmentType]).keySet();
                List<String> listKeys = new ArrayList<>(keys);

                for(Map.Entry<String, Tech> list : Menu.fullList.get(treatments[treatmentType]).entrySet()) {
                    weightedGraph.addEdge(loop, index[treatmentType] + listKeys.indexOf(list.getValue().getName())+1, getWeight(list.getValue(),choice));
                }
            }
        }

        weightedGraph.uniformCostSearch(0, index[5]+1);
        path = weightedGraph.printPath(0,index[5]+1);

        for(int i = 1; i < 6; i++) {
            String[] treatments = {"PRELIMINARY","CHEMICAL","BIOLOGICAL","TERTIARY","SLUDGE"};
            Collection<Tech> keys = Menu.fullList.get(treatments[i-1]).values();
            List<Tech> listKeys = new ArrayList<>(keys);
            best.computeIfAbsent(treatments[i-1],k -> new LinkedHashMap<>());
            best.get(treatments[i-1]).put(listKeys.get(path[i]-index[i-1]-1).getName(),listKeys.get(path[i]-index[i-1]-1));
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
