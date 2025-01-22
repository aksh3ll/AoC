package fr.akshell.aoc.y2015;

import static fr.akshell.aoc.utils.MiscUtils.getAllCombinations;

import fr.akshell.aoc.base.BaseDay;
import lombok.Setter;
import java.util.List;

@Setter
public class Day17 extends BaseDay<Integer> {

    private int containerSize;

    private List<Integer> convertInput(String input) {
        return input.lines().filter(s -> !s.isBlank()).mapToInt(Integer::parseInt).boxed().toList();
    }

    private List<List<Integer>> filterToSize(List<List<Integer>> combinations, int containerSize) {
        combinations
                .removeIf(combination -> combination.stream().mapToInt(Integer::intValue).sum() != containerSize);
        return combinations;
    }

    @Override
    public Integer part1(String input) {
        List<Integer> containers = convertInput(input);
        List<List<Integer>> combinations = filterToSize(getAllCombinations(containers), containerSize);
        return combinations.size();
    }

    @Override
    public Integer part2(String input) {
        List<Integer> containers = convertInput(input);
        List<List<Integer>> combinations = filterToSize(getAllCombinations(containers), containerSize);
        int min = combinations.stream().mapToInt(List::size).min().orElseThrow();
        combinations.removeIf(combination -> combination.size() != min);
        return combinations.size();
    }
}
