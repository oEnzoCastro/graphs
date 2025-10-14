package com.graphs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.graphs.models.Graph;

public class Main {
    public static void main(String[] args) {

        Graph graph = readGraph("src/main/java/com/graphs/database/graph-100.txt");

        graph.minPath(100, 2);

        System.out.println(graph.toString());
    }

    public static Graph readGraph(String filePath) {
        Graph graph = new Graph();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            String line;
            line = reader.readLine(); // Skip first line
            String[] lineSplit = line.split(" ");

            graph.nodes = Integer.parseInt(lineSplit[0]);
            graph.edges = Integer.parseInt(lineSplit[1]);

            while ((line = reader.readLine()) != null) {

                lineSplit = line.split(" ");

                int nodeValue = Integer.parseInt(lineSplit[0]);

                // Add node
                graph.addNode(nodeValue);

                int targetValue = Integer.parseInt(lineSplit[1]);
                int weight = Integer.parseInt(lineSplit[2]);

                // Add edge
                graph.addEdge(nodeValue, targetValue, weight);

            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

        return graph;

    }




}
