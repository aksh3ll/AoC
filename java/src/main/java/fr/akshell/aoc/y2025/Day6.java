package fr.akshell.aoc.y2025;

import fr.akshell.aoc.base.BaseDay;

public class Day6 extends BaseDay<Long> {

    public record Grid(long[][] data, char[] operations, int rows, int cols) {}

    private Grid parseInput(String input) {
        String[] lines = input.lines()
                .filter(line -> !line.isBlank())
                .toArray(String[]::new);
        int rows = lines.length - 1;
        int cols = lines[0].trim().split("\\s+").length;
        long[][] data = new long[rows][cols];
        for (int r = 0; r < rows; r++) {
            String[] cells = lines[r].trim().split("\\s+");
            for (int c = 0; c < cols; c++) {
                data[r][c] = Long.parseLong(cells[c]);
            }
        }
        char[] operations = new char[cols];
        String[] cells = lines[rows].trim().split("\\s+");
        for (int c = 0; c < cols; c++) {
            operations[c] = cells[c].charAt(0);
        }
        return new Grid(data, operations, rows, cols);
    }

    @Override
    public Long part1(String input) {
        Grid grid = parseInput(input);
        long sum = 0L;
        for (int c = 0; c < grid.cols; c++) {
            long colResult = grid.data[0][c];
            for (int r = 1; r < grid.rows; r++) {
                char op = grid.operations[c];
                long val = grid.data[r][c];
                switch (op) {
                    case '+' -> colResult += val;
                    case '*' -> colResult *= val;
                    default -> throw new IllegalArgumentException("Unknown operation: " + op);
                }
            }
            sum += colResult;
        }
        return sum;
    }

    @Override
    public Long part2(String input) {
        Grid grid = parseInput(input);
        return 0L;
    }
}
