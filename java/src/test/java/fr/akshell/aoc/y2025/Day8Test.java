package fr.akshell.aoc.y2025;

import fr.akshell.aoc.base.BaseTest;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Day8Test extends BaseTest<Long> {

    private static final String INPUT_DEMO_1 = """
""";
    private static final long INPUT_DEMO_1_PART1_RESULT = -1L;
    private static final long INPUT_DEMO_1_PART2_RESULT = -1L;
    private static final long FINAL_PART1_RESULT = -1L;
    private static final long FINAL_PART2_RESULT = -1L;

    public Day8Test() {
        super(2025, 8, new Day8(),
                INPUT_DEMO_1, INPUT_DEMO_1_PART1_RESULT, INPUT_DEMO_1_PART2_RESULT,
                FINAL_PART1_RESULT, FINAL_PART2_RESULT);
    }
}
