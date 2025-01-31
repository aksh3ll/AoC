package fr.akshell.aoc.graph;

public interface IEdge<T extends IContent> {
    INode<T> node();
    long weight();
}
