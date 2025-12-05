package fr.akshell.aoc.y2025;

import fr.akshell.aoc.base.BaseTest;

class Day2Test extends BaseTest<Long> {

    private static final String INPUT_DEMO_1 = """
11-22,95-115,998-1012,1188511880-1188511890,222220-222224,
1698522-1698528,446443-446449,38593856-38593862,565653-565659,
824824821-824824827,2121212118-2121212124
""";
    private static final long INPUT_DEMO_1_PART1_RESULT = 1227775554L;
    private static final long INPUT_DEMO_1_PART2_RESULT = 4174379265L;
    private static final long FINAL_PART1_RESULT = 12850231731L;
    private static final long FINAL_PART2_RESULT = 24774350322L;

    public Day2Test() {
        super(2025, 2, new Day2(),
                INPUT_DEMO_1, INPUT_DEMO_1_PART1_RESULT, INPUT_DEMO_1_PART2_RESULT,
                FINAL_PART1_RESULT, FINAL_PART2_RESULT);
    }
}
