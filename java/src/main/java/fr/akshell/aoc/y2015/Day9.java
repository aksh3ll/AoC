package fr.akshell.aoc.y2015;

import fr.akshell.aoc.base.BaseDay;
import fr.akshell.aoc.pojo.Graph;
import java.util.Arrays;
import java.util.List;

import static fr.akshell.aoc.utils.MiscUtils.permute;

public class Day9 extends BaseDay<Integer> {

    private record Route(String from, String to, int distance) {
        public static Route of(String line) {
            String[] parts = line.split(" ");
            return new Route(parts[0], parts[2], Integer.parseInt(parts[4]));
        }
    }

    private Graph convertInputToGraph(String input) {
        List<Route> routes = Arrays.stream(input.split("\n")).map(Route::of).toList();
        Graph graph = new Graph();
        routes.forEach(route -> {
            graph.addEdge(route.from(), route.to(), route.distance());
            graph.addEdge(route.to(), route.from(), route.distance());
        });
        return graph;
    }

    private int shortestPath(Graph graph) {
        int minDistance = Integer.MAX_VALUE;
        for (List<String> permutation : permute(graph.getVertices().stream().toList())) {
            int distance = 0;
            for (int i = 0; i < permutation.size() - 1; i++) {
                distance += graph.getEdge(permutation.get(i), permutation.get(i + 1)).weight();
            }
            minDistance = Math.min(minDistance, distance);
        }
        return minDistance;
    }

    private int longestPath(Graph graph) {
        int maxDistance = Integer.MIN_VALUE;
        for (List<String> permutation : permute(graph.getVertices().stream().toList())) {
            int distance = 0;
            for (int i = 0; i < permutation.size() - 1; i++) {
                distance += graph.getEdge(permutation.get(i), permutation.get(i + 1)).weight();
            }
            maxDistance = Math.max(maxDistance, distance);
        }
        return maxDistance;
    }

    public Integer part1(String input) {
        return shortestPath(convertInputToGraph(input));
    }

    public Integer part2(String input) {
        return longestPath(convertInputToGraph(input));
    }
}
