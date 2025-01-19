package fr.akshell.aoc.y2015;

import fr.akshell.aoc.base.BaseDay;
import org.springframework.util.DigestUtils;

public class Day4 extends BaseDay<Integer> {

    private int puzzle(String key, String suffix) {
        int i = 0;
        while (true) {
            String hash = DigestUtils.md5DigestAsHex(String.valueOf(key + i).getBytes());
            if (hash.startsWith(suffix)) {
                return i;
            }
            i++;
        }
    }

    public Integer part1(String input) {
        return puzzle(input, "00000");
    }

    public Integer part2(String input) {
        return puzzle(input, "000000");
    }
}
