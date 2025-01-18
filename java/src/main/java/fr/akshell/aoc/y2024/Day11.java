package fr.akshell.aoc.y2024;

import fr.akshell.aoc.base.BaseDay;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

public class Day11 extends BaseDay {
    @Getter
    @Setter
    private int iteration = 0;

    public record Pair(long stone, int depth) {}
    public Map<Pair, Long> cache = new HashMap<>();

    public static int countDigits(long number) {
        if (number == 0) {
            return 1;
        }
        return (int) Math.floor(Math.log10(Math.abs(number))) + 1;
    }

    List<Long> apply_rules(long stone) {
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

    public long part1(String input) {
        List<Long> stones = Arrays.stream(input.split("\\s+")).map(String::strip).map(Long::parseLong).toList();
        for (int i = 0; i < iteration; i++) {
            var new_stones = new ArrayList<Long>();
            for (Long stone : stones) {
                new_stones.addAll(apply_rules(stone));
            }
            stones = new_stones;
        }
        return stones.size();
    }

    public long apply_rules2(long stone, int iteration) {
        if (iteration == 0) {
            return 1;
        }

        Pair key = new Pair(stone, iteration);
        Long value = cache.get(key);
        if (value != null) {
            return value;
        }

        if (stone == 0) {
            value = apply_rules2(1, iteration - 1);
        } else {
            int digits = countDigits(stone);
            if ((digits & 1) == 0) {
                long divisor = (long) Math.pow(10, digits >> 1);
                value = apply_rules2(stone / divisor, iteration - 1)
                        + apply_rules2(stone % divisor, iteration - 1);
            } else {
                value = apply_rules2(stone * 2024, iteration - 1);
            }
        }

        cache.put(new Pair(stone, iteration), value);
        return value;
    }

    public long part2(String input) {
        List<Long> stones = Arrays.stream(input.split("\\s+")).map(String::strip).map(Long::parseLong).toList();
        cache = new HashMap<>();
        long stones_count = 0;
        for (Long stone : stones) {
            stones_count += apply_rules2(stone, iteration);
        }
        System.out.println(cache.size());
        return stones_count;
    }
}
