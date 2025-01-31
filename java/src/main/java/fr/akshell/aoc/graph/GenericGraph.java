package fr.akshell.aoc.graph;

import lombok.NonNull;

import java.util.*;

public record GenericGraph<T extends IContent>(Map<String, INode<T>> nodes) {

    public static <T extends IContent> GenericGraph<T> of() {
        return new GenericGraph<>(new HashMap<>());
    }

    public static <T extends IContent> GenericGraph<T> of(Collection<T> contents) {
        GenericGraph<T> genericGraph = of();
        contents.forEach(genericGraph::addNode);
        return genericGraph;
    }

    public void addNode(INode<T> node) {
        nodes.put(node.id(), node);
    }

    public void addNode(T content) {
        addNode(Node.of(content));
    }

    public INode<T> getNode(String id) {
        return nodes.get(id);
    }

    public int nodeCount() {
        return nodes.size();
    }

    public int edgeCount() {
        return nodes.values().stream().mapToInt(n -> n.neighbors().size()).sum();
    }

    public void addEdge(@NonNull T c1, @NonNull T c2, long weight) {
        var node1 = nodes.get(c1.id());
        var node2 = nodes.get(c2.id());
        if (node1 == null || node2 == null) {
            throw new IllegalArgumentException("Node not found in graph");
        }
        node1.edges().add(new Edge<>(node2, weight));
    }
    public void addEdge(@NonNull T c1, @NonNull T c2) {
        addEdge(c1, c2, 1);
    }
}
