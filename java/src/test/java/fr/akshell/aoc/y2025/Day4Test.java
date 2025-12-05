package fr.akshell.aoc.y2025;

import fr.akshell.aoc.base.BaseTest;

public class Day4Test extends BaseTest<Integer> {

    private static final String INPUT_DEMO_1 = """
..@@.@@@@.
@@@.@.@.@@
@@@@@.@.@@
@.@@@@..@.
@@.@@@@.@@
.@@@@@@@.@
.@.@.@.@@@
@.@@@.@@@@
.@@@@@@@@.
@.@.@@@.@.
""";
    private static final int INPUT_DEMO_1_PART1_RESULT = 13;
    private static final int INPUT_DEMO_1_PART2_RESULT = 43;
    private static final int FINAL_PART1_RESULT = 1356;
    private static final int FINAL_PART2_RESULT = 8713;

    public Day4Test() {
        super(2025, 4, new Day4(),
                INPUT_DEMO_1, INPUT_DEMO_1_PART1_RESULT, INPUT_DEMO_1_PART2_RESULT,
                FINAL_PART1_RESULT, FINAL_PART2_RESULT);
    }
}
