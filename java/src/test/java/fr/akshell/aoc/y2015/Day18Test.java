package fr.akshell.aoc.y2015;

import fr.akshell.aoc.base.BaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class Day18Test extends BaseTest<Integer> {

    private static final String INPUT_DEMO_1 = """
.#.#.#
...##.
#....#
..#...
#.#..#
####..
""";

        private static final int INPUT_DEMO_1_PART1_RESULT = 4;
        private static final int INPUT_DEMO_1_PART2_RESULT = 17;
        private static final int FINAL_PART1_RESULT = 821;
        private static final int FINAL_PART2_RESULT = 886;

        public Day18Test() {
            super(2015, 18, new Day18(),
                    INPUT_DEMO_1, INPUT_DEMO_1_PART1_RESULT, INPUT_DEMO_1, INPUT_DEMO_1_PART2_RESULT,
                    FINAL_PART1_RESULT, FINAL_PART2_RESULT);
        }

    @Test
    @Override
    protected void givenDemoInput1_whenRunningPart1_thenExpectedResultIsFound() {
        Day18 day18 = (Day18) getDay();
        day18.setSteps(4);
        assertThat(day18.part1(INPUT_DEMO_1)).isEqualTo(INPUT_DEMO_1_PART1_RESULT);
    }

    @Test
    @Override
    protected void givenDemoInput2_whenRunningPart2_thenExpectedResultIsFound() {
        Day18 day18 = (Day18) getDay();
        day18.setSteps(5);
        assertThat(day18.part2(INPUT_DEMO_1)).isEqualTo(INPUT_DEMO_1_PART2_RESULT);
    }

    @Test
    @Override
    protected void givenFinalInput_whenRunningPart1_thenExpectedResultIsFound() {
        Day18 day18 = (Day18) getDay();
        day18.setSteps(100);
        assertThat(day18.part1(getFinalInput())).isEqualTo(FINAL_PART1_RESULT);
    }

    @Test
    @Override
    protected void givenFinalInput_whenRunningPart2_thenExpectedResultIsFound() {
        Day18 day18 = (Day18) getDay();
        day18.setSteps(100);
        assertThat(day18.part2(getFinalInput())).isEqualTo(FINAL_PART2_RESULT);
    }
}
