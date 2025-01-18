package fr.akshell.aoc.y2015;

import static fr.akshell.aoc.utils.GraphUtils.heldKarp;

import fr.akshell.aoc.base.BaseDay;
import fr.akshell.aoc.graph.Graph;
import fr.akshell.aoc.pojo.IContent;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Day9 extends BaseDay {

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

    public long part1(String input) {
        List<Route> routes = Arrays.stream(input.split("\n")).map(Route::of).toList();
        Set<City> cities = routes.stream().map(Route::from).map(City::new).collect(Collectors.toSet());
        routes.stream().map(Route::to).map(City::new).forEach(cities::add);
        Graph graph = new Graph();
        routes.forEach(route -> graph.addEdge(route.from(), route.to(), route.distance()));
        return heldKarp(graph);
    }

    public long part2(String input) {
        List<Route> routes = Arrays.stream(input.split("\n")).map(Route::of).toList();
        return 0;
    }
}
