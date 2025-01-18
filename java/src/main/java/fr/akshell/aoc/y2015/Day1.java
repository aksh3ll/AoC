package fr.akshell.aoc.y2015;

import fr.akshell.aoc.base.BaseDay;

public class Day1 extends BaseDay {

    public long part1(String input) {
        return input.chars().map(c -> c == '(' ? 1 : -1).sum();
    }

    public long part2(String input) {
        int floor = 0;
        for (int i = 0; i < input.length(); i++) {
            floor += input.charAt(i) == '(' ? 1 : -1;
            if (floor == -1) {
                return i + 1;
            }
        }
        return -1;
    }
}
