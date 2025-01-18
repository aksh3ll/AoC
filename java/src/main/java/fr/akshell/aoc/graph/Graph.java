package fr.akshell.aoc.graph;

import java.util.*;

public class Graph {
    public record Edge(String vertex, int weight) {}
    private final Map<String, List<Edge>> adjacencyList;

    public Graph() {
        this.adjacencyList = new HashMap<>();
    }

    public void addVertex(String vertex) {
        adjacencyList.putIfAbsent(vertex, new ArrayList<>());
    }

    public void addEdge(String vertex1, String vertex2, int weight) {
        adjacencyList.putIfAbsent(vertex1, new ArrayList<>());
        adjacencyList.putIfAbsent(vertex2, new ArrayList<>());
        adjacencyList.get(vertex1).add(new Edge(vertex2, weight));
        adjacencyList.get(vertex2).add(new Edge(vertex1, weight)); // For undirected graph
    }

    public List<Edge> getNeighbors(String vertex) {
        return adjacencyList.get(vertex);
    }

    public Set<String> getVertices() {
        return adjacencyList.keySet();
    }

}
