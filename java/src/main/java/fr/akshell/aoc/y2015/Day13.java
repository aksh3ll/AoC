package fr.akshell.aoc.y2015;

import static fr.akshell.aoc.utils.MiscUtils.permute;

import fr.akshell.aoc.base.BaseDay;
import fr.akshell.aoc.pojo.Graph;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class Day13 extends BaseDay<Integer> {

    private final static Pattern RE_POTENTIAL = Pattern.compile(
            "^(\\w+) would (\\w+) (\\d+) happiness units by sitting next to (\\w+)\\.$");

    private Graph convertInput(String input) {
        Graph graph = new Graph();
        Arrays.stream(input.split("\n")).filter(s -> !s.isBlank()).forEach(s -> {
            var matcher = RE_POTENTIAL.matcher(s);
            if (matcher.matches()) {
                var value = Integer.parseInt(matcher.group(3));
                if ("lose".equals(matcher.group(2))) {
                    value = -value;
                }
                graph.addEdge(matcher.group(1), matcher.group(4), value);
            }
        });

        return graph;
    }

    private int longestPath(Graph graph) {
        int maxDistance = Integer.MIN_VALUE;
        for (List<String> permutation : permute(graph.getVertices().stream().toList())) {
            int distance = 0;
            int end = permutation.size() - 1;
            for (int i = 0; i < end; i++) {
                distance += graph.getEdge(permutation.get(i), permutation.get(i + 1)).weight()
                        + graph.getEdge(permutation.get(i + 1), permutation.get(i)).weight();
            }
            distance += graph.getEdge(permutation.getFirst(), permutation.get(end)).weight()
                    + graph.getEdge(permutation.get(end), permutation.getFirst()).weight();
            maxDistance = Math.max(maxDistance, distance);
        }
        return maxDistance;
    }

    public Integer part1(String input) {
        return longestPath(convertInput(input));
    }

    public Integer part2(String input) {
        Graph graph = convertInput(input);
        graph.addVertex("Me");
        graph.getVertices().forEach(v -> {
            graph.addEdge("Me", v, 0);
            graph.addEdge(v, "Me", 0);
        });
        return longestPath(graph);
    }
}
