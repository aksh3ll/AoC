package fr.akshell.aoc.y2025;

import fr.akshell.aoc.base.BaseDay;

import java.util.List;

public class Day1 extends BaseDay<Integer> {

    private final static int START_POSITION = 50;

    public record Move(char direction, int distance) {}

    private int countClicks1(List<Move> moves, int position) {
        int clicks = 0;
        for (int i = 0; i < moves.size(); i++) {
            Move move = moves.get(i);
            int change = 0;
            if (move.direction == 'L') {
                change = -move.distance;
            } else if (move.direction == 'R') {
                change = move.distance;
            } else {
                throw new IllegalArgumentException("Unknown direction: " + move.direction);
            }
            position = Math.floorMod(position + change, 100);
            if (position == 0) {
                clicks++;
            }
        }
        return clicks;
    }

    private int countClicks2(List<Move> moves, int position) {
        int clicks = 0;
        for (int i = 0; i < moves.size(); i++) {
            Move move = moves.get(i);
            int change = 0;
            if (move.direction == 'L') {
                change = -move.distance;
            } else if (move.direction == 'R') {
                change = move.distance;
            } else {
                throw new IllegalArgumentException("Unknown direction: " + move.direction);
            }
            clicks += Math.abs(Math.floorDiv(position + change, 100));
            position = Math.floorMod(position + change, 100);
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
