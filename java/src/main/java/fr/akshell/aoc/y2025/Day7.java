package fr.akshell.aoc.y2025;

import fr.akshell.aoc.base.BaseDay;
import jakarta.annotation.Nonnull;

import java.util.*;

public class Day7 extends BaseDay<Long> {
    private static final char START_POSITION = 'S';
    private char[][] grid;

    public record Position(int row, int col) {
        public Position left() {
            return new Position(row, col - 1);
        }
        public Position right() {
            return new Position(row, col + 1);
        }
        public Position down() {
            return new Position(row + 1, col);
        }
        @Override
        @Nonnull
        public String toString() {
            return "(" + row + "," + col + ")";
        }
    }
    private Map<Position, Long> cacheTimelines;

    private void parseInput(String input) {
        String[] lines = input.split("\n");
        int rows = lines.length;
        int cols = lines[0].length();
        grid = new char[rows][cols];
        for (int r = 0; r < rows; r++) {
            grid[r] = lines[r].toCharArray();
        }
    }

    private Position findStartPosition() {
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[r].length; c++) {
                if (grid[r][c] == START_POSITION) {
                    return new Position(r, c);
                }
            }
        }
        return null;
    }

    @Override
    public Long part1(String input) {
        parseInput(input);
        Position start = findStartPosition();

        Set<Position> beams = new LinkedHashSet<>();
        beams.add(start);
        long countSplits = 0;
        while (!beams.isEmpty()) {
            Set<Position> newBeams = new LinkedHashSet<>();
            for (Position current : beams) {
                int r = current.row + 1;
                int c = current.col;
                if (r >= grid.length) {
                    continue;
                }
                if (grid[r][c] == '^') {
                    countSplits++;
                    newBeams.add(new Position(r, c - 1));
                    newBeams.add(new Position(r, c + 1));
                } else if (grid[r][c] == '.') {
                    newBeams.add(new Position(r, c));
                }
            }
            beams = newBeams;
        }

        return countSplits;
    }

    public long countTimelines(Position current) {
        Long timelines = cacheTimelines.get(current);
        if (timelines == null) {
            Position newPosition = current.down();
            if (newPosition.row() >= grid.length) {
                timelines = 1L;
            } else {
                char cellValue = grid[newPosition.row()][newPosition.col()];
                if (cellValue == '^') {
                    timelines = countTimelines(newPosition.left()) + countTimelines(newPosition.right());
                } else if (cellValue == '.') {
                    timelines = countTimelines(newPosition);
                } else {
                    throw new IllegalStateException("Unexpected cell value in position " + newPosition + " -> " + cellValue);
                }
            }
            cacheTimelines.put(newPosition, timelines);
        }
        return timelines;
    }

    @Override
    public Long part2(String input) {
        parseInput(input);
        Position start = findStartPosition();
        assert start != null;
        cacheTimelines = new HashMap<>();
        return countTimelines(start);
    }
}
