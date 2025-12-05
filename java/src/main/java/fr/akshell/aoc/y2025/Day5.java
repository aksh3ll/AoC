package fr.akshell.aoc.y2025;

import fr.akshell.aoc.base.BaseDay;

import java.util.List;

public class Day5 extends BaseDay<Integer> {

    public record Range(int start, int end) {
        public boolean contains(int value) {
            return value >= start && value <= end;
        }
    }

    public record Stock(List<Range> ranges, List<Integer> ingredients) {}

    private Stock parseInput(String input) {
        String[] parts = input.split("\n\n");
        List<Range> ranges = parts[0].lines()
                .map(line -> {
                    String[] bounds = line.split("-");
                    return new Range(Integer.parseInt(bounds[0]), Integer.parseInt(bounds[1]));
                })
                .toList();
        List<Integer> ingredients = parts[1].lines()
                .map(Integer::parseInt)
                .toList();
        return new Stock(ranges, ingredients);
    }

    @Override
    public Integer part1(String input) {
        int freshIngredients = 0;
        Stock stock = parseInput(input);
        for (int ingredient : stock.ingredients) {
            for (Range range : stock.ranges) {
                if (range.contains(ingredient)) {
                    freshIngredients++;
                    break;
                }
            }
        }
        return freshIngredients;
    }

    @Override
    public Integer part2(String input) {
        Stock stock = parseInput(input);
        return null;
    }
}
