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

    public void minPath(int start, int end) {
        // Dijkstra's algorithm

        int test = getNode(start);

        System.out.println(test);

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
