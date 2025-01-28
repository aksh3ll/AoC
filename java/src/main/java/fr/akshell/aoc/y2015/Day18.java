package fr.akshell.aoc.y2015;

import fr.akshell.aoc.base.BaseDay;
import fr.akshell.aoc.pojo.Maze;
import fr.akshell.aoc.pojo.Vector2D;
import fr.akshell.aoc.utils.MazeUtils;
import lombok.Setter;

import java.util.Arrays;

@Setter
public class Day18 extends BaseDay<Integer> {

    private static final char ALIVE = '#';
    private static final char DEAD = '.';
    private int steps = 100;


    private static final Vector2D[] DIRECTIONS = new Vector2D[] {
            new Vector2D(-1, -1), new Vector2D(-1, 0), new Vector2D(-1, 1),
            new Vector2D(0, -1), new Vector2D(0, 1),
            new Vector2D(1, -1), new Vector2D(1, 0), new Vector2D(1, 1)
    };

    private static Maze nextGeneration(Maze maze) {
        Maze newGrid = Maze.of(maze.width(), maze.height());

        for (int i = 0; i < maze.width(); i++) {
            for (int j = 0; j < maze.height(); j++) {
                int neighbors = countNeighbors(i, j, maze);
                if (maze.get(i, j) == ALIVE) {
                    newGrid.set(i, j, neighbors == 2 || neighbors == 3 ? ALIVE : DEAD);
                } else {
                    newGrid.set(i, j, neighbors == 3 ? ALIVE : DEAD);
                }
            }
        }
        return newGrid;
    }

    private static int countNeighbors(int x, int y, Maze grid) {
        return Arrays.stream(DIRECTIONS).mapToInt(direction -> {
            int newX = x + direction.x();
            int newY = y + direction.y();
            if (newX >= 0 && newX < grid.width() && newY >= 0 && newY < grid.height() && grid.get(newX, newY) == ALIVE) {
                return 1;
            }
            return 0;
        }).sum();
    }

    private static int countLights(Maze maze) {
        int count = 0;
        for (int i = 0; i < maze.width(); i++) {
            for (int j = 0; j < maze.height(); j++) {
                if (maze.get(i, j) == ALIVE) {
                    count++;
                }
            }
        }
        return count;
    }

    private void forceCornersAlight(Maze maze) {
        maze.set(0, 0, ALIVE);
        maze.set(0, maze.width() - 1, ALIVE);
        maze.set(maze.height() - 1, 0, ALIVE);
        maze.set(maze.height() - 1, maze.width() - 1, ALIVE);
    }

    @Override
    public Integer part1(String input) {
        Maze maze = MazeUtils.convertInputToMaze(input);
        for (int i = 0; i < steps; i++) {
            maze = nextGeneration(maze);
        }
        return countLights(maze);
    }

    @Override
    public Integer part2(String input) {
        Maze maze = MazeUtils.convertInputToMaze(input);
        forceCornersAlight(maze);
        for (int i = 0; i < steps; i++) {
            maze = nextGeneration(maze);
            forceCornersAlight(maze);
        }
        return countLights(maze);
    }
}
