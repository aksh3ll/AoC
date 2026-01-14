package fr.akshell.aoc.y2015;

import fr.akshell.aoc.base.BaseTest;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class Day20Test extends BaseTest<Integer> {

    private static final String INPUT_DEMO_1 = "120";
    private static final int INPUT_DEMO_1_PART1_RESULT = 6;
    private static final int INPUT_DEMO_1_PART2_RESULT = 6;
    private static final int FINAL_PART1_RESULT = 786240;
    private static final int FINAL_PART2_RESULT = 831600;

    public Day20Test() {
        super(2015, 20, new Day20(),
                INPUT_DEMO_1, INPUT_DEMO_1_PART1_RESULT, INPUT_DEMO_1, INPUT_DEMO_1_PART2_RESULT,
                FINAL_PART1_RESULT, FINAL_PART2_RESULT);
    }

    @Disabled("This test is disabled because part 2 is not yet solved")
    @Override
    @Test
    public void givenFinalInput_whenRunningPart2_thenExpectedResultIsFound() {
        assertThat(DUMMY).isTrue();
    }

    @Test
    void testSumOfDivisors() {
        assertThat(Day20.sumFactors(6)).isEqualTo(12);
        assertThat(Day20.sumFactors(28)).isEqualTo(56);
        assertThat(Day20.sumFactors(12)).isEqualTo(28);
    }
}
