package com.graphs.models;

import java.util.ArrayList;

class Node {
    NodeList edges;
    int value;

    public Node(int value) {
        this.value = value;
        this.edges = new NodeList();
    }

    public void addEdge(int value, int weight) {
        this.edges.add(value, weight);
    }

}

public class Graph {
    public ArrayList<Node> nodeArray; // ArrayList of nodes
    public int nodes;
    public int edges;

    public Graph() {
        this.nodeArray = new ArrayList<Node>();
    }

    public void addNode(int value) {

        if (nodeExistis(value)) {
            return;
        }

        nodeArray.add(new Node(value));
    }

    public void addEdge(int value, int target, int weight) {
        if (nodeExistis(value)) {

            if (!nodeExistis(target)) {
                addNode(target);
            }

            for (Node node : nodeArray) {
                if (node.value == value) {
                    node.addEdge(target, weight);
                }
            }
        } else {
            System.out.println("node " + value + " does not exist");
        }
    }

    private boolean nodeExistis(int value) {

        for (Node node : nodeArray) {
            if (node.value == value) {
                return true;
            }
        }
        return false;
    }

    private int getNode(int value) {

        int index = -1;

        for (Node node : nodeArray) {
            index++;
            if (node.value == value) {
                return index;
            }
        }
        System.out.println("node " + value + " does not exist");
        return -1;
    }

    public ArrayList<Integer> minPath(int start, int end) {
        // Dijkstra's algorithm

        // Check if start and end nodes exist
        if (getNode(start) == -1 || getNode(end) == -1) {
            System.out.println("Start or end node does not exist");
            return new ArrayList<>();
        }

        int n = nodeArray.size();
        int[] distances = new int[n]; // Distance from start to each node
        int[] previous = new int[n]; // Previous node in optimal path
        boolean[] visited = new boolean[n];

        // Initialize distances to infinity (max value) and previous to -1
        for (int i = 0; i < n; i++) {
            distances[i] = Integer.MAX_VALUE;
            previous[i] = -1;
            visited[i] = false;
        }

        // Distance from start to itself is 0
        int startIndex = getNode(start);
        distances[startIndex] = 0;

        // Main Dijkstra's algorithm loop
        for (int count = 0; count < n - 1; count++) {
            // Find the unvisited node with minimum distance
            int minDistance = Integer.MAX_VALUE;
            int currentIndex = -1;

            for (int i = 0; i < n; i++) {
                if (!visited[i] && distances[i] < minDistance) {
                    minDistance = distances[i];
                    currentIndex = i;
                }
            }

            // If no node was found, break
            if (currentIndex == -1)
                break;

            // Mark current node as visited
            visited[currentIndex] = true;

            // Update distances to neighbors
            Node currentNode = nodeArray.get(currentIndex);
            NodeEdge edge = currentNode.edges.head;

            while (edge != null) {
                int neighborIndex = getNode(edge.value);

                if (neighborIndex != -1 && !visited[neighborIndex]) {
                    int newDistance = distances[currentIndex] + edge.weight;

                    if (newDistance < distances[neighborIndex]) {
                        distances[neighborIndex] = newDistance;
                        previous[neighborIndex] = currentIndex;
                    }
                }

                edge = edge.next;
            }
        }

        // Build the path from start to end
        ArrayList<Integer> path = new ArrayList<>();
        int endIndex = getNode(end);

        // Check if path exists
        if (distances[endIndex] == Integer.MAX_VALUE) {
            System.out.println("No path exists from " + start + " to " + end);
            return path;
        }

        // Reconstruct path by following previous nodes
        int current = endIndex;
        while (current != -1) {
            path.add(0, nodeArray.get(current).value);
            current = previous[current];
        }

        // Print the result
        // System.out.print("Path: ");
        // for (int i = 0; i < path.size(); i++) {
        //     System.out.print(path.get(i));
        //     if (i < path.size() - 1) {
        //         System.out.print(" -> ");
        //     }
        // }

        // // Print the total weight of the path
        // System.out.println(" (Peso: " + distances[endIndex] + ")");

        return path;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nodeArray.size(); i++) {
            sb.append(nodeArray.get(i).value).append(": ");
            if (nodeArray.get(i).edges.head != null) {
                sb.append(nodeArray.get(i).edges.toString());
            } else {
                sb.append("No edges");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
