package fr.akshell.aoc.graph;

import java.util.ArrayList;
import java.util.List;

public record Node<T extends IContent>(String id, T content, List<Edge<T>> edges) implements INode<T> {

    public static <T extends IContent> Node<T> of(T content) {
        return new Node<>(content.id(), content, new ArrayList<>());
    }

    public List<INode<T>> neighbors() {
        return edges.stream().map(Edge::node).toList();
    }
}
