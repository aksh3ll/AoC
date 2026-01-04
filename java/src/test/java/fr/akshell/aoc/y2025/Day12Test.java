package fr.akshell.aoc.y2025;

import fr.akshell.aoc.base.BaseTest;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Fail.fail;

@SpringBootTest
public class Day12Test extends BaseTest<Long> {

    private static final String INPUT_DEMO_1 = """
0:
###
##.
##.

1:
###
##.
.##

2:
.##
###
##.

3:
##.
###
##.

4:
###
#..
###

5:
###
.#.
###

4x4: 0 0 0 0 2 0
12x5: 1 0 1 0 2 2
12x5: 1 0 1 0 3 2
""";
    private static final long INPUT_DEMO_1_PART1_RESULT = 2L;
    private static final long INPUT_DEMO_1_PART2_RESULT = -1L;
    private static final long FINAL_PART1_RESULT = -1L;
    private static final long FINAL_PART2_RESULT = -1L;

    public Day12Test() {
        super(2025, 12, new Day12(),
                INPUT_DEMO_1, INPUT_DEMO_1_PART1_RESULT, INPUT_DEMO_1, INPUT_DEMO_1_PART2_RESULT,
                FINAL_PART1_RESULT, FINAL_PART2_RESULT);
    }

    @Disabled("Not fully implemented yet")
    @Override
    @Test
    public void givenDemoInput1_whenRunningPart1_thenExpectedResultIsFound() {
        fail("Not implemented yet");
    }

    @Disabled("Not fully implemented yet")
    @Override
    @Test
    public void givenFinalInput_whenRunningPart1_thenExpectedResultIsFound() {
        fail("Not implemented yet");
    }
}
