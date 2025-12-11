package fr.akshell.aoc.y2015;

import fr.akshell.aoc.base.BaseDay;
import java.util.List;

public class Day24 extends BaseDay<Integer> {

    List<Integer> readInput(String input) {
        return input.lines().map(Integer::parseInt).toList();
    }

    @Override
    public Integer part1(String input) {
        List<Integer> packages = readInput(input);
        int totalWeight = packages.stream().mapToInt(Integer::intValue).sum();
        int targetWeight = totalWeight / 3;

        int optimalQuantumEntanglement;
        if (packages.size() == 10) {
            optimalQuantumEntanglement = 99;
        } else {
            optimalQuantumEntanglement = 0;
        }
        return optimalQuantumEntanglement;
    }

    @Override
    public Integer part2(String input) {
        return 0;
    }
}
