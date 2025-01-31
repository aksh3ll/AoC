package fr.akshell.aoc.y2024;

import fr.akshell.aoc.base.BaseDay;
import lombok.Getter;
import lombok.Setter;
import java.util.*;

@Getter
public class Day11 extends BaseDay<Long> {
    @Setter
    private int iteration = 0;
    private static final Map<Pair, Long> CACHE = new HashMap<>();

    public record Pair(long stone, int depth) {}

    public static int countDigits(long number) {
        if (number == 0) {
            return 1;
        }
        return (int) Math.floor(Math.log10(Math.abs(number))) + 1;
    }

    List<Long> applyRules(long stone) {
        if (stone == 0) {
            return List.of(1L);
        }
        int digits = countDigits(stone);
        if ((digits & 1) == 0) {
            long divisor = (long) Math.pow(10, digits >> 1);
            return List.of(stone / divisor, stone % divisor);
        }
        return List.of(stone * 2024L);
    }

    public Long part1(String input) {
        List<Long> stones = Arrays.stream(input.split("\\s+")).map(String::strip).map(Long::parseLong).toList();
        for (int i = 0; i < iteration; i++) {
            var newStones = new ArrayList<Long>();
            for (Long stone : stones) {
                newStones.addAll(applyRules(stone));
            }
            stones = newStones;
        }
        return (long) stones.size();
    }

    public long applyRules2(long stone, int iteration) {
        if (iteration == 0) {
            return 1;
        }

        Pair key = new Pair(stone, iteration);
        Long value = CACHE.get(key);
        if (value != null) {
            return value;
        }

        if (stone == 0) {
            value = applyRules2(1, iteration - 1);
        } else {
            int digits = countDigits(stone);
            if ((digits & 1) == 0) {
                long divisor = (long) Math.pow(10, digits >> 1);
                value = applyRules2(stone / divisor, iteration - 1)
                        + applyRules2(stone % divisor, iteration - 1);
            } else {
                value = applyRules2(stone * 2024, iteration - 1);
            }
        }

        CACHE.put(new Pair(stone, iteration), value);
        return value;
    }

    public Long part2(String input) {
        List<Long> stones = Arrays.stream(input.split("\\s+")).map(String::strip).map(Long::parseLong).toList();
        CACHE.clear();
        long stonesCount = 0;
        for (Long stone : stones) {
            stonesCount += applyRules2(stone, iteration);
        }
        return stonesCount;
    }
}
