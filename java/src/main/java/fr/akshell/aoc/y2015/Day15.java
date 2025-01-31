package fr.akshell.aoc.y2015;

import fr.akshell.aoc.base.BaseDay;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

public class Day15  extends BaseDay<Integer>  {
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

    private int calculateScore(List<Ingredient> ingredients, int[] quantities) {
        int capacity = 0;
        int durability = 0;
        int flavor = 0;
        int texture = 0;
        int calories = 0;
        for (int i = 0; i < ingredients.size(); i++) {
            var ingredient = ingredients.get(i);
            capacity += ingredient.capacity() * quantities[i];
            durability += ingredient.durability() * quantities[i];
            flavor += ingredient.flavor() * quantities[i];
            texture += ingredient.texture() * quantities[i];
            calories += ingredient.calories() * quantities[i];
        }
        return Math.max(0, capacity) * Math.max(0, durability) * Math.max(0, flavor) * Math.max(0, texture);
    }

    @Override
    public Integer part1(String input) {
        var ingredients = convertInput(input);
        return 0;
    }

    @Override
    public Integer part2(String input) {
        var ingredients = convertInput(input);
        return 0;
    }
}
