package fr.akshell.aoc.y2025;

import fr.akshell.aoc.base.BaseDay;
import jakarta.annotation.Nonnull;
import java.util.List;

public class Day4 extends BaseDay<Integer> {

    public record Position(int row, int col) {
        public static Position of(int row, int col) {
            return new Position(row, col);
        }
    }

    public record Grid(char[][] cells) {
        public static Grid fromInput(String input) {
            String[] lines = input.lines()
                    .filter(line -> !line.isBlank())
                    .toArray(String[]::new);
            char[][] cells = new char[lines.length][lines[0].length()];
            for (int i = 0; i < lines.length; i++) {
                cells[i] = lines[i].toCharArray();
            }
            return new Grid(cells);
        }

        public void removeRoll(Position pos) {
            cells[pos.row][pos.col] = '.';
        }

        public List<Position> getMovables() {
            List<Position> movables = new java.util.ArrayList<>();
            for (int i = 0; i < cells.length; i++) {
                for (int j = 0; j < cells[i].length; j++) {
                    Position current = Position.of(i, j);
                    if (cells[i][j] == '@' && countAdjacentRolls(this, current) < 4) {
                        movables.add(current);
                    }
                }
            }
            return movables;
        }

        @Override
        @Nonnull
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (char[] row : cells) {
                sb.append(new String(row)).append("\n");
            }
            return sb.toString();
        }

        @Override
        public int hashCode() {
            int result = 1;
            for (char[] row : cells) {
                result = 31 * result + java.util.Arrays.hashCode(row);
            }
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof Grid(char[][] cells2))) return false;
            if (cells.length != cells2.length) return false;
            for (int i = 0; i < cells.length; i++) {
                if (!java.util.Arrays.equals(cells[i], cells2[i])) {
                    return false;
                }
            }
            return true;
        }
    }

    public enum Direction {
        TOP_LEFT_CORNER(-1, -1),
        TOP_RIGHT_CORNER(-1, 1),
        BOTTOM_LEFT_CORNER(1, -1),
        BOTTOM_RIGHT_CORNER(1, 1),
        UP(-1, 0),
        DOWN(1, 0),
        LEFT(0, -1),
        RIGHT(0, 1);

        public final int deltaRow;
        public final int deltaCol;

        Direction(int deltaRow, int deltaCol) {
            this.deltaRow = deltaRow;
            this.deltaCol = deltaCol;
        }
    }

    public static boolean isValidPosition(Grid grid, Position pos) {
        return pos.row >= 0 && pos.row < grid.cells.length &&
               pos.col >= 0 && pos.col < grid.cells[0].length;
    }

    public static int countAdjacentRolls(Grid grid, Position pos) {
        int count = 0;
        for (Direction dir : Direction.values()) {
            Position newPos = Position.of(pos.row + dir.deltaRow, pos.col + dir.deltaCol);
            if (isValidPosition(grid, newPos) && grid.cells[newPos.row][newPos.col] == '@') {
                count++;
            }
        }
        return count;
    }

    @Override
    public Integer part1(String input) {
        Grid grid = Grid.fromInput(input);
        List<Position> movables = grid.getMovables();
        return movables.size();
    }

    @Override
    public Integer part2(String input) {
        Grid grid = Grid.fromInput(input);
        int deletedRolls = 0;
        while (true) {
            List<Position> movables = grid.getMovables();
            if (movables.isEmpty()) {
                break;
            }
            deletedRolls += movables.size();
            movables.forEach(grid::removeRoll);
        }

        return deletedRolls;
    }
}
