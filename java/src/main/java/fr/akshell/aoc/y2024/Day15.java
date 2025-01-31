package fr.akshell.aoc.y2024;

import fr.akshell.aoc.base.BaseDay;
import fr.akshell.aoc.pojo.Maze;
import fr.akshell.aoc.pojo.Vector2D;
import fr.akshell.aoc.utils.MazeUtils;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;

import static fr.akshell.aoc.pojo.Maze.EMPTY;
import static fr.akshell.aoc.pojo.Maze.WALL;

public class Day15 extends BaseDay<Integer> {
    public static final char BOX = 'O';
    public static final char L_BOX_LEFT = '[';
    public static final char L_BOX_RIGHT = ']';
    public static final char ROBOT = '@';
    public static final List<Character> MOVABLES = List.of(L_BOX_LEFT, L_BOX_RIGHT);

    public record Day15Input(Maze warehouse, List<Character> moves) {}

    public static class ConundrumException extends RuntimeException {
        public ConundrumException(String message) {
            super(message);
        }
    }

    public static final Map<Character, Vector2D> DIRECTIONS = Map.of(
        '<', new Vector2D(-1, 0),
        '>', new Vector2D(1, 0),
        '^', new Vector2D(0, -1),
        'v', new Vector2D(0, 1)
    );

    static class BlockedException extends RuntimeException {
        public BlockedException(String message) {
            super(message);
        }
    }

    public Maze enlarge(Maze warehouse) {
        int newWidth = warehouse.width() * 2;
        char[][] newWarehouse = new char[warehouse.height()][newWidth];
        for (int y = 0; y < warehouse.height(); y++) {
            for (int x = 0; x < warehouse.width(); x++) {
                char c = warehouse.get(x, y);
                if (c == WALL) {
                    newWarehouse[y][x * 2] = WALL;
                    newWarehouse[y][x * 2 + 1] = WALL;
                } else if (c == EMPTY) {
                    newWarehouse[y][x * 2] = EMPTY;
                    newWarehouse[y][x * 2 + 1] = EMPTY;
                } else if (c == BOX) {
                    newWarehouse[y][x * 2] = L_BOX_LEFT;
                    newWarehouse[y][x * 2 + 1] = L_BOX_RIGHT;
                } else if (c == ROBOT) {
                    newWarehouse[y][x * 2] = ROBOT;
                    newWarehouse[y][x * 2 + 1] = EMPTY;
                }
            }
        }
        return new Maze(newWarehouse, newWidth, warehouse.height());
    }

    public Day15Input convertInput(String input) {
        String[] parts = input.split("\n\n");

        Maze warehouse = MazeUtils.convertInputToMaze(parts[0]);
        List<Character> moves;

        moves = new ArrayList<>();
        for (char c : parts[1].toCharArray()) {
            if (c == '<' || c == '>' || c == '^' || c == 'v') {
                moves.add(c);
            }
        }

        return new Day15Input(warehouse, moves);
    }

    public int calcGpsScore(Maze warehouse, char search) {
        int score = 0;
        for (int y = 0; y < warehouse.height(); y++) {
            for (int x = 0; x < warehouse.width(); x++) {
                if (warehouse.get(x, y) == search) {
                    score += 100 * y + x;
                }
            }
        }
        return score;
    }

    public boolean canMove(Maze warehouse, Vector2D robot, Vector2D dir) {
        int newX = robot.x() + dir.x();
        int newY = robot.y() + dir.y();

        if (warehouse.get(newX, newY) == WALL) {
            return false;
        }

        Vector2D newRobot = new Vector2D(newX, newY);
        if (warehouse.get(newX, newY) == BOX && !canMove(warehouse, newRobot, dir)) {
            return false;
        }

        if (warehouse.get(newX, newY) == EMPTY) {
            char tmp = warehouse.get(newX, newY);
            warehouse.set(newX, newY, warehouse.get(robot.x(), robot.y()));
            warehouse.set(robot.x(), robot.y(), tmp);
            return true;
        }

        return false;
    }

    private void logResult(Maze maze, int score) {
        logger.info("Last state:\n{}", maze);
        logger.info("score: {}", score);
    }

    public Integer part1(String input) {
        Day15Input day15Input = convertInput(input);
        Maze warehouse = day15Input.warehouse;
        List<Character> moves = day15Input.moves;
        final Vector2D[] robot = {warehouse.find(ROBOT)};

        moves.stream().map(DIRECTIONS::get).forEach(direction -> {
            if (canMove(warehouse, robot[0], direction)) {
                robot[0] = new Vector2D(robot[0].x() + direction.x(), robot[0].y() + direction.y());
            }
        });

        int score = calcGpsScore(warehouse, BOX);
        logResult(warehouse, score);
        return score;
    }

    public List<Vector2D> getShifts(Maze warehouse, Vector2D origin, Vector2D dir) {
        int newX = origin.x() + dir.x();
        int newY = origin.y() + dir.y();

        if (warehouse.get(newX, newY) == WALL) {
            throw new BlockedException("We're blocked by a wall!");
        }

        if (MOVABLES.contains(warehouse.get(newX, newY))) {
            if (dir.x() == 0) { // vertical
                int secondDirX = warehouse.get(newX, newY) == L_BOX_LEFT ? 1 : -1;
                return Stream.of(
                        List.of(origin),
                        getShifts(warehouse, new Vector2D(newX, newY), dir),
                        getShifts(warehouse, new Vector2D(newX + secondDirX, newY), dir)
                ).flatMap(Collection::stream).toList();
            } else { // horizontal
                return Stream.of(
                    List.of(origin, new Vector2D(newX, newY)),
                    getShifts(warehouse, new Vector2D(newX + dir.x(), newY), dir)
                ).flatMap(Collection::stream).toList();
            }
        }

        if (warehouse.get(newX, newY) == EMPTY) {
            return List.of(origin);
        }

        throw new ConundrumException("We're stuck in a conundrum!");
    }

    public void shift(Maze warehouse, List<Vector2D> shifts, Vector2D dir) {
        List<Vector2D> mutableShifts = new ArrayList<>(new HashSet<>(shifts));
        mutableShifts.sort((a, b) -> (b.y() - a.y()) * dir.y() + (b.x() - a.x()) * dir.x());

        mutableShifts.forEach(shift -> {
            int newX = shift.x() + dir.x();
            int newY = shift.y() + dir.y();
            char tmp = warehouse.get(newX, newY);
            warehouse.set(newX, newY, warehouse.get(shift));
            warehouse.set(shift, tmp);
        });
    }

    public Integer part2(String input) {
        Day15Input day15Input = convertInput(input);
        Maze warehouse = day15Input.warehouse;
        List<Character> moves = day15Input.moves;
        warehouse = enlarge(warehouse);
        final Vector2D[] robot = {warehouse.find(ROBOT)};
        AtomicReference<Integer> index = new AtomicReference<>(0);

        Maze finalWarehouse = warehouse;
        moves.stream().map(DIRECTIONS::get).forEach(direction -> {
            try {
                List<Vector2D> shifts = getShifts(finalWarehouse, robot[0], direction);
                shift(finalWarehouse, shifts, direction);
                robot[0] = new Vector2D(robot[0].x() + direction.x(), robot[0].y() + direction.y());
            } catch (BlockedException e) {
                // expected sometimes, should be ignored
            } catch (RuntimeException e) {
                throw new ConundrumException("We're stuck in a conundrum on move " + index + "!");
            }
            index.getAndSet(index.get() + 1);
        });

        int score = calcGpsScore(warehouse, L_BOX_LEFT);
        logResult(warehouse, score);
        return score;
    }
}
