package Coursework;

import java.util.*;

public class WeightedGraph {

    private final PriorityQueue<Edge> priorityQueue;
    private final LinkedList<Edge>[] adjacencyList;
    private final LinkedList<Integer> path;
    private final int[] distances;
    private final int[] parent;
    private final int vertices;
    public static final int MAX_VALUE = 99999;

    @SuppressWarnings("unchecked")
    public WeightedGraph(int vertices){
        this.vertices = vertices;
        this.priorityQueue = new PriorityQueue<>(vertices, new Edge());
        this.adjacencyList = new LinkedList[vertices];
        this.distances = new int[vertices];
        this.parent = new int[vertices];
        this.path = new LinkedList<>();
        //initialize adjacency lists for all the vertices
        for (int i = 0; i <vertices ; i++) {
            adjacencyList[i] = new LinkedList<>();
        }
    }

    public int uniformCostSearch(int source, int destination){
        Edge currentNode;

        for (int i = 0; i < adjacencyList.length; i++) {
            distances[i] = MAX_VALUE;
        }

        System.out.println();
        for(int print :distances){
            System.out.format("%d ",print);
        }
        System.out.println();

        priorityQueue.add(new Edge(source,source,0));
        distances[source] = 0;

        while (!priorityQueue.isEmpty()) {
            currentNode = priorityQueue.peek();
            System.out.println("The eval Node is " + currentNode.destination);
            priorityQueue.poll();
            for(Edge child : adjacencyList[currentNode.destination]){
                if(distances[child.destination] > (child.weight + distances[currentNode.destination])){
                    priorityQueue.add(child);
                    distances[child.destination] = child.weight + distances[currentNode.destination];
                    parent[child.destination] = currentNode.destination;
                }
            }
        }

        int current = destination;
        while (current!=0){
            path.add(current);
            current = parent[current];
        }
        System.out.println();
        for(int print :distances){
            System.out.format("%d ",print);
        }
        System.out.println();
        for(int print :parent){
            System.out.format("%d ",print);
        }
        System.out.println();
        return distances[destination];
    }

    public void addEdge(int source, int destination, int weight) {
        Edge edge = new Edge(source, destination, weight);
        adjacencyList[source].addLast(edge); //for directed graph
    }

    public void printGraph(){
        for (int i = 0; i <vertices ; i++) {
            LinkedList<Edge> list = adjacencyList[i];
            for (Edge edge : list) {
                System.out.println("vertex-" + i + " is connected to " +
                        edge.destination + " with weight " + edge.weight);
            }
        }
    }

    static class Edge implements Comparator<Edge> {
        int source;
        int destination;
        int weight;

        public Edge() {

        }

        public Edge(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }

        @Override
        public int compare(Edge o1, Edge o2) {
            if (o1.weight < o2.weight)
                return -1;
            if (o1.weight > o2.weight)
                return 1;
            if (o1.destination < o2.destination)
                return -1;
            return 0;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Edge edge) {
                return this.destination == edge.destination;
            }
            return false;
        }
    }
}
