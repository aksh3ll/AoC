package fr.akshell.aoc.y2024;

import fr.akshell.aoc.base.BaseTest;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class Day16Test extends BaseTest<Integer> {

    private static final String INPUT_DEMO_1 = """
###############
#.......#....E#
#.#.###.#.###.#
#.....#.#...#.#
#.###.#####.#.#
#.#.#.......#.#
#.#.#####.###.#
#...........#.#
###.#.#####.#.#
#...#.....#.#.#
#.#.#.###.#.#.#
#.....#...#.#.#
#.###.#.#.#.#.#
#S..#.....#...#
###############
""";
    private static final int INPUT_DEMO_1_PART1_RESULT = 7036;
    private static final int INPUT_DEMO_1_PART2_RESULT = 45;

    private static final String INPUT_DEMO_2 = """
#################
#...#...#...#..E#
#.#.#.#.#.#.#.#.#
#.#.#.#...#...#.#
#.#.#.#.###.#.#.#
#...#.#.#.....#.#
#.#.#.#.#.#####.#
#.#...#.#.#.....#
#.#.#####.#.###.#
#.#.#.......#...#
#.#.###.#####.###
#.#.#...#.....#.#
#.#.#.#####.###.#
#.#.#.........#.#
#.#.#.#########.#
#S#.............#
#################
""";
    private static final int INPUT_DEMO_2_PART1_RESULT = 11048;
    private static final int INPUT_DEMO_2_PART2_RESULT = 64;
    private static final int FINAL_PART1_RESULT = 122492;
    private static final int FINAL_PART2_RESULT = 0;

    public Day16Test() {
        super(2024, 16, new Day16(),
                INPUT_DEMO_1, INPUT_DEMO_1_PART1_RESULT, INPUT_DEMO_2, INPUT_DEMO_2_PART2_RESULT,
                FINAL_PART1_RESULT, FINAL_PART2_RESULT);
    }

    @Test
    void givenDemoInput2_whenRunningPart1_thenExpectedResultIsFound() {
        assertThat(getDay().part1(INPUT_DEMO_2)).isEqualTo(INPUT_DEMO_2_PART1_RESULT);
    }

    @Disabled("This test is disabled because part 2 is not yet solved")
    @Test
    void givenDemoInput1_whenRunningPart2_thenExpectedResultIsFound() {
        assertThat(getDay().part2(INPUT_DEMO_1)).isEqualTo(INPUT_DEMO_1_PART2_RESULT);
    }

    @Disabled("This test is disabled because part 2 is not yet solved")
    @Test
    @Override
    public void givenDemoInput2_whenRunningPart2_thenExpectedResultIsFound() {
        assertThat(DUMMY).isTrue();
    }

    @Disabled("This test is disabled because part 2 is not yet solved")
    @Test
    @Override
    public void givenFinalInput_whenRunningPart2_thenExpectedResultIsFound() {
        assertThat(DUMMY).isTrue();
    }
}