package Coursework;

import java.util.*;

public class UniformCostSearch {
    private final PriorityQueue<Node> priorityQueue;
    private final Set<Integer> settled;
    private final int[] distances;
    private final int numberOfNodes;
    private final int[][] adjacencyMatrix;
    private final LinkedList<Integer> path;
    private final int[] parent;
    private int source, destination;
    public static final int MAX_VALUE = 99999;

    public UniformCostSearch(int numberOfNodes) {
        this.numberOfNodes = numberOfNodes;
        this.settled = new HashSet<>();
        this.priorityQueue = new PriorityQueue<>(numberOfNodes, new Node());
        this.distances = new int[numberOfNodes + 1];
        this.path = new LinkedList<>();
        this.adjacencyMatrix = new int[numberOfNodes + 1][numberOfNodes + 1];
        this.parent = new int[numberOfNodes + 1];
    }

    public int uniformCostSearch(int[][] adjacencyMatrix, int source, int destination) {
        int evaluationNode;
        this.source = source;
        this.destination = destination;

        for (int i = 0; i < numberOfNodes; i++) {
            distances[i] = MAX_VALUE;
        }

        for (int sourcevertex = 0; sourcevertex < numberOfNodes; sourcevertex++) {
            System.arraycopy(adjacencyMatrix[sourcevertex], 0, this.adjacencyMatrix[sourcevertex], 0, numberOfNodes);
        }

        priorityQueue.add(new Node(source, 0));
        distances[source] = 0;

        while (!priorityQueue.isEmpty()) {
            evaluationNode = getNodeWithMinDistanceFromPriorityQueue();
            System.out.println("The eval Node is " + evaluationNode);
            if (evaluationNode == destination) {
                return distances[destination];
            }
            settled.add(evaluationNode);
            addFrontiersToQueue(evaluationNode);
        }
        return distances[destination];
    }

    private void addFrontiersToQueue(int evaluationNode) {
        for (int destination = 0; destination < numberOfNodes; destination++) {
            if (!settled.contains(destination)) {
                if (adjacencyMatrix[evaluationNode][destination] != MAX_VALUE) {
                    if (distances[destination] > adjacencyMatrix[evaluationNode][destination] + distances[evaluationNode]) {
                        distances[destination] = adjacencyMatrix[evaluationNode][destination] + distances[evaluationNode];
                        parent[destination] = evaluationNode;
                    }
                    Node node = new Node(destination, distances[destination]);
                    priorityQueue.remove(node);
                    priorityQueue.add(node);
                }
            }
        }
    }

    private int getNodeWithMinDistanceFromPriorityQueue() {
        Node node = priorityQueue.remove();
        return node.node;
    }

    public int[] printPath() {
        int[] nodes = new int[7];
        path.add(destination);
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

        System.out.println("The Path between " + source + " and " + destination + " is ");
        Iterator<Integer> iterator = path.descendingIterator();
        int i = 0;
        while (iterator.hasNext()) {
            nodes[i] = iterator.next();
            System.out.print(nodes[i] + "\t");
            i++;
        }
        return nodes;
    }

    static class Node implements Comparator<Node> {
        public int node;
        public int cost;

        public Node() {

        }

        public Node(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }

        @Override
        public int compare(Node node1, Node node2) {
            if (node1.cost < node2.cost)
                return -1;
            if (node1.cost > node2.cost)
                return 1;
            if (node1.node < node2.node)
                return -1;
            return 0;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Node node) {
                return this.node == node.node;
            }
            return false;
        }
    }
}