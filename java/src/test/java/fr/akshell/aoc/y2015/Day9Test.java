package fr.akshell.aoc.y2015;

import fr.akshell.aoc.base.BaseTest;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

class Day9Test extends BaseTest {

    private final static String INPUT_DEMO_1 = """
London to Dublin = 464
London to Belfast = 518
Dublin to Belfast = 141
""";
    private final static long INPUT_DEMO_1_PART1_RESULT = 605;
    private final static long INPUT_DEMO_1_PART2_RESULT = 982;
    private final static long FINAL_PART1_RESULT = Long.MAX_VALUE;
    private final static long FINAL_PART2_RESULT = Long.MAX_VALUE;

    Day9 day9 = new Day9();

    public Day9Test() {
        super(2015, 9);
    }

    @Test
    public void givenDemoInput1_whenRunningPart1_thenExpectedResultIsFound() {
        assertThat(day9.part1(INPUT_DEMO_1)).isEqualTo(INPUT_DEMO_1_PART1_RESULT);
    }

    @Test
    public void givenDemoInput1_whenRunningPart2_thenExpectedResultIsFound() {
        assertThat(day9.part2(INPUT_DEMO_1)).isEqualTo(INPUT_DEMO_1_PART2_RESULT);
    }

    @Test
    public void givenFinalInput_whenRunningPart1_thenExpectedResultIsFound() throws IOException, InterruptedException {
        assertThat(day9.part1(getFinalInput())).isEqualTo(FINAL_PART1_RESULT);
    }

    @Test
    public void givenFinalInput_whenRunningPart2_thenExpectedResultIsFound() throws IOException, InterruptedException {
        assertThat(day9.part2(getFinalInput())).isEqualTo(FINAL_PART2_RESULT);
    }
}