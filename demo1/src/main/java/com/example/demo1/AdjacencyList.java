package com.example.demo1;
import java.util.*;

public class AdjacencyList {

    LinkedHashMap<String, LinkedHashMap<String,Tech>> fullList;

    public AdjacencyList(LinkedHashMap<String,LinkedHashMap<String,Tech>> fullList){
        this.fullList = fullList;
    }

    public void UniformCostSearch(int choice){

        LinkedList<Tech> newTech = new LinkedList<>(); //linked list to hold treatment plans

        int[] index = new int[6]; //array of int to hold index of the final code of each type
        int[] path;
        int distance;

        System.out.println();

        index[0] = 0;
        index[1] = fullList.get("PRELIMINARY").size();
        index[2] = index[1] + fullList.get("CHEMICAL").size();
        index[3] = index[2] + fullList.get("BIOLOGICAL").size();
        index[4] = index[3] + fullList.get("TERTIARY").size();
        index[5] = index[4] + fullList.get("SLUDGE").size();

        WeightedGraph weightedGraph = new WeightedGraph(index[5]+2);

        for(int loop = 0; loop <= index[5]; loop++) {

            if(loop==0){

                Set<String> keys = fullList.get("PRELIMINARY").keySet();
                List<String> listKeys = new ArrayList<>(keys);

                for(Map.Entry<String, Tech> list : fullList.get("PRELIMINARY").entrySet()) {
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

                Set<String> keys = fullList.get(treatments[treatmentType]).keySet();
                List<String> listKeys = new ArrayList<>(keys);

                for(Map.Entry<String, Tech> list : fullList.get(treatments[treatmentType]).entrySet()) {
                    weightedGraph.addEdge(loop, index[treatmentType] + listKeys.indexOf(list.getValue().getName())+1, getWeight(list.getValue(),choice));
                }
            }
        }

        distance = weightedGraph.uniformCostSearch(0, index[5]+1);
        path = weightedGraph.printPath(0,index[5]+1);

        System.out.println();

        for(int i = 1; i < 6; i++) {
            String[] treatments = {"PRELIMINARY","CHEMICAL","BIOLOGICAL","TERTIARY","SLUDGE"};
            Collection<Tech> keys = fullList.get(treatments[i-1]).values();
            List<Tech> listKeys = new ArrayList<>(keys);
            newTech.add(listKeys.get(path[i] - index[i - 1] - 1));
        }

        System.out.println();

        double TSS = 1000, BOD = 1000, COD = 1000, cost = 0;
        String[] names = new String[5];
        int currentNum = 0;

        for(Tech calculate : newTech){
            System.out.format("%S %.2f %.2f %.2f %.2f %.3f\n", calculate.getName(), calculate.getTSS(), calculate.getCOD(), calculate.getBOD(), calculate.getArea(), calculate.getEnergy());
            TSS = TSS*(1-calculate.getTSS()); //get final TSS
            BOD = BOD*(1-calculate.getBOD()); //get final BOD
            COD = COD*(1-calculate.getCOD()); //get final COD
            cost = cost + calculate.getArea() * calculate.getEnergy();
            names[currentNum] = calculate.getName();
            currentNum++;
        }

        System.out.println();

        System.out.format("%-15S %-30S %-30S %-50S %-20S %-4S %-5S %-5S %-5S\n", "Preliminary", "Chemical", "Biological", "Tertiary", "Sludge", "TSS","COD","BOD","Cost");
        System.out.format("%-15S %-30S %-30S %-50S %-20S %4.2f %5.2f %5.2f %5.2f\n",names[0],names[1],names[2],names[3],names[4],TSS,BOD,COD,cost);

        System.out.println("\nThe Distance between source " + 0 + " and destination " + (index[5]+1) + " is " + distance);
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
