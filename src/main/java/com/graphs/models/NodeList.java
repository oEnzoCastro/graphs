package com.graphs.models;

class NodeEdge {
    int value;
    int weight;
    NodeEdge next;

    public NodeEdge(int value, int weight) {
        this.value = value;
        this.weight = weight;
        this.next = null;
    }
}

public class NodeList {
    NodeEdge head;

    public NodeList() {
        this.head = null;
    }

    public void add(int value, int weight) {
        NodeEdge newNode = new NodeEdge(value, weight);
        if (head == null) {
            head = newNode;
        } else {
            NodeEdge current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        NodeEdge current = head;
        while (current.next != null) {
            sb.append("v:" + current.value).append("; w:" + current.weight + " | ");
            current = current.next;
        }
        sb.append("v:" + current.value).append("; w:" + current.weight);

        return sb.toString();
    }

}
