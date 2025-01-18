package fr.akshell.aoc.pojo;

public record Vector4D(int x, int y, int d_x, int d_y) implements IContent {

    public static Vector4D of(Vector2D pos, int d_x, int d_y) {
        return new Vector4D(pos.x(), pos.y(), d_x, d_y);
    }

    public Vector2D position() {
        return new Vector2D(x, y);
    }

    public Vector4D turnLeft() {
        return new Vector4D(x, y, d_y, -d_x);
    }

    public Vector4D turnRight() {
        return new Vector4D(x, y, -d_y, d_x);
    }

    public Vector4D turnAround() {
        return new Vector4D(x, y, -d_x, -d_y);
    }

    public Vector4D moveForward() {
        return new Vector4D(x + d_x, y + d_y, d_x, d_y);
    }

    public static String id(int x, int y, int d_x, int d_y) {
        return String.format("(%d, %d, %d, %d)", x, y, d_x, d_y);
    }

    public String id() {
        return id(x, y, d_x, d_y);
    }
}
