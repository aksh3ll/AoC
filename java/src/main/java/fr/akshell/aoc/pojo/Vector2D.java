package fr.akshell.aoc.pojo;

public record Vector2D(int x, int y) {

    public String id() {
        return id(x, y);
    }

    public static String id(int x, int y) {
        return String.format("(%d, %d)", x, y);
    }
}
