package fr.akshell.aoc.y2015;

import fr.akshell.aoc.base.BaseDay;
import lombok.Getter;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.BiFunction;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day6 extends BaseDay<Integer> {

    @Getter
    private enum Order {
        TURN_ON("turn on"), TURN_OFF("turn off"), TOGGLE("toggle");

        private final String order;

        Order(String order) {
            this.order = order;
        }

        public static Order of(String order) {
            return Arrays.stream(Order.values())
                    .filter(o -> o.getOrder().equals(order))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Invalid order: " + order));
        }
    }

    private static final Pattern RE_ORDER = Pattern.compile("^([\\w ]+) (\\d+),(\\d+) through (\\d+),(\\d+)$");
    private static final BiFunction<Order, Integer, Integer> SWITCH1 = (order , value) -> switch (order) {
        case TURN_ON -> 1;
        case TURN_OFF -> 0;
        case TOGGLE -> 1 - value;
    };
    private static final BiFunction<Order, Integer, Integer> SWITCH2 = (order , value) -> switch (order) {
        case TURN_ON -> value + 1;
        case TURN_OFF -> Math.max(0, value - 1);
        case TOGGLE -> value + 2;
    };

    private record Instruction(Order order, int x1, int y1, int x2, int y2) {}

    private static List<Instruction> parseInstructions(String input) {
        return input.lines()
                .map(RE_ORDER::matcher)
                .filter(Matcher::matches)
                .map(matcher -> new Instruction(
                        Order.of(matcher.group(1)),
                        Integer.parseInt(matcher.group(2)),
                        Integer.parseInt(matcher.group(3)),
                        Integer.parseInt(matcher.group(4)),
                        Integer.parseInt(matcher.group(5))
                ))
                .toList();
    }

    private int[][] applyInstructions(
            Collection<Instruction> instructions, BiFunction<Order, Integer, Integer> switchFn, int width, int height) {
        int[][] grid = new int[height][width];
        instructions.forEach(instruction -> {
            for (int i = instruction.x1; i <= instruction.x2; i++) {
                for (int j = instruction.y1; j <= instruction.y2; j++) {
                    grid[j][i] = switchFn.apply(instruction.order, grid[j][i]);
                }
            }
        });
        return grid;
    }

    public Integer part1(String input) {
        return Arrays
                .stream(applyInstructions(parseInstructions(input), SWITCH1, 1000, 1000))
                .flatMapToInt(Arrays::stream)
                .sum();
    }

    public Integer part2(String input) {
        return Arrays
                .stream(applyInstructions(parseInstructions(input), SWITCH2, 1000, 1000))
                .flatMapToInt(Arrays::stream)
                .sum();
    }
}
