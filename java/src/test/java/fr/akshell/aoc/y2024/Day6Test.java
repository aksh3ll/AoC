package fr.akshell.aoc.y2024;


import fr.akshell.aoc.base.BaseTest;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Day6Test extends BaseTest<Integer> {

    private static final String INPUT_DEMO_1 = """
....#.....
.........#
..........
..#.......
.......#..
..........
.#..^.....
........#.
#.........
......#...
""";
    private static final int INPUT_DEMO_1_PART1_RESULT = 41;
    private static final int INPUT_DEMO_1_PART2_RESULT = 6;
    private static final int FINAL_PART1_RESULT = 5444;
    private static final int FINAL_PART2_RESULT = 1946;

    public Day6Test() {
        super(2024, 6, new Day6(),
                INPUT_DEMO_1, INPUT_DEMO_1_PART1_RESULT, INPUT_DEMO_1_PART2_RESULT,
                FINAL_PART1_RESULT, FINAL_PART2_RESULT);
    }
}
