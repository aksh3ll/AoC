package fr.akshell.aoc.pojo;

import fr.akshell.aoc.graph.IContent;

public record Vector2D(int x, int y) implements IContent {

    public String id() {
        return id(x, y);
    }

    public static String id(int x, int y) {
        return String.format("(%d, %d)", x, y);
    }
}
