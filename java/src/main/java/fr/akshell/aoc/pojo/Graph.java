package fr.akshell.aoc.pojo;

import lombok.NonNull;

import java.util.*;

public record Graph<T extends IContent>(Map<String, INode<T>> nodes) {

    public static <T extends IContent> Graph<T> of() {
        return new Graph<>(new HashMap<>());
    }

    public static <T extends IContent> Graph<T> of(Collection<T> contents) {
        Graph<T> graph = of();
        contents.forEach(graph::addNode);
        return graph;
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

    public void addEdge(@NonNull T c1, @NonNull T c2, long weight, boolean bidirectional) {
        var node1 = nodes.get(c1.id());
        var node2 = nodes.get(c2.id());
        if (node1 == null || node2 == null) {
            throw new IllegalArgumentException("Node not found in graph");
        }
        node1.neighbors().add(node2);
        if (bidirectional) {
            node2.neighbors().add(node1);
        }
    }

    public void addEdge(@NonNull T c1, @NonNull T c2, long weight) {
        addEdge(c1, c2, weight, false);
    }

    public void addEdge(@NonNull T c1, @NonNull T c2, boolean bidirectional) {
        addEdge(c1, c2, 1, bidirectional);
    }

    public void addEdge(@NonNull T c1, @NonNull T c2) {
        addEdge(c1, c2, 1, false);
    }
}
