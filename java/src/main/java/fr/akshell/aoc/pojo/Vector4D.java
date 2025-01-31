package fr.akshell.aoc.pojo;

import fr.akshell.aoc.graph.IContent;

public record Vector4D(int x, int y, int dX, int dY) implements IContent {

    public static Vector4D of(Vector2D pos, int dX, int dY) {
        return new Vector4D(pos.x(), pos.y(), dX, dY);
    }

    public Vector2D position() {
        return new Vector2D(x, y);
    }

    public Vector4D turnLeft() {
        return new Vector4D(x, y, dY, -dX);
    }

    public Vector4D turnRight() {
        return new Vector4D(x, y, -dY, dX);
    }

    public Vector4D turnAround() {
        return new Vector4D(x, y, -dX, -dY);
    }

    public Vector4D moveForward() {
        return new Vector4D(x + dX, y + dY, dX, dY);
    }

    public static String id(int x, int y, int dX, int dY) {
        return String.format("(%d, %d, %d, %d)", x, y, dX, dY);
    }

    public String id() {
        return id(x, y, dX, dY);
    }
}
