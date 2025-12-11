package fr.akshell.aoc.y2025;

import fr.akshell.aoc.base.BaseDay;

import java.util.List;

public class Day11 extends BaseDay<Long> {

    public record Node(String name, java.util.List<String> children) {
        public static Node of(String name) {
            return new Node(name, new java.util.ArrayList<>());
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
        Node node = getNodeByName(parts[0].trim());
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

    public Node parseInput(String input) {
        nodes = new java.util.ArrayList<>();
        input.lines().forEach(this::parseLine);
        return nodes.getFirst();
    }

    private int findPathsToOut(String nodeName, java.util.Set<String> visited) {
        if (nodeName.equals("out")) {
            return 1;
        }
        if (visited.contains(nodeName)) {
            return 0;
        }
        visited.add(nodeName);
        Node node = getNodeByName(nodeName);
        int totalPaths = 0;
        for (String childName : node.children) {
            totalPaths += findPathsToOut(childName, new java.util.HashSet<>(visited));
        }
        return totalPaths;
    }

    @Override
    public Long part1(String input) {
        parseInput(input);
        return (long) findPathsToOut("you", new java.util.HashSet<>());
    }

    @Override
    public Long part2(String input) {
        parseInput(input);
        return (long) findPathsToOut("svr", new java.util.HashSet<>());
    }
}
