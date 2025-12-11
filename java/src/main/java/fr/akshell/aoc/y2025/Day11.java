package fr.akshell.aoc.y2025;

import fr.akshell.aoc.base.BaseDay;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day11 extends BaseDay<Long> {

    public record Node(String name, List<String> children, List<String> parents) {
        public static Node of(String name) {
            return new Node(name, new ArrayList<>(), new ArrayList<>());
        }
    }

    private List<Node> nodes;

    private Node getNodeByName(String name) {
        return nodes.stream()
                .filter(n -> n.name.equals(name))
                .findFirst()
                .orElseGet(() -> {
                    Node newNode = Node.of(name);
                    nodes.add(newNode);
                    return newNode;
                });
    }

    private void parseLine(String line) {
        String[] parts = line.split(":");
        String nodeName = parts[0].trim();
        Node node = getNodeByName(nodeName);
        if (parts.length > 1) {
            String[] childrenNames = parts[1].trim().split(" ");
            for (String childName : childrenNames) {
                childName = childName.trim();
                if (!childName.isEmpty()) {
                    node.children.add(childName);
                    getNodeByName(childName);
                }
            }
        }
    }

    public void parseInput(String input) {
        nodes = new ArrayList<>();
        input.lines().forEach(this::parseLine);
        for (Node node : nodes) {
            for (String childName : node.children) {
                Node childNode = getNodeByName(childName);
                childNode.parents.add(node.name);
            }
        }
    }

    private int findPathsTo(String nodeName, Set<String> visited, String end) {
        if (nodeName.equals(end)) {
            return 1;
        }
        if (visited.contains(nodeName)) {
            return 0;
        }
        visited.add(nodeName);
        Node node = getNodeByName(nodeName);
        int totalPaths = 0;
        for (String childName : node.children) {
            totalPaths += findPathsTo(childName, new HashSet<>(visited), end);
        }
        return totalPaths;
    }

    private String searchParent(String nodeName, List<String> parentsToFind) {
        if (parentsToFind.contains(nodeName)) {
            return nodeName;
        }
        Node node = getNodeByName(nodeName);
        for (String parentName : node.parents) {
            String found = searchParent(parentName, parentsToFind);
            if (found != null) {
                return found;
            }
        }
        return null;
    }

    @Override
    public Long part1(String input) {
        parseInput(input);
        return (long) findPathsTo("you", new HashSet<>(), "out");
    }

    @Override
    public Long part2(String input) {
        parseInput(input);
        String dacParent = searchParent("dac", List.of("svr", "fft"));
        String fftParent = searchParent("fft", List.of("svr", "dac"));
        String step1;
        String step2;
        if ("svr".equals(dacParent) && "dac".equals(fftParent)) {
            step1 = "dac";
            step2 = "fft";
        } else if ("fft".equals(dacParent) && "svr".equals(fftParent)) {
            step1 = "fft";
            step2 = "dac";
        } else {
            return -1L;
        }
        return (long) findPathsTo("svr", new HashSet<>(), step1)
                * findPathsTo(step1, new HashSet<>(), step2)
                * findPathsTo(step2, new HashSet<>(), "out");
    }
}
