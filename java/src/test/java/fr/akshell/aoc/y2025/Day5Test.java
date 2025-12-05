package fr.akshell.aoc.y2025;

import fr.akshell.aoc.base.BaseTest;

public class Day5Test extends BaseTest<Integer> {

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
    private static final int INPUT_DEMO_1_PART1_RESULT = 3;
    private static final int INPUT_DEMO_1_PART2_RESULT = -1;
    private static final int FINAL_PART1_RESULT = -1;
    private static final int FINAL_PART2_RESULT = -1;

    public Day5Test() {
        super(2025, 5, new Day5(),
                INPUT_DEMO_1, INPUT_DEMO_1_PART1_RESULT, INPUT_DEMO_1_PART2_RESULT,
                FINAL_PART1_RESULT, FINAL_PART2_RESULT);
    }
}
