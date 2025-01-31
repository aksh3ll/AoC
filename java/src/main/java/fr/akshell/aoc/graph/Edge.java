package fr.akshell.aoc.graph;

public record Edge<T extends IContent>(INode<T> node, long weight) implements IEdge<T> {}
