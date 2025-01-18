package fr.akshell.aoc.pojo;

import java.util.List;

public interface INode<T extends IContent> {
    T content();
    String id();
    List<INode<T>> neighbors();
}
