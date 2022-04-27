package com.example.demo1;

import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * Public class UniformCostSearch searches for the best combination of wastewater technologies in a specific category
 * such as cost, cleaning efficiency, energy and more using uniform cost search.
 * Referenced and Modified code from: <a href="https://www.sanfoundry.com/java-program-implement-uniform-cost-search/">https://www.sanfoundry.com/java-program-implement-uniform-cost-search/</a>
 *
 * @author Group 2A
 * @version 1.0
 * @since 21/04/2022
 */
public class UniformCostSearch {

    /**
     * Priority queue to hold currently evaluating node
     */
    private final PriorityQueue<Node> priorityQueue;
    /**
     * Linked List to hold all possible paths between nodes
     */
    private final LinkedList<Node>[] adjacencyList;
    /**
     * Linked List to hold the shortest path between start and end node
     */
    private final LinkedList<Integer> path;
    /**
     * Integer array to hold the lowest cost to each node
     */
    private final int[] distances;
    /**
     * Integer array to hold the parent to each node
     */
    private final int[] parent;
    /**
     * Integer to signify infinity cost of path
     */
    public static final int MAX_VALUE = 99999;

    /**
     * This constructor initialises the uniform cost search
     * @param vertices number of nodes in the uniform cost search
     */
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

    /**
     * This method finds the shortest path to each node
     * @param source this is the source node to use as starting node
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
     * This method returns the shortest path between the 2 nodes specified
     * @param source starting node
     * @param destination end node
     * @return the shortest path between the 2 nodes specified
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
     * This method adds a path between 2 nodes
     * @param source starting node
     * @param destination end node
     * @param weight path cost
     */
    public void addEdge(int source, int destination, int weight) { //add edges for uniform cost search
        Node node = new Node(source, destination, weight);
        adjacencyList[source].addLast(node); 
    }

    /**
     * Static class Node is the template for creating the nodes for the uniform cost search.
     *
     * @author Group 2A
     * @version 1.0
     * @since 21/04/2022
     */
    static class Node implements Comparator<Node> { //edge data class
        /**
         * Starting node
         */
        int source;
        /**
         * End node
         */
        int destination;
        /**
         * Path cost
         */
        int weight;

        /**
         * This constructor is used to create a new node
         */
        public Node() {}

        /**
         * This constructor is used to create a new path between 2 nodes
         * @param source starting node
         * @param destination end node
         * @param weight path cost
         */
        public Node(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }

        /**
         * This method compares 2 nodes and returns the order of the nodes
         * @param o1 the first object to be compared.
         * @param o2 the second object to be compared.
         * @return an integer to represent the order of the nodes
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
         * This method compares the given object to ascertain if it is the same node
         * @param obj the reference object with which to compare.
         * @return boolean to ascertain if it is the same node
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

