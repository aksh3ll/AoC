package fr.akshell.aoc.y2025;

import fr.akshell.aoc.base.BaseTest;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Day5Test extends BaseTest<Long> {

    private static final String INPUT_DEMO_1 = """
3-5
10-14
16-20
12-18

1
5
8
11
17
32
""";
    private static final long INPUT_DEMO_1_PART1_RESULT = 3L;
    private static final long INPUT_DEMO_1_PART2_RESULT = 14L;
    private static final long FINAL_PART1_RESULT = 733L;
    private static final long FINAL_PART2_RESULT = 345821388687084L;

    public Day5Test() {
        super(2025, 5, new Day5(),
                INPUT_DEMO_1, INPUT_DEMO_1_PART1_RESULT, INPUT_DEMO_1_PART2_RESULT,
                FINAL_PART1_RESULT, FINAL_PART2_RESULT);
    }
}
