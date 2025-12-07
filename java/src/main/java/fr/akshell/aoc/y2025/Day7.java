package fr.akshell.aoc.y2025;

import fr.akshell.aoc.base.BaseDay;

import java.util.ArrayList;
import java.util.List;

public class Day7 extends BaseDay<Long> {

    public record Position(int row, int col) {}

    private char[][] parseInput(String input) {
        String[] lines = input.split("\n");
        int rows = lines.length;
        int cols = lines[0].length();
        char[][] grid = new char[rows][cols];
        for (int r = 0; r < rows; r++) {
            grid[r] = lines[r].toCharArray();
        }
        return grid;
    }

    private Position findPosition(char[][] grid, char target) {
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[r].length; c++) {
                if (grid[r][c] == target) {
                    return new Position(r, c);
                }
            }
        }
        return null;
    }

    @Override
    public Long part1(String input) {
        char[][] grid = parseInput(input);
        Position start = findPosition(grid, 'S');
        List<Position> beams = new ArrayList<>();
        beams.add(start);
        long countSplits = 0;
        while (!beams.isEmpty()) {
            Position current = beams.remove(0);
            int r = current.row + 1;
            if (r < grid.length) {
                continue;
            }
            int c = current.col;
            if (grid[r][c] == '^') {
                countSplits++;
                beams.add(new Position(r, c - 1));
                beams.add(new Position(r, c + 1));
            }
        }

        return countSplits;
    }

    @Override
    public Long part2(String input) {
        char[][] grid = parseInput(input);
        return -1L;
    }
}
