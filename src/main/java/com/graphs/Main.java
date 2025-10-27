package com.graphs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.graphs.models.Graph;

public class Main {
    public static void main(String[] args) {

        // Test sparse graphs
        System.out.println("=== SPARSE GRAPHS ===");
        testGraphType("sparse", new int[] { 100, 1000, 10000, 100000 });

        System.out.println("\n");

        // Test dense graphs
        System.out.println("=== DENSE GRAPHS ===");
        testGraphType("dense", new int[] { 100, 1000, 10000, 100000 });
    }

    private static void testGraphType(String graphType, int[] sizes) {
        for (int size : sizes) {
            String filePath = "src/main/java/com/graphs/database/" + graphType + "-" + size + ".txt";
            System.out.println("\nTesting " + graphType + "-" + size + ".txt:");

            double totalTime = 0;
            for (int i = 1; i <= 3; i++) {
                long start = System.nanoTime();
                Graph graph = readGraph(filePath);
                long end = System.nanoTime();
                double timeTaken = (end - start) / 1000000.0;
                totalTime += timeTaken;
                System.out.println("  Iteration " + i + " - Time: " + String.format("%.4f", timeTaken) + " ms");
            }
            double avgTime = totalTime / 3;
            System.out.println("  Average: " + String.format("%.4f", avgTime) + " ms");
        }
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
