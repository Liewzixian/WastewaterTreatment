package com.example.demo1;

import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class UniformCostSearch {

    private final PriorityQueue<Node> priorityQueue;
    private final LinkedList<Node>[] adjacencyList;
    private final LinkedList<Integer> path;
    private final int[] distances;
    private final int[] parent;
    public static final int MAX_VALUE = 99999;

    @SuppressWarnings("unchecked")
    public UniformCostSearch(int vertices){ //constructor
        this.priorityQueue = new PriorityQueue<>(vertices, new Node());
        this.adjacencyList = new LinkedList[vertices];
        this.distances = new int[vertices];
        this.parent = new int[vertices];
        this.path = new LinkedList<>();
        for (int i = 0; i <vertices ; i++) {
            adjacencyList[i] = new LinkedList<>();
        }
    }

    public void findBestCombination(int source){ //uniform cost search
        Node currentNode;

        for (int i = 0; i < adjacencyList.length; i++) { //initialise initial path to MAX
            distances[i] = MAX_VALUE;
        }

        priorityQueue.add(new Node(source,source,0)); //add first node
        distances[source] = 0;

        while (!priorityQueue.isEmpty()) { //uniform cost search
            currentNode = priorityQueue.peek();
            priorityQueue.poll();
            for(Node child : adjacencyList[currentNode.destination]){
                if(distances[child.destination] > (child.weight + distances[currentNode.destination])){
                    priorityQueue.add(child);
                    distances[child.destination] = child.weight + distances[currentNode.destination];
                    parent[child.destination] = currentNode.destination;
                }
            }
        }
    }

    public int[] returnBestCombination(int source,int destination){ //return best path
        int[] jobs = new int[7];
        path.add(destination); //start from last node
        boolean found = false;
        int vertex = destination;
        while (!found) {
            if (vertex == source) {
                found = true;
                continue;
            }
            path.add(parent[vertex]);
            vertex = parent[vertex];
        }

        Iterator<Integer> iterator = path.descendingIterator(); //reverse path

        int i = 0;
        while (iterator.hasNext()) //add path to list
            jobs[i++] = iterator.next();

        return jobs; //return path
    }

    public void addEdge(int source, int destination, int weight) { //add edges for uniform cost search
        Node node = new Node(source, destination, weight);
        adjacencyList[source].addLast(node); 
    }

    static class Node implements Comparator<Node> { //edge data class
        int source;
        int destination;
        int weight;

        public Node() {}

        public Node(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }

        @Override
        public int compare(Node o1, Node o2) {
            if (o1.weight < o2.weight)
                return -1;
            else if (o1.weight > o2.weight)
                return 1;
            else if (o1.destination < o2.destination)
                return -1;
            return 0;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Node edge) {
                return (this.destination == edge.destination && this.source == edge.source);
            }
            return false;
        }
    }
}

