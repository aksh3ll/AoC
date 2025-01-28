package fr.akshell.aoc.y2015;

import fr.akshell.aoc.base.BaseDay;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.Function;

public class Day2 extends BaseDay<Long> {

    public record Box(long l, long w, long h) {
        public static Box of(long... dimensions) {
            return new Box(dimensions[0], dimensions[1], dimensions[2]);
        }
    }

    private static final Function<Box, Long> WRAPPING_PAPER = box -> {
        long lw = box.l() * box.w();
        long wh = box.w() * box.h();
        long hl = box.h() * box.l();
        return 2 * lw + 2 * wh + 2 * hl + Math.min(Math.min(lw, wh), hl);
    };

    private static final Function<Box, Long> RIBBON = box -> 2 * Math.min(box.l() + box.w(), Math.min(box.w() + box.h(), box.h() + box.l())) + box.l() * box.w() * box.h();

    public Collection<Box> convertInput(String input) {
        return input
                .lines()
                .map(line -> Box.of(
                        Arrays
                                .stream(line.split("x"))
                                .mapToLong(Long::parseLong)
                                .toArray()))
                .toList();
    }

    public Long part1(String input) {
        return convertInput(input).stream().mapToLong(WRAPPING_PAPER::apply).sum();
    }

    public Long part2(String input) {
        return convertInput(input).stream().mapToLong(RIBBON::apply).sum();
    }
}
