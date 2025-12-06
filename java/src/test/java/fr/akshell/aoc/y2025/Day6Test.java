package fr.akshell.aoc.y2025;

import fr.akshell.aoc.base.BaseTest;

public class Day6Test extends BaseTest<Long> {

    private static final String INPUT_DEMO_1 = """
123 328  51 64\s
 45 64  387 23\s
  6 98  215 314
*   +   *   + \s
""";
    private static final long INPUT_DEMO_1_PART1_RESULT = 4277556L;
    private static final long INPUT_DEMO_1_PART2_RESULT = 3263827L;
    private static final long FINAL_PART1_RESULT = 6172481852142L;
    private static final long FINAL_PART2_RESULT = 10188206723429L;

    public Day6Test() {
        super(2025, 6, new Day6(),
                INPUT_DEMO_1, INPUT_DEMO_1_PART1_RESULT, INPUT_DEMO_1_PART2_RESULT,
                FINAL_PART1_RESULT, FINAL_PART2_RESULT);
    }
}
