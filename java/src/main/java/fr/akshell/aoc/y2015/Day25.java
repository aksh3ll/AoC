package fr.akshell.aoc.y2015;

import fr.akshell.aoc.base.BaseDay;

import java.util.regex.Pattern;

public class Day25 extends BaseDay<Long> {
    private static final Pattern RE_POSITION = Pattern.compile(
            "^.+ row (\\d+), column (\\d+)\\.$");

    public record Position(int row, int column) {}

    protected static Position parseInput(String input) {
        var matcher = RE_POSITION.matcher(input.strip());
        if (matcher.matches()) {
            return new Position(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)));
        }
        throw new IllegalArgumentException("Invalid input: " + input);
    }

    protected static long computeCodeAtPosition(Position position) {
        long code = 20151125L;
        int targetIndex = (position.row + position.column - 2) * (position.row + position.column - 1) / 2 + position.column - 1;
        for (int i = 0; i < targetIndex; i++) {
            code = (code * 252533L) % 33554393L;
        }
        return code;
    }

    @Override
    public Long part1(String input) {
        return computeCodeAtPosition(parseInput(input));
    }

    @Override
    public Long part2(String input) {
        return 0L;
    }
}
