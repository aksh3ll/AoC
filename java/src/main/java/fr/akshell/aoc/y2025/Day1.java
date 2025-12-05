package fr.akshell.aoc.y2025;

import fr.akshell.aoc.base.BaseDay;
import java.util.List;

public class Day1 extends BaseDay<Integer> {
    private static final int START_POSITION = 50;

    public record Move(char direction, int distance) {}

    private int countClicks1(List<Move> moves, int position) {
        int clicks = 0;
        for (Move move : moves) {
            int newPosition;
            newPosition = getNewPosition(position, move);
            position = Math.floorMod(newPosition, 100);
            if (position == 0) {
                clicks++;
            }
        }
        return clicks;
    }

    private static int getNewPosition(int position, Move move) {
        int newPosition;
        if (move.direction == 'L') {
            newPosition = position - move.distance;
        } else if (move.direction == 'R') {
            newPosition = position + move.distance;
        } else {
            throw new IllegalArgumentException("Unknown direction: " + move.direction);
        }
        return newPosition;
    }

    private int countClicks2(List<Move> moves, int position) {
        int clicks = 0;
        for (Move move : moves) {
            int oldPosition = position;
            int newPosition = getNewPosition(position, move);
            if (move.direction == 'L') {
                if (oldPosition == 0) {
                    clicks += Math.abs(Math.floorDiv(newPosition - 1, 100)) - 1;
                } else {
                    clicks += Math.abs(Math.floorDiv(newPosition - 1, 100));
                }
            } else {
                clicks += Math.abs(Math.floorDiv(newPosition, 100));
            }
            position = Math.floorMod(newPosition, 100);
        }
        return clicks;
    }

    @Override
    public Integer part1(String input) {
        List<Move> lines = input.lines()
                .filter(line -> !line.isBlank())
                .map(line -> new Move(line.charAt(0), Integer.parseInt(line.substring(1))))
                .toList();
        return countClicks1(lines, START_POSITION);
    }

    @Override
    public Integer part2(String input) {
        List<Move> lines = input.lines()
                .filter(line -> !line.isBlank())
                .map(line -> new Move(line.charAt(0), Integer.parseInt(line.substring(1))))
                .toList();
        return countClicks2(lines, START_POSITION);
    }
}
