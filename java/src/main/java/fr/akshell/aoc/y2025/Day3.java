package fr.akshell.aoc.y2025;

import fr.akshell.aoc.base.BaseDay;
import java.util.List;

public class Day3  extends BaseDay<Long> {

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

    public long calcJoltage2(String row) {
        StringBuilder sb = new StringBuilder(row);
        int joltage = 1;
        while (sb.length() > 12) {
            int i = 0;
            while (i < sb.length()) {
                if (sb.charAt(i) == Character.forDigit(joltage, 10)) {
                    sb.deleteCharAt(i);
                    if (sb.length() == 12) {
                        break;
                    }
                } else {
                    i++;
                }
            }
            joltage++;
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
