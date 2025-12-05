package fr.akshell.aoc.y2025;

import fr.akshell.aoc.base.BaseDay;

import java.util.ArrayList;
import java.util.List;

public class Day5 extends BaseDay<Long> {

    public record Range(long start, long end) {
        public boolean contains(long value) {
            return value >= start && value <= end;
        }

        public boolean intersect(Range other) {
            return this.start <= other.end && other.start <= this.end;
        }

        public static Range merge(Range r1, Range r2) {
            return new Range(Math.min(r1.start, r2.start), Math.max(r1.end, r2.end));
        }
    }

    public record Ranges(List<Range> ranges) {
        public boolean contains(long value) {
            for (Range range : ranges) {
                if (range.contains(value)) {
                    return true;
                }
            }
            return false;
        }
    }

    public record Stock(Ranges ranges, List<Long> ingredients) {}

    private Stock parseInput(String input) {
        String[] parts = input.split("\n\n");
        List<Range> ranges = parts[0].lines()
                .map(line -> {
                    String[] bounds = line.split("-");
                    return new Range(Long.parseLong(bounds[0]), Long.parseLong(bounds[1]));
                })
                .toList();
        List<Long> ingredients = parts[1].lines()
                .map(Long::parseLong)
                .toList();
        return new Stock(new Ranges(ranges), ingredients);
    }

    @Override
    public Long part1(String input) {
        long freshIngredients = 0;
        Stock stock = parseInput(input);
        for (long ingredient : stock.ingredients) {
            if (stock.ranges.contains(ingredient)) {
                freshIngredients++;
            }
        }
        return freshIngredients;
    }

    @Override
    public Long part2(String input) {
        Stock stock = parseInput(input);
        boolean merge = true;
        Ranges ranges = stock.ranges;
        while (merge) {
            merge = false;
            for (int i = 0; i < ranges.ranges.size() - 1; i++) {
                Range r1 = ranges.ranges.get(i);
                for (int j = i + 1; j < ranges.ranges.size(); j++) {
                    Range r2 = ranges.ranges.get(j);
                    if (r1.intersect(r2)) {
                        Range merged = Range.merge(r1, r2);
                        List<Range> newRanges = new ArrayList<>(ranges.ranges.subList(0, i));
                        newRanges.add(merged);
                        newRanges.addAll(ranges.ranges.subList(i + 1, j));
                        newRanges.addAll(ranges.ranges.subList(j + 1, ranges.ranges.size()));
                        ranges = new Ranges(newRanges);
                        merge = true;
                        break;
                    }
                }
                if (merge) {
                    break;
                }
            }
        }

        return ranges.ranges.stream()
                .map(r -> r.end - r.start  + 1)
                .reduce(0L, Long::sum);
    }
}
