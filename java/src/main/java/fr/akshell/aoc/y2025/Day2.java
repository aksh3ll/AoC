package fr.akshell.aoc.y2025;

import fr.akshell.aoc.base.BaseDay;

import java.util.Arrays;
import java.util.List;

public class Day2 extends BaseDay<Long> {
    public record Range(long start, long end) {}

    private boolean isInvalidId1(long id) {
        String idStr = Long.toString(id);
        int length = idStr.length();
        if (length % 2 == 1) {
            return false;
        }
        return idStr.substring(length / 2).equals(idStr.substring(0, length / 2));
    }

    private boolean isInvalidId2(long id) {
        String idStr = Long.toString(id);
        int length = idStr.length();
        for (int i = 1; i <= length / 2; i++) {
            String pattern = idStr.substring(0, i);
            if (idStr.matches("(" + pattern + ")+")) {
                return true;
            }
        }
        return false;
    }

    private List<Range> extractRanges(String input) {
        return Arrays.stream(input.replace("\n", "")
                .split(",")).toList()
                .stream()
                .filter(s -> !s.isBlank())
                .map(s -> {
                    String[] parts = s.split("-");
                    return new Range(Long.parseLong(parts[0]), Long.parseLong(parts[1]));
                })
                .toList();
    }

    @Override
    public Long part1(String input) {
        List<Range> ranges = extractRanges(input);
        return ranges.stream()
                .flatMap(range -> java.util.stream.LongStream.rangeClosed(range.start, range.end).boxed())
                .filter(this::isInvalidId1)
                .reduce(0L, Long::sum);
    }

    @Override
    public Long part2(String input) {
        List<Range> ranges = extractRanges(input);
        return ranges.stream()
                .flatMap(range -> java.util.stream.LongStream.rangeClosed(range.start, range.end).boxed())
                .filter(this::isInvalidId2)
                .reduce(0L, Long::sum);
    }
}
