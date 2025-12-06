package fr.akshell.aoc.y2025;

import fr.akshell.aoc.base.BaseDay;

import java.util.ArrayList;
import java.util.Comparator;
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

    private final Comparator<String> compareJoltage = (s1, s2) -> {
        for (int i = 0; i < Math.min(s1.length(), s2.length()); i++) {
            int c1 = Character.getNumericValue(s1.charAt(i));
            int c2 = Character.getNumericValue(s2.charAt(i));
            if (c1 != c2) {
                return Integer.compare(c1, c2);
            }
        }
        return Integer.compare(s1.length(), s2.length());
    };

    public long calcJoltage2(String row) {
        while (row.length() > 12) {
            List<String> candidates = new ArrayList<>();
            for (int i = 0; i < row.length(); i++) {
                candidates.add(row.substring(0, i) + row.substring(i + 1));
            }
            row = candidates.stream().max(compareJoltage).orElse(row);
        }
        return Long.parseLong(row);
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
