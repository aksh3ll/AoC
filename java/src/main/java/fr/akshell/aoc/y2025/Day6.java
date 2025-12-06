package fr.akshell.aoc.y2025;

import fr.akshell.aoc.base.BaseDay;
import jakarta.annotation.Nonnull;

public class Day6 extends BaseDay<Long> {

    public record Grid(String[][] data, char[] operations, int rows, int cols) {
        @Override
        @Nonnull
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < cols; c++) {
                    sb.append(data[r][c]);
                    if (c < cols - 1) {
                        sb.append(" | ");
                    }
                }
                sb.append("\n");
            }
            sb.append("Operations: ");
            for (char op : operations) {
                sb.append(op).append(" ");
            }
            return sb.toString();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Grid grid = (Grid) o;

            if (rows != grid.rows) return false;
            if (cols != grid.cols) return false;
            if (!java.util.Arrays.deepEquals(data, grid.data)) return false;
            return java.util.Arrays.equals(operations, grid.operations);
        }

        @Override
        public int hashCode() {
            int result = java.util.Arrays.deepHashCode(data);
            result = 31 * result + java.util.Arrays.hashCode(operations);
            result = 31 * result + rows;
            result = 31 * result + cols;
            return result;
        }
    }

    public static int indexOfFirstNonSpace(String s, int start) {
        if (s == null)
            return -1;
        int len = s.length();
        if (start < 0)
            start = 0;
        if (start >= len)
            return -1;
        for (int i = start; i < len; i++) {
            if (!Character.isWhitespace(s.charAt(i))) {
                return i;
            }
        }
        return -1;
    }

    private Grid parseInput(String input) {
        String[] lines = input.lines().filter(line -> !line.isBlank()).toArray(String[]::new);
        int rows = lines.length - 1;
        int cols = lines[rows].trim().split("\\s+").length;

        // Extract the operations
        char[] operations = new char[cols];
        String[] cells = lines[rows].trim().split("\\s+");
        for (int c = 0; c < cols; c++) {
            operations[c] = cells[c].charAt(0);
        }

        // Extract the grid data
        String[][] data = new String[rows][cols];
        int pos = 0;
        for (int col = 0; col < cols; col++) {
            int nextPos = indexOfFirstNonSpace(lines[rows], pos + 1);
            for (int r = 0; r < rows; r++) {
                if (nextPos == -1) {
                    data[r][col] = lines[r].substring(pos);
                } else {
                    data[r][col] = lines[r].substring(pos, nextPos - 1);
                }
            }
            pos = nextPos;
        }

        return new Grid(data, operations, rows, cols);
    }

    @Override
    public Long part1(String input) {
        Grid grid = parseInput(input);
        long sum = 0L;
        for (int c = 0; c < grid.cols; c++) {
            long colResult = Long.parseLong(grid.data[0][c].trim());
            for (int r = 1; r < grid.rows; r++) {
                char op = grid.operations[c];
                long val = Long.parseLong(grid.data[r][c].trim());
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

    public String extractColumn(Grid grid, int colIndex, int subIndex) {
        StringBuilder sb = new StringBuilder();
        for (int r = 0; r < grid.rows; r++) {
            String cell = grid.data[r][colIndex];
            if (subIndex < cell.length()) {
                sb.append(cell.charAt(subIndex));
            }
        }
        return sb.toString();
    }

    @Override
    public Long part2(String input) {
        Grid grid = parseInput(input);
        long sum = 0L;
        for (int c = 0; c < grid.cols; c++) {
            long colResult = Long.parseLong(extractColumn(grid, c, 0).trim());
            int subCols = grid.data[0][c].length();
            char op = grid.operations[c];
            for (int r = 1; r < subCols; r++) {
                long val = Long.parseLong(extractColumn(grid, c, r).trim());
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
}
