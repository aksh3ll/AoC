package fr.akshell.aoc.pojo;

import java.util.ArrayList;
import java.util.List;

public record Node<T extends IContent>(String id, T content, List<INode<T>> neighbors) implements INode<T> {

    public static <T extends IContent> Node<T> of(T content) {
        return new Node<>(content.id(), content, new ArrayList<>());
    }
}
