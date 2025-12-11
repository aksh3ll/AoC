package fr.akshell.aoc.y2015;

import fr.akshell.aoc.base.BaseDay;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day20 extends BaseDay<Integer> {

    private static final Map<Integer, List<Integer>> factorsCache = new HashMap<>();

    public static List<Integer> findFactors(int number) {
        if (factorsCache.containsKey(number)) {
            return factorsCache.get(number);
        }
        List<Integer> factors = new ArrayList<>();
        for (int i = 1; i <= number; i++) {
            if (number % i == 0) {
                factors.add(i);
            }
        }
        factorsCache.put(number, factors);
        return factors;
    }

    public static int sumFactors(int number) {
        List<Integer> factors = findFactors(number);
        return factors.stream().mapToInt(Integer::intValue).sum();
    }

    public static int sumFactorsWithLimit(int number, int limit) {
        List<Integer> factors = findFactors(number);
        return factors.stream().filter(factor -> factor * limit >= number).mapToInt(Integer::intValue).sum();
    }

    public static int findHouseNumberLinear(int from, int limit) {
        int houseNumber = from;
        while (true) {
            if (sumFactors(houseNumber) * 10 >= limit) {
                return houseNumber;
            }
            houseNumber++;
        }
    }

    public static int findHouseNumberWithLimit(int limit) {
        int houseNumber = 1;
        while (true) {
            if (sumFactorsWithLimit(houseNumber, 10) * 11 >= limit) {
                return houseNumber;
            }
            houseNumber++;
        }
    }

    public Integer part1(String input) {
        int presents = Integer.parseInt(input.trim());
        return findHouseNumberLinear(1, presents);
    }

    public Integer part2(String input) {
        int limit = Integer.parseInt(input.trim());
        return findHouseNumberWithLimit(limit);
    }
}
