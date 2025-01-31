package fr.akshell.aoc.y2015;

import fr.akshell.aoc.base.BaseDay;
import java.util.function.UnaryOperator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day11 extends BaseDay<String> {
    public static final int MAX_ITER = 10000000;
    private static final Pattern RE_PASSWORD = Pattern.compile("^([a-z]{8})$");
    private static final Pattern RE_RULE2 = Pattern.compile("[iol]");
    private static final Pattern RE_RULE3 = Pattern.compile("(\\w)\\1");
    private static final UnaryOperator<String> REVERSE = input -> new StringBuilder(input).reverse().toString();

    private char nextLetter(char c) {
        return (char) (c + 1);
    }

    private static int countMatches(Matcher matcher) {
        int count = 0;
        while (matcher.find()) {
            count++;
        }
        return count;
    }

    public boolean rule1(String input) {
        char last = '-';
        int count = 0;
        for (char c : input.toCharArray()) {
            if (c == nextLetter(last)) {
                count += 1;
                if (count >= 3) {
                    return true;
                }
            } else {
                count = 1;
            }
            last = c;
        }
        return false;
    }

    public boolean rule2(String input) {
        return !RE_RULE2.matcher(input).find();
    }

    public boolean rule3(String input)  {
        return countMatches(RE_RULE3.matcher(input)) >= 2;
    }

    public boolean validatePassword(String input) {
        if (!RE_PASSWORD.matcher(input).matches()) {
            throw new RuntimeException(String.format("password `{%s}` should be 8 characters lowercase.", input));
        }
        return rule1(input) && rule2(input) && rule3(input);
    }

    private String incrementPassword(String input) {
        boolean inc = true;
        StringBuilder newPassword = new StringBuilder();
        for (char c : REVERSE.apply(input).toCharArray()) {
            if (inc) {
                c = nextLetter(c);
            }
            if (c > 'z') {
                c = 'a';
            } else {
                inc = false;
            }
            newPassword.append(c);
        }
        return REVERSE.apply(newPassword.toString());
    }

    public String findNextValidPassword(String input) {
        String newPass = input;
        int i = 0;
        while (i++ < MAX_ITER) {
            newPass = incrementPassword(newPass);
            if (validatePassword(newPass)) {
                return newPass;
            }
        }
        throw new RuntimeException("No valid password found");
    }

    public String part1(String input) {
        return findNextValidPassword(input.trim());
    }

    public String part2(String input) {
        return findNextValidPassword(findNextValidPassword(input.trim()));
    }
}
