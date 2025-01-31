package fr.akshell.aoc.utils;

import fr.akshell.aoc.pojo.Graph;
import fr.akshell.aoc.graph.GenericGraph;
import fr.akshell.aoc.graph.IContent;
import fr.akshell.aoc.graph.INode;
import fr.akshell.aoc.pojo.Maze;
import fr.akshell.aoc.pojo.Vector2D;
import lombok.experimental.UtilityClass;

import java.util.*;

@UtilityClass
public class GraphUtils {

    private static final int[][] DIRECTIONS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public GenericGraph<Vector2D> convertMazeToGraph(Maze maze) {
        Map<String, Vector2D> nodes = new HashMap<>();

        // Add nodes for walkable cells
        for (int j = 0; j < maze.height(); j++) {
            for (int i = 0; i < maze.width(); i++) {
                if (maze.get(i, j) != '#') { // Assuming '#' represents walls
                    var v = new Vector2D(i, j);
                    nodes.put(v.id(), v);
                }
            }
        }
        GenericGraph<Vector2D> genericGraph = GenericGraph.of(nodes.values());

        // Add edges between adjacent walkable cells
        for (int j = 0; j < maze.height(); j++) {
            for (int i = 0; i < maze.width(); i++) {
                if (maze.get(i, j) != '#') {
                    var v1 = nodes.get(Vector2D.id(i, j));
                    int finalI = i;
                    int finalJ = j;
                    Arrays.stream(DIRECTIONS).forEach(dir -> {
                        int x = finalI + dir[0];
                        int y = finalJ + dir[1];
                        if (x >= 0 && x < maze.width() && y >= 0 && y < maze.height() && maze.get(x, y) != '#') {
                            genericGraph.addEdge(v1, nodes.get(Vector2D.id(x, y)));
                        }
                    });
                }
            }
        }

        return genericGraph;
    }

    public static int heldKarp(Graph graph) {
        Set<String> vertices = graph.getVertices();
        int n = vertices.size();
        List<String> vertexList = new ArrayList<>(vertices);
        Map<String, Integer> vertexIndex = new HashMap<>();
        for (int i = 0; i < n; i++) {
            vertexIndex.put(vertexList.get(i), i);
        }

        int[][] dist = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE / 2);
            dist[i][i] = 0;
        }

        for (String u : vertices) {
            int uIdx = vertexIndex.get(u);
            for (Graph.Edge edge : graph.getNeighbors(u)) {
                int vIdx = vertexIndex.get(edge.vertex());
                dist[uIdx][vIdx] = edge.weight();
            }
        }

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
        List<String> vertexList = new ArrayList<>(vertices);
        Map<String, Integer> vertexIndex = new HashMap<>();
        for (int i = 0; i < n; i++) {
            vertexIndex.put(vertexList.get(i), i);
        }

        int[][] dist = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Integer.MIN_VALUE / 2);
            dist[i][i] = 0;
        }

        for (String u : vertices) {
            int uIdx = vertexIndex.get(u);
            for (Graph.Edge edge : graph.getNeighbors(u)) {
                int vIdx = vertexIndex.get(edge.vertex());
                dist[uIdx][vIdx] = edge.weight();
            }
        }

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

    public <T extends IContent> Set<String> dfs(GenericGraph<T> genericGraph, String startNodeId) {
        INode<T> startNode = genericGraph.getNode(startNodeId);
        assert (startNode != null): "Node not found in graph";
        Set<String> visited = new HashSet<>();
        Queue<INode<T>> queue = new LinkedList<>();
        queue.add(startNode);
        while (!queue.isEmpty()) {
            INode<T> node = queue.poll();
            if (!visited.contains(node.id())) {
                visited.add(node.id());
                queue.addAll(node.neighbors());
            }
        }
        return visited;
    }
}
