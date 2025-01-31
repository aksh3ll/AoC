package fr.akshell.aoc.y2015;

import static org.springframework.util.DigestUtils.md5DigestAsHex;

import fr.akshell.aoc.base.BaseDay;

public class Day4 extends BaseDay<Integer> {

    private int puzzle(String key, String suffix) {
        int i = 0;
        while (true) {
            String hash = md5DigestAsHex((key + i).getBytes());
            if (hash.startsWith(suffix)) {
                return i;
            }
            i++;
        }
    }

    public Integer part1(String input) {
        return puzzle(input.strip(), "00000");
    }

    public Integer part2(String input) {
        return puzzle(input.strip(), "000000");
    }
}
