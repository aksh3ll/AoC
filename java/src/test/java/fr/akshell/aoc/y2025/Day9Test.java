package fr.akshell.aoc.y2025;

import fr.akshell.aoc.base.BaseTest;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class Day9Test extends BaseTest<Long> {

    private static final String INPUT_DEMO_1 = """
7,1
11,1
11,7
9,7
9,5
2,5
2,3
7,3
""";
    private static final long INPUT_DEMO_1_PART1_RESULT = 50L;
    private static final long INPUT_DEMO_1_PART2_RESULT = 24L;
    private static final long FINAL_PART1_RESULT = 4748826374L;
    private static final long FINAL_PART2_RESULT = -1L;

    public Day9Test() {
        super(2025, 9, new Day9(),
                INPUT_DEMO_1, INPUT_DEMO_1_PART1_RESULT, INPUT_DEMO_1_PART2_RESULT,
                FINAL_PART1_RESULT, FINAL_PART2_RESULT);
    }

    @Disabled("Part 2 is too slow, needs optimization")
    @Override
    @Test
    public void givenFinalInput_whenRunningPart2_thenExpectedResultIsFound() {
        assertThat(getDay().part2(getFinalInput())).isEqualTo(FINAL_PART2_RESULT);
    }
}
