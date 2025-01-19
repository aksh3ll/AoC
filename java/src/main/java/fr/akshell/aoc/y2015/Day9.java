package fr.akshell.aoc.y2015;

import fr.akshell.aoc.base.BaseDay;
import fr.akshell.aoc.graph.Graph;
import fr.akshell.aoc.pojo.IContent;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static fr.akshell.aoc.utils.MiscUtils.permute;

public class Day9 extends BaseDay<Integer> {

    private record City(String name) implements IContent {
        public String id() {
            return name;
        }
    }

    private record Route(String from, String to, int distance) {
        public static Route of(String line) {
            String[] parts = line.split(" ");
            return new Route(parts[0], parts[2], Integer.parseInt(parts[4]));
        }
    }

    private int shortestPath(Graph graph, List<String> cities) {
        int minDistance = Integer.MAX_VALUE;
        for (List<String> permutation : permute(cities)) {
            int distance = 0;
            for (int i = 0; i < permutation.size() - 1; i++) {
                distance += graph.getEdge(permutation.get(i), permutation.get(i + 1)).weight();
            }
            minDistance = Math.min(minDistance, distance);
        }
        return minDistance;
    }

    private int longestPath(Graph graph, List<String> cities) {
        int maxDistance = Integer.MIN_VALUE;
        for (List<String> permutation : permute(cities)) {
            int distance = 0;
            for (int i = 0; i < permutation.size() - 1; i++) {
                distance += graph.getEdge(permutation.get(i), permutation.get(i + 1)).weight();
            }
            maxDistance = Math.max(maxDistance, distance);
        }
        return maxDistance;
    }

    public Integer part1(String input) {
        List<Route> routes = Arrays.stream(input.split("\n")).map(Route::of).toList();
        Set<City> cities = routes.stream().map(Route::from).map(City::new).collect(Collectors.toSet());
        routes.stream().map(Route::to).map(City::new).forEach(cities::add);
        Graph graph = new Graph();
        routes.forEach(route -> graph.addEdge(route.from(), route.to(), route.distance()));
        return shortestPath(graph, cities.stream().map(City::name).toList());
    }

    public Integer part2(String input) {
        List<Route> routes = Arrays.stream(input.split("\n")).map(Route::of).toList();
        Set<City> cities = routes.stream().map(Route::from).map(City::new).collect(Collectors.toSet());
        routes.stream().map(Route::to).map(City::new).forEach(cities::add);
        Graph graph = new Graph();
        routes.forEach(route -> graph.addEdge(route.from(), route.to(), route.distance()));
        return longestPath(graph, cities.stream().map(City::name).toList());
    }
}
