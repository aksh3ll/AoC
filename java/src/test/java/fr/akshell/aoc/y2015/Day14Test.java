package fr.akshell.aoc.y2015;

import static org.assertj.core.api.Assertions.assertThat;

import fr.akshell.aoc.base.BaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Day14Test extends BaseTest<Integer> {

    private static final String INPUT_DEMO_1 = """
Comet can fly 14 km/s for 10 seconds, but then must rest for 127 seconds.
Dancer can fly 16 km/s for 11 seconds, but then must rest for 162 seconds.
""";
    private static final int INPUT_DEMO_1_PART1_RESULT = 1120;
    private static final int INPUT_DEMO_1_PART2_RESULT = 689;
    private static final int FINAL_PART1_RESULT = 2640;
    private static final int FINAL_PART2_RESULT = 1102;

    public Day14Test() {
        super(2015, 14, new Day14(),
                INPUT_DEMO_1, INPUT_DEMO_1_PART1_RESULT, INPUT_DEMO_1, INPUT_DEMO_1_PART2_RESULT,
                FINAL_PART1_RESULT, FINAL_PART2_RESULT);
    }

    @Override
    @Test
    protected void givenDemoInput1_whenRunningPart1_thenExpectedResultIsFound() {
        Day14 day14 = (Day14) getDay();
        day14.setTime(1000);
        assertThat(day14.part1(INPUT_DEMO_1)).isEqualTo(INPUT_DEMO_1_PART1_RESULT);
    }

    @Override
    @Test
    protected void givenDemoInput2_whenRunningPart2_thenExpectedResultIsFound() {
        Day14 day14 = (Day14) getDay();
        day14.setTime(1000);
        assertThat(day14.part2(INPUT_DEMO_1)).isEqualTo(INPUT_DEMO_1_PART2_RESULT);
    }

    @Override
    @Test
    protected void givenFinalInput_whenRunningPart1_thenExpectedResultIsFound() {
        Day14 day14 = (Day14) getDay();
        day14.setTime(2503);
        assertThat(getDay().part1(getFinalInput())).isEqualTo(FINAL_PART1_RESULT);
    }

    @Override
    @Test
    protected void givenFinalInput_whenRunningPart2_thenExpectedResultIsFound() {
        Day14 day14 = (Day14) getDay();
        day14.setTime(2503);
        assertThat(getDay().part2(getFinalInput())).isEqualTo(FINAL_PART2_RESULT);
    }
}
