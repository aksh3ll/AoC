package fr.akshell.aoc.y2024;

import fr.akshell.aoc.base.BaseTest;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class Day16Test extends BaseTest {

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
    private final static int INPUT_DEMO_1_PUZZLE1_RESULT = 7036;
    private final static int INPUT_DEMO_1_PUZZLE2_RESULT = 45;

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
    private final static int INPUT_DEMO_2_PUZZLE1_RESULT = 11048;
    private final static int INPUT_DEMO_2_PUZZLE2_RESULT = 64;

    private final Day16 day16 = new Day16();

    public Day16Test() {
        super(2024, 16);
    }

    @Test
    public void givenDemoInput1_whenRunningPart1_thenExpectedResultIsFound() {
        assertThat(day16.part1(INPUT_DEMO_1)).isEqualTo(INPUT_DEMO_1_PUZZLE1_RESULT);
    }

    @Test
    public void givenDemoInput2_whenRunningPart1_thenExpectedResultIsFound() {
        assertThat(day16.part1(INPUT_DEMO_2)).isEqualTo(INPUT_DEMO_2_PUZZLE1_RESULT);
    }

    @Disabled
    @Test
    public void givenDemoInput1_whenRunningPart2_thenExpectedResultIsFound() {
        assertThat(day16.part2(INPUT_DEMO_1)).isEqualTo(INPUT_DEMO_1_PUZZLE2_RESULT);
    }

    @Disabled
    @Test
    public void givenDemoInput2_whenRunningPart2_thenExpectedResultIsFound() {
        assertThat(day16.part2(INPUT_DEMO_2)).isEqualTo(INPUT_DEMO_2_PUZZLE2_RESULT);
    }

    @Test
    public void givenFinalInput_whenRunningPart1_thenExpectedResultIsFound() throws IOException, InterruptedException {
        assertThat(day16.part1(getFinalInput())).isEqualTo(122492);
    }

    @Disabled
    @Test
    public void givenFinalInput_whenRunningPart2_thenExpectedResultIsFound() throws IOException, InterruptedException {
        assertThat(day16.part2(getFinalInput())).isEqualTo(0);
    }
}