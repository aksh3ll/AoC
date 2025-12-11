package fr.akshell.aoc.y2025;

import fr.akshell.aoc.base.BaseTest;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class Day10Test extends BaseTest<Long> {

    private static final String INPUT_DEMO_1 = """
[.##.] (3) (1,3) (2) (2,3) (0,2) (0,1) {3,5,4,7}
[...#.] (0,2,3,4) (2,3) (0,4) (0,1,2) (1,2,3,4) {7,5,12,7,2}
[.###.#] (0,1,2,3,4) (0,3,4) (0,1,2,4,5) (1,2) {10,11,11,5,10,5}
""";
    private static final long INPUT_DEMO_1_PART1_RESULT = 7L;
    private static final long INPUT_DEMO_1_PART2_RESULT = 33L;
    private static final long FINAL_PART1_RESULT = 488L;
    private static final long FINAL_PART2_RESULT = -1L;

    public Day10Test() {
        super(2025, 10, new Day10(),
                INPUT_DEMO_1, INPUT_DEMO_1_PART1_RESULT, INPUT_DEMO_1, INPUT_DEMO_1_PART2_RESULT,
                FINAL_PART1_RESULT, FINAL_PART2_RESULT);
    }

    @Override
    @Disabled("Test is too slow to run by default")
    @Test
    public void givenDemoInput2_whenRunningPart2_thenExpectedResultIsFound() {
        assertThat(getDay().part2(INPUT_DEMO_1)).isEqualTo(INPUT_DEMO_1_PART2_RESULT);
    }

    @Override
    @Disabled("Test is too slow to run by default")
    @Test
    public void givenFinalInput_whenRunningPart2_thenExpectedResultIsFound() {
        assertThat(getDay().part2(getFinalInput())).isEqualTo(FINAL_PART2_RESULT);
    }
}
