package fr.akshell.aoc.pojo;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public record Maze(char[][] grid, int width, int height) implements Serializable {
    public static final char WALL = '#';
    public static final char EMPTY = '.';

    public static Maze of(int width, int height) {
        return new Maze(new char[height][width], width, height);
    }

    public char get(int x, int y) {
        return grid[y][x];
    }

    public char get(Vector2D pos) {
        return get(pos.x(), pos.y());
    }

    public char get(Vector4D pos) {
        return get(pos.x(), pos.y());
    }

    public void set(Vector4D pos, char value) {
        set(pos.x(), pos.y(), value);
    }

    public void set(Vector2D pos, char value) {
        set(pos.x(), pos.y(), value);
    }

    public void set(int x, int y, char value) {
        grid[y][x] = value;
    }

    public boolean isValid(int x, int y) {
        return (x >= 0) && (x < width) && (y >= 0) && (y < height);
    }

    public boolean isValid(Vector2D pos) {
        return isValid(pos.x(), pos.y());
    }

    public boolean isValid(Vector4D pos) {
        return isValid(pos.x(), pos.y());
    }

    public Vector2D find(char search) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (grid[i][j] == search) {
                    return new Vector2D(j, i);
                }
            }
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Maze maze = (Maze) o;
        return width == maze.width() && height == maze.height && Objects.deepEquals(grid, maze.grid());
    }

    @Override
    public int hashCode() {
        return Objects.hash(Arrays.deepHashCode(grid), width, height);
    }

    public String toString() {
        return Arrays.stream(grid).map(String::new).collect(Collectors.joining("\n"));
    }
}