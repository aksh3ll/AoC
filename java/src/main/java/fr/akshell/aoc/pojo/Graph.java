package fr.akshell.aoc.pojo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Graph {
    public record Edge(String vertex, int weight) {}
    private final Map<String, List<Edge>> adjacencyList;

    public Graph() {
        this.adjacencyList = new HashMap<>();
    }

    public void addVertex(String vertex) {
        adjacencyList.putIfAbsent(vertex, new ArrayList<>());
    }

    /**
     * Add a directional edge between two vertices with a given weight
     * @param vertex1 from
     * @param vertex2 to
     * @param weight distance/weight/cost/etc.
     */
    public void addEdge(String vertex1, String vertex2, int weight) {
        adjacencyList.putIfAbsent(vertex1, new ArrayList<>());
        adjacencyList.putIfAbsent(vertex2, new ArrayList<>());
        adjacencyList.get(vertex1).add(new Edge(vertex2, weight));
    }

    public Edge getEdge(String vertex1, String vertex2) {
        return adjacencyList
                .get(vertex1)
                .stream()
                .filter(e -> e.vertex().equals(vertex2))
                .findFirst()
                .orElseThrow();
    }

    public List<Edge> getNeighbors(String vertex) {
        return adjacencyList.get(vertex);
    }

    public Set<String> getVertices() {
        return adjacencyList.keySet();
    }

}
