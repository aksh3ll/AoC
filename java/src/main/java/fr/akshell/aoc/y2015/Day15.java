package fr.akshell.aoc.y2015;

import fr.akshell.aoc.base.BaseDay;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

public class Day15  extends BaseDay<Long>  {
    private static final Pattern RE_INGREDIENT = Pattern.compile(
            "^(\\w+): capacity (-?\\d+), durability (-?\\d+), flavor (-?\\d+), texture (-?\\d+), calories (-?\\d+)$");

    private record Ingredient(String name, int capacity, int durability, int flavor, int texture, int calories) {}

    private List<Ingredient> convertInput(String input) {
        return Arrays
                .stream(input.split("\n"))
                .filter(s -> !s.isBlank())
                .map(s -> {
                    var matcher = RE_INGREDIENT.matcher(s);
                    if (matcher.matches()) {
                        return new Ingredient(matcher.group(1), Integer.parseInt(matcher.group(2)),
                                Integer.parseInt(matcher.group(3)), Integer.parseInt(matcher.group(4)),
                                Integer.parseInt(matcher.group(5)), Integer.parseInt(matcher.group(6)));
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .toList();
    }

    private long calculateScore(List<Ingredient> ingredients, long[] quantities, long withCalories) {
        long capacity = 0;
        long durability = 0;
        long flavor = 0;
        long texture = 0;
        long calories = 0;
        for (int i = 0; i < ingredients.size(); i++) {
            var ingredient = ingredients.get(i);
            capacity += ingredient.capacity() * quantities[i];
            durability += ingredient.durability() * quantities[i];
            flavor += ingredient.flavor() * quantities[i];
            texture += ingredient.texture() * quantities[i];
            calories += ingredient.calories() * quantities[i];
        }
        long score = Math.max(0L, capacity) * Math.max(0L, durability) * Math.max(0L, flavor) * Math.max(0L, texture);
        if (withCalories > 0 && calories != withCalories) {
            score = -1L;
        }
        return score;
    }

    private Long solver(String input, long withCalories) {
        var ingredients = convertInput(input);
        long maxScore = 0;
        int n = ingredients.size();
        int totalQuantity = 100;
        long[] quantities = new long[n];
        while (quantities[0] <= totalQuantity) {
            long quantitySum = 0;
            for (int i = 0; i < n - 1; i++) {
                quantitySum += quantities[i];
            }
            quantities[n - 1] = totalQuantity - quantitySum;

            long score = calculateScore(ingredients, quantities, withCalories);
            if (score > maxScore) {
                maxScore = score;
            }

            for (int i = n - 2; i >= 0; i--) {
                if (quantities[i] < totalQuantity) {
                    quantities[i]++;
                    break;
                } else if (i == 0) {
                    return maxScore;
                } else {
                    quantities[i] = 0;
                }
            }
        }
        return maxScore;

    }

    @Override
    public Long part1(String input) {
        return solver(input, 0L);
    }

    @Override
    public Long part2(String input) {
        return solver(input, 500L);
    }
}
