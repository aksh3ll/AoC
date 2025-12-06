package fr.akshell.aoc.y2025;

import fr.akshell.aoc.base.BaseDay;
import java.util.List;

public class Day3 extends BaseDay<Long> {

    private  List<String> extractGrid(String input) {
        return input.lines()
                .filter(line -> !line.isBlank())
                .toList();
    }

    public long calcJoltage1(String row) {
        long b1 = 0;
        int b1Pos = 0;
        long b2 = 0;
        for (int i = 0; i < row.length() - 1; i++) {
            long iVal = Character.getNumericValue(row.charAt(i));
            if (iVal > b1) {
                b1 = iVal;
                b1Pos = i;
            }
        }
        for (int i = b1Pos + 1; i < row.length(); i++) {
            long iVal = Character.getNumericValue(row.charAt(i));
            if (iVal > b2) {
                b2 = iVal;
            }
        }
        return b1 * 10L + b2;
    }

    private int findLowestJoltagePos(StringBuilder sb, int limit) {
        int minJoltage = 10;
        int pos = -1;
        for (int i = 0; i < limit; i++) {
            int joltage = Character.getNumericValue(sb.charAt(i));
            if (joltage < minJoltage) {
                minJoltage = joltage;
                pos = i;
            }
        }
        return pos;
    }

    private int findHighestJoltagePos(StringBuilder sb, int start, int limit) {
        int maxJoltage = -1;
        int pos = -1;
        for (int i = start; i < limit; i++) {
            int joltage = Character.getNumericValue(sb.charAt(i));
            if (joltage > maxJoltage) {
                maxJoltage = joltage;
                pos = i;
            }
        }
        return pos;
    }

    public long calcJoltage2(String row) {
        StringBuilder sb = new StringBuilder(row);
        int start = 0;
        while (sb.length() > 12) {
            int highestJoltagePos = findHighestJoltagePos(sb, start, sb.length() - 12 + start);
            if (highestJoltagePos > 0) {
                sb.delete(start, highestJoltagePos);
            }
            start++;
        }
        return Long.parseLong(sb.toString());
    }

    @Override
    public Long part1(String input) {
        return extractGrid(input).stream().map(this::calcJoltage1).reduce(0L, Long::sum);
    }

    @Override
    public Long part2(String input) {
        return extractGrid(input).stream().map(this::calcJoltage2).reduce(0L, Long::sum);
    }
}
