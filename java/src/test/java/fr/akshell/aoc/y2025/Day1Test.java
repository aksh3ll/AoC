package fr.akshell.aoc.y2025;

import fr.akshell.aoc.base.BaseTest;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Day1Test extends BaseTest<Integer> {
    private static final String INPUT_DEMO_1 = """
L68
L30
R48
L5
R60
L55
L1
L99
R14
L82
""";
    private static final int INPUT_DEMO_1_PART1_RESULT = 3;
    private static final int INPUT_DEMO_1_PART2_RESULT = 6;
    private static final int FINAL_PART1_RESULT = 1048;
    private static final int FINAL_PART2_RESULT = 6498;

    public Day1Test() {
        super(2025, 1, new Day1(),
                INPUT_DEMO_1, INPUT_DEMO_1_PART1_RESULT, INPUT_DEMO_1, INPUT_DEMO_1_PART2_RESULT,
                FINAL_PART1_RESULT, FINAL_PART2_RESULT);
    }
}
