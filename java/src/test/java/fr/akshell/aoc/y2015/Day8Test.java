package fr.akshell.aoc.y2015;

import fr.akshell.aoc.base.BaseTest;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;


class Day8Test extends BaseTest {

    private final static String INPUT_DEMO_1 = """
""
"abc"
"aaa\\"aaa"
"\\x27"
""";

    private final static long INPUT_DEMO_1_PART1_RESULT = 12;
    private final static long INPUT_DEMO_1_PART2_RESULT = 19;
    private final static long FINAL_PART1_RESULT = 1342;
    private final static long FINAL_PART2_RESULT = 2074;

    Day8 day8 = new Day8();

    public Day8Test() {
        super(2015, 8);
    }

    @Test
    public void givenDemoInput1_whenRunningPart1_thenExpectedResultIsFound() {
        assertThat(day8.part1(INPUT_DEMO_1)).isEqualTo(INPUT_DEMO_1_PART1_RESULT);
    }

    @Test
    public void givenDemoInput1_whenRunningPart2_thenExpectedResultIsFound() {
        assertThat(day8.part2(INPUT_DEMO_1)).isEqualTo(INPUT_DEMO_1_PART2_RESULT);
    }

    @Test
    public void givenFinalInput_whenRunningPart1_thenExpectedResultIsFound() throws IOException, InterruptedException {
        assertThat(day8.part1(getFinalInput())).isEqualTo(FINAL_PART1_RESULT);
    }

    @Test
    public void givenFinalInput_whenRunningPart2_thenExpectedResultIsFound() throws IOException, InterruptedException {
        assertThat(day8.part2(getFinalInput())).isEqualTo(FINAL_PART2_RESULT);
    }
}