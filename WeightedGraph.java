package Coursework;

import java.util.Comparator;
import java.util.LinkedList;

public class WeightedGraph {

    public static final int MAX_VALUE = 99999;

    public WeightedGraph(){

    }

    static class Edge implements Comparator<Edge> {
        int source;
        int destination;
        int weight;

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

    @SuppressWarnings("unchecked")
    static class Graph {
        int vertices;
        LinkedList<Edge>[] adjacencyList;

        Graph(int vertices) {
            this.vertices = vertices;
            adjacencyList = new LinkedList[vertices];
            //initialize adjacency lists for all the vertices
            for (int i = 0; i <vertices ; i++) {
                adjacencyList[i] = new LinkedList<>();
            }
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
    }

    public int uniformCostSearch(Graph graph, int source, int destination){

        for (int i = 0; i < graph.vertices; i++) {
            //distances[i] = MAX_VALUE;
        }

        return 0;
    }
}
