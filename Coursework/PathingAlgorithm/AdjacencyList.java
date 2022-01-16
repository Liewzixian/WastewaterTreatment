package Coursework.PathingAlgorithm;

import Coursework.DataClasses.Tech;

import java.util.LinkedList;
import java.util.Scanner;

public class AdjacencyList {

    public void UniformCostSearch(LinkedList<Tech> loadTech){

        Scanner input = new Scanner(System.in);
        LinkedList<Tech> newTech = new LinkedList<>(); //linked list to hold treatment plans

        int[] index = new int[6]; //array of int to hold index of the final code of each type
        int[] path;
        int currentNum;
        int count = 0;
        int distance;
        double weight;

        while (true) {
            System.out.println("\nEnter weight:");
            weight = input.nextDouble();

            if(weight>0 && weight<1){
                break;
            }
            else {
                System.out.println("Invalid input.");
            }
        }

        System.out.println();

        for(Tech cycle : loadTech){ //get index of the final code of each type
            index[cycle.getType()]=loadTech.indexOf(cycle)+1;
            count++;
        }

        WeightedGraph weightedGraph = new WeightedGraph(count+2);

        for(int loop = 0; loop <= count; loop++) {
            currentNum = 0;
            for (Tech cycle : loadTech) {
                if((loop==0 && cycle.getType() ==1)||((loop>0 && loop<=index[1]) && cycle.getType() ==2)||((loop>index[1]&&loop<=index[2]) && cycle.getType() ==3)||((loop>index[2]&&loop<=index[3]) && cycle.getType() ==4)||((loop>index[3]&&loop<=index[4]) && cycle.getType() ==5))
                    weightedGraph.addEdge(loop,currentNum + 1,300 - (int) (((cycle.getTSS() + cycle.getCOD() + cycle.getBOD())*weight - (cycle.getEnergy() + cycle.getEnergy() /10)*(1-weight)) * 100));
                else if(loop>index[4]&&loop<=index[5]) {
                    weightedGraph.addEdge(loop, count + 1, 300);
                    break;
                }
                currentNum++;
            }
        }
        distance = weightedGraph.uniformCostSearch(0, count+1);
        path = weightedGraph.printPath(0,count+1);

        System.out.println();

        for(int i = 1; i < 6; i++){
            newTech.add(loadTech.get(path[i]-1));
        }

        System.out.println();

        double TSS = 1000, BOD = 1000, COD = 1000, cost = 0;
        String[] names = new String[5];

        currentNum = 0;
        for(Tech calculate : newTech){
            System.out.format("%d %d %S %.2f %.2f %.2f %.2f %.3f\n", calculate.getType(), calculate.getCode(), calculate.getName(), calculate.getTSS(), calculate.getCOD(), calculate.getBOD(), calculate.getArea(), calculate.getEnergy());
            TSS = TSS*(1-calculate.getTSS()); //get final TSS
            BOD = BOD*(1-calculate.getBOD()); //get final BOD
            COD = COD*(1-calculate.getCOD()); //get final COD
            cost = cost + calculate.getArea() * calculate.getEnergy();
            if(calculate.getType() ==(currentNum+1))
                names[currentNum] = calculate.getName();
            currentNum++;
        }

        System.out.println();

        System.out.format("%-15S %-30S %-30S %-50S %-20S %-4S %-5S %-5S %-5S\n", "Preliminary", "Chemical", "Biological", "Tertiary", "Sludge", "TSS","COD","BOD","Cost");
        System.out.format("%-15S %-30S %-30S %-50S %-20S %4.2f %5.2f %5.2f %5.2f\n",names[0],names[1],names[2],names[3],names[4],TSS,BOD,COD,cost);

        System.out.println("\nThe Distance between source " + 0 + " and destination " + (count+1) + " is " + distance);
    }
}
