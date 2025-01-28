package fr.akshell.aoc.y2015;

import fr.akshell.aoc.base.BaseTest;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Day8Test extends BaseTest<Integer> {

    private final static String INPUT_DEMO_1 = """
""
"abc"
"aaa\\"aaa"
"\\x27"
""";

    private final static int INPUT_DEMO_1_PART1_RESULT = 12;
    private final static int INPUT_DEMO_1_PART2_RESULT = 19;
    private final static int FINAL_PART1_RESULT = 1342;
    private final static int FINAL_PART2_RESULT = 2074;

    public Day8Test() {
        super(2015, 8, new Day8(),
                INPUT_DEMO_1, INPUT_DEMO_1_PART1_RESULT, INPUT_DEMO_1_PART2_RESULT,
                FINAL_PART1_RESULT, FINAL_PART2_RESULT);
    }
}
