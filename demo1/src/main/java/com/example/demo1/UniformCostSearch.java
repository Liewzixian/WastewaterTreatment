package com.example.demo1;

import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * Public class UniformCostSearch is used perform uniform cost search to get the best combination of tech for a specific
 * criteria.
 *
 * @author Group 2A
 * @version 1.0
 * @since 21/04/2022
 */
public class UniformCostSearch {

    /**
     * Priority queue to hold the nodes
     */
    private final PriorityQueue<Node> priorityQueue;
    /**
     * Linked list to hold all nodes
     */
    private final LinkedList<Node>[] adjacencyList;
    /**
     * Linked list to hold best path
     */
    private final LinkedList<Integer> path;
    /**
     * Array to hold the shortest cost of a path
     */
    private final int[] distances;
    /**
     * Array to hold the shortest parent of a node
     */
    private final int[] parent;
    /**
     * MAX_VALUE to denote that a path is unconnected between two node
     */
    public static final int MAX_VALUE = 99999;

    /**
     * This constructor initialises a priority queue to be used in the uniform cost search with the given number
     * of nodes.
     * @param vertices number of nodes
     */
    @SuppressWarnings("unchecked")
    public UniformCostSearch(int vertices){
        this.priorityQueue = new PriorityQueue<>(vertices, new Node());
        this.adjacencyList = new LinkedList[vertices];
        this.distances = new int[vertices];
        this.parent = new int[vertices];
        this.path = new LinkedList<>();
        for (int i = 0; i <vertices ; i++) {
            adjacencyList[i] = new LinkedList<>();
        }
    }

    /**
     * This method finds the shortest path of all the nodes to each other.
     * @param source the first node of the adjacency list
     */
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

    /**
     * This method returns the best combination of tech which has generated the shortest path.
     * @param source the starting node
     * @param destination the final node
     * @return an array of integer denoting the best combination of tech for a given criteria
     */
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

    /**
     * This method adds a new path to be used in the uniform cost search.
     * @param source starting node
     * @param destination ending node
     * @param weight path cost between the two nodes
     */
    public void addEdge(int source, int destination, int weight) { //add edges for uniform cost search
        Node node = new Node(source, destination, weight);
        adjacencyList[source].addLast(node); 
    }

    /**
     * Public class Node is used to hold information about the path cost between two nodes.
     *
     * @author Group 2A
     * @version 1.0
     * @since 21/04/2022
     */
    static class Node implements Comparator<Node> {
        /**
         * starting node
         */
        int source;
        /**
         * ending node
         */
        int destination;
        /**
         * path cost between the two nodes
         */
        int weight;

        /**
         * This constructor is created as a placeholder.
         */
        public Node() {}

        /**
         * This constructor creates a new node to be used in the uniform cost search.
         * @param source starting node
         * @param destination ending node
         * @param weight path cost between the two nodes
         */
        public Node(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }

        /**
         * This method compares the path cost of two nodes.
         * @param o1 the first object to be compared.
         * @param o2 the second object to be compared.
         * @return an integer denoting the order of the nodes.
         */
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

        /**
         * This method checks to see if the object specified is a Node object.
         * @param obj the reference object with which to compare.
         * @return boolean denoting if the specified object is a Node object.
         */
        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Node edge) {
                return (this.destination == edge.destination && this.source == edge.source);
            }
            return false;
        }
    }
}

