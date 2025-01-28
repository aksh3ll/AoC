package fr.akshell.aoc.y2015;

import fr.akshell.aoc.base.BaseDay;

import java.util.Arrays;
import java.util.function.Function;
import java.util.regex.Pattern;

public class Day5 extends BaseDay<Integer> {
    
    // rule 1 V1: at least 3 vowels
    private static final Pattern RE_RULE1_V1 = Pattern.compile("[aeiou].*[aeiou].*[aeiou]");
    // rule 2 V1: at least one double letter
    private static final Pattern RE_RULE2_V1 = Pattern.compile("(\\w)\\1");
    // rule 3 V1: not couples ab, cd, pq or xy
    private static final Pattern RE_RULE3_V1 = Pattern.compile("(ab|cd|pq|xy)");
    // Apply all rules for V1
    private static final Function<String, Boolean> NICE_STRING_V1 =
            string -> RE_RULE1_V1.matcher(string).find()
                    && RE_RULE2_V1.matcher(string).find()
                    && !RE_RULE3_V1.matcher(string).find();

    // rule 1 V2:
    private static final Pattern RE_RULE1_V2 = Pattern.compile("(\\w{2}).*\\1");
    // rule 2 V2:
    private static final Pattern RE_RULE2_V2 = Pattern.compile("(\\w).\\1");
    // Apply all rules for V2
    private static final Function<String, Boolean> NICE_STRING_V2 =
            string -> RE_RULE1_V2.matcher(string).find()
                    && RE_RULE2_V2.matcher(string).find();

    public int countNiceStrings(Function<String, Boolean> niceStringTest, String input) {
            return input
                    .lines()
                    .mapToInt(line -> niceStringTest.apply(line) ? 1 : 0)
                    .sum();
    }

    public Integer part1(String input) {
        return countNiceStrings(NICE_STRING_V1, input);
    }

    public Integer part2(String input) {
        return countNiceStrings(NICE_STRING_V2, input);
    }
}
