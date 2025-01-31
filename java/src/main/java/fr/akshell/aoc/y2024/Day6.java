package fr.akshell.aoc.y2024;

import static fr.akshell.aoc.pojo.Maze.EMPTY;
import static fr.akshell.aoc.pojo.Maze.WALL;

import fr.akshell.aoc.base.BaseDay;
import fr.akshell.aoc.pojo.Maze;
import fr.akshell.aoc.pojo.Vector2D;
import fr.akshell.aoc.pojo.Vector4D;
import fr.akshell.aoc.utils.MazeUtils;

import java.util.*;

public class Day6 extends BaseDay<Integer> {
    public static final int MAX_MOVES = 10000;
    private static final char GUARD = '^';
    private static final char BOX = 'O';
    private static final char VISITED = 'X';

    public boolean searchLoop(Maze maze, Vector4D guard) {
        List<Vector4D> moves = new ArrayList<>();
        moves.add(guard);
        while (moves.size() < MAX_MOVES) {
            Vector4D nextMove = guard.moveForward();
            if (!maze.isValid(nextMove)) {
                return false;
            }
            guard = switch (maze.get(nextMove)) {
                case WALL, BOX -> guard.turnRight(); // turn 90° right
                case EMPTY, GUARD -> nextMove; // move forward
                default -> guard; // Should never happen, so do nothing
            };
            if (moves.contains(guard)) {
                return true;
            } else {
                moves.add(guard);
            }
        }
        return false;
    }

    public void run(Maze maze, List<Vector2D> visited, List<Vector2D> blockers) {
        Vector2D guardPos = maze.find(GUARD);
        assert guardPos != null;
        Vector4D guard = new Vector4D(guardPos.x(), guardPos.y(), 0, -1);
        logger.info("grid ({}x{}) guard: ({}, {})", maze.width(), maze.height(), guard.x(), guard.y());
        visited.add(new Vector2D(guard.x(), guard.y()));

        for (int moves = 0; moves < MAX_MOVES; moves++) {
            Vector4D newPos = guard.moveForward();
            if (!maze.isValid(newPos)) {
                break;
            }
            guard = switch (maze.get(newPos)) {
                case WALL, BOX -> guard.turnRight(); // turn 90° right
                case EMPTY, GUARD -> {
                    if (!visited.contains(newPos.position())) {
                        maze.set(newPos, BOX);
                        if (searchLoop(maze, guard)) {
                            blockers.add(newPos.position());
                        }
                        maze.set(newPos, EMPTY);
                        visited.add(newPos.position());
                    }
                    yield guard.moveForward(); // move forward
                }
                default -> guard;
            };
        }
    }

    public Integer part1(String input) {
        Maze maze = MazeUtils.convertInputToMaze(input);
        List<Vector2D> visited = new ArrayList<>();
        List<Vector2D> blockers = new ArrayList<>();
        run(maze, visited, blockers);
        visited.forEach(v -> maze.set(v, VISITED));
        logger.info("Maze:\n{}", maze);
        return visited.size();
    }

    public Integer part2(String input) {
        Maze maze = MazeUtils.convertInputToMaze(input);
        List<Vector2D> visited = new ArrayList<>();
        List<Vector2D> blockers = new ArrayList<>();
        run(maze, visited, blockers);
        logger.info("blockers: {}", blockers.size());
        return blockers.size();
    }
}
