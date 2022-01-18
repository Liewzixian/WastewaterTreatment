package Coursework.PathingAlgorithm;

import Coursework.DataClasses.Tech;

import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

public class AdjacencyList {

    TreeMap<Integer, TreeMap<Integer,Tech>> fullList;

    public AdjacencyList(TreeMap<Integer, TreeMap<Integer,Tech>> fullList){
        this.fullList = fullList;
    }

    public void UniformCostSearch(double weight){

        LinkedList<Tech> newTech = new LinkedList<>(); //linked list to hold treatment plans

        int[] index = new int[6]; //array of int to hold index of the final code of each type
        int[] path;
        int distance;

        System.out.println();

        index[0] = 0;
        index[1] = fullList.get(1).size();
        index[2] = index[1] + fullList.get(2).size();
        index[3] = index[2] + fullList.get(3).size();
        index[4] = index[3] + fullList.get(4).size();
        index[5] = index[4] + fullList.get(5).size();

        WeightedGraph weightedGraph = new WeightedGraph(index[5]+2);

        for(int loop = 0; loop <= index[5]; loop++) {

            if(loop==0){
                for(Map.Entry<Integer, Tech> list : fullList.get(1).entrySet()) {
                    weightedGraph.addEdge(loop, list.getKey(), 300 - (int) (((list.getValue().getTSS() + list.getValue().getCOD() + list.getValue().getBOD()) * weight - (list.getValue().getEnergy() + list.getValue().getEnergy() / 10) * (1 - weight)) * 100));
                }
            }
            else if(loop > index[4]){
                weightedGraph.addEdge(loop, index[5] + 1, 300);
            }
            else {
                int treatmentType = 1;
                while(loop > index[treatmentType])
                    treatmentType++;

                for(Map.Entry<Integer, Tech> list : fullList.get(treatmentType+1).entrySet()) {
                    weightedGraph.addEdge(loop, index[treatmentType] + list.getKey(), 300 - (int) (((list.getValue().getTSS() + list.getValue().getCOD() + list.getValue().getBOD()) * weight - (list.getValue().getEnergy() + list.getValue().getEnergy() / 10) * (1 - weight)) * 100));
                }
            }
        }

        distance = weightedGraph.uniformCostSearch(0, index[5]+1);
        path = weightedGraph.printPath(0,index[5]+1);

        System.out.println();

        for(int i = 1; i < 6; i++)
            newTech.add(fullList.get(i).get(path[i]-index[i-1]));

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
}
