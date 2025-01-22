package fr.akshell.aoc.y2015;

import fr.akshell.aoc.base.BaseTest;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Day2Test extends BaseTest<Long> {

    private final static String INPUT_DEMO_1 = "2x3x4";
    private final static long INPUT_DEMO_1_PART1_RESULT = 58;
    private final static long INPUT_DEMO_1_PART2_RESULT = 34;
    private final static long FINAL_PART1_RESULT = 1598415;
    private final static long FINAL_PART2_RESULT = 3812909;

    public Day2Test() {
        super(2015, 2, new Day2(),
                INPUT_DEMO_1, INPUT_DEMO_1_PART1_RESULT, INPUT_DEMO_1_PART2_RESULT,
                FINAL_PART1_RESULT, FINAL_PART2_RESULT);
    }
}
