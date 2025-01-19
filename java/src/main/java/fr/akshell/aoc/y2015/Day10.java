package fr.akshell.aoc.y2015;

import fr.akshell.aoc.base.BaseDay;

public class Day10 extends BaseDay<Integer> {

    public String lookAndSay(String input) {
        StringBuilder result = new StringBuilder();
        char current = input.charAt(0);
        int count = 1;
        for (int i = 1; i < input.length(); i++) {
            if (input.charAt(i) == current) {
                count++;
            } else {
                result.append(count).append(current);
                current = input.charAt(i);
                count = 1;
            }
        }
        result.append(count).append(current);
        return result.toString();
    }

    public String lookAndSay(String input, int times) {
        String result = input;
        for (int i = 0; i < times; i++) {
            result = lookAndSay(result);
        }
        return result;
    }

    public Integer part1(String input) {
        return lookAndSay(input.strip(), 40).length();
    }

    public Integer part2(String input) {
        return lookAndSay(input.strip(), 50).length();
    }
}
