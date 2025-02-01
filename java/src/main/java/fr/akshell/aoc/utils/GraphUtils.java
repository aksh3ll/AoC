package fr.akshell.aoc.utils;

import fr.akshell.aoc.pojo.Graph;
import fr.akshell.aoc.pojo.Maze;
import fr.akshell.aoc.pojo.Vector2D;
import lombok.experimental.UtilityClass;

import java.util.*;

@UtilityClass
public class GraphUtils {

    private static final int[][] DIRECTIONS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public Graph convertMazeToGraph(Maze maze) {
        Graph graph = new Graph();
        // Add edges between adjacent walkable cells
        for (int j = 0; j < maze.height(); j++) {
            for (int i = 0; i < maze.width(); i++) {
                if (maze.get(i, j) != '#') {
                    var v1 = Vector2D.id(i, j);
                    int finalI = i;
                    int finalJ = j;
                    Arrays.stream(DIRECTIONS).forEach(dir -> {
                        int x = finalI + dir[0];
                        int y = finalJ + dir[1];
                        if (x >= 0 && x < maze.width() && y >= 0 && y < maze.height() && maze.get(x, y) != '#') {
                            graph.addEdge(v1, Vector2D.id(x, y), 1);
                        }
                    });
                }
            }
        }
        return graph;
    }

    private int[][] getAdjacencyMatrix(Graph graph, int defaultValue) {
        Set<String> vertices = graph.getVertices();
        int n = vertices.size();

        List<String> vertexList = new ArrayList<>(vertices);
        Map<String, Integer> vertexIndex = new HashMap<>();
        for (int i = 0; i < vertices.size(); i++) {
            vertexIndex.put(vertexList.get(i), i);
        }

        int[][] adjacencyMatrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(adjacencyMatrix[i], defaultValue);
            adjacencyMatrix[i][i] = 0;
        }

        for (String u : vertices) {
            int uIdx = vertexIndex.get(u);
            for (Graph.Edge edge : graph.getNeighbors(u)) {
                int vIdx = vertexIndex.get(edge.vertex());
                adjacencyMatrix[uIdx][vIdx] = edge.weight();
            }
        }

        return adjacencyMatrix;
    }

    public static int heldKarp(Graph graph) {
        int n = graph.getVertices().size();
        int[][] dist = getAdjacencyMatrix(graph, Integer.MAX_VALUE / 2);

        int[][] dp = new int[1 << n][n];
        for (int[] row : dp) {
            Arrays.fill(row, Integer.MAX_VALUE / 2);
        }
        dp[1][0] = 0;

        for (int mask = 1; mask < (1 << n); mask += 2) {
            for (int u = 0; u < n; u++) {
                if ((mask & (1 << u)) != 0) {
                    for (int v = 0; v < n; v++) {
                        if ((mask & (1 << v)) == 0) {
                            dp[mask | (1 << v)][v] = Math.min(dp[mask | (1 << v)][v], dp[mask][u] + dist[u][v]);
                        }
                    }
                }
            }
        }

        int result = Integer.MAX_VALUE;
        for (int u = 1; u < n; u++) {
            result = Math.min(result, dp[(1 << n) - 1][u] + dist[u][0]);
        }

        return result;
    }

    public static int heldKarpMax(Graph graph) {
        Set<String> vertices = graph.getVertices();
        int n = vertices.size();
        int[][] dist = getAdjacencyMatrix(graph, Integer.MIN_VALUE / 2);

        int[][] dp = new int[1 << n][n];
        for (int[] row : dp) {
            Arrays.fill(row, Integer.MIN_VALUE / 2);
        }
        dp[1][0] = 0;

        for (int mask = 1; mask < (1 << n); mask += 2) {
            for (int u = 0; u < n; u++) {
                if ((mask & (1 << u)) != 0) {
                    for (int v = 0; v < n; v++) {
                        if ((mask & (1 << v)) == 0) {
                            dp[mask | (1 << v)][v] = Math.max(dp[mask | (1 << v)][v], dp[mask][u] + dist[u][v]);
                        }
                    }
                }
            }
        }

        int result = Integer.MIN_VALUE;
        for (int u = 1; u < n; u++) {
            result = Math.max(result, dp[(1 << n) - 1][u] + dist[u][0]);
        }

        return result;
    }

    public Set<String> dfs(Graph graph, String startVertex) {
        if (!graph.getVertices().contains(startVertex)) {
            throw new AssertionError("Node not found in graph");
        }
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.add(startVertex);
        while (!queue.isEmpty()) {
            String vertex = queue.poll();
            if (!visited.contains(vertex)) {
                visited.add(vertex);
                queue.addAll(graph.getNeighbors(vertex).stream().map(Graph.Edge::vertex).toList());
            }
        }
        return visited;
    }
}
