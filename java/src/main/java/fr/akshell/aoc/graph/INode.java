package fr.akshell.aoc.graph;

import java.util.List;

public interface INode<T extends IContent> {
    T content();
    String id();
    List<IEdge<T>> edges();
    List<INode<T>> neighbors();
}
