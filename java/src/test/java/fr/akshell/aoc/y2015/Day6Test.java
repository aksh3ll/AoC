package fr.akshell.aoc.y2015;

import fr.akshell.aoc.base.BaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class Day6Test extends BaseTest {

    private final static String INPUT_DEMO_1 = """
turn on 0,0 through 999,999
toggle 0,0 through 999,0
turn off 499,499 through 500,500
""";
private final static long INPUT_DEMO_1_PART1_RESULT = 998996;
private final static long INPUT_DEMO_1_PART2_RESULT = 1001996;

private final static String INPUT_DEMO_2 = """
turn on 0,0 through 0,0
toggle 0,0 through 999,999
""";
private final static long INPUT_DEMO_2_PART1_RESULT = 999999;
private final static long INPUT_DEMO_2_PART2_RESULT = 2000001;

private final static long FINAL_PART1_RESULT = 400410;
private final static long FINAL_PART2_RESULT = 15343601;

    Day6 day6 = new Day6();

    public Day6Test() {
        super(2015, 6);
    }

    @Test
    public void givenDemoInput1_whenRunningPart1_thenExpectedResultIsFound() {
        assertThat(day6.part1(INPUT_DEMO_1)).isEqualTo(INPUT_DEMO_1_PART1_RESULT);
    }

    @Test
    public void givenDemoInput1_whenRunningPart2_thenExpectedResultIsFound() {
        assertThat(day6.part2(INPUT_DEMO_1)).isEqualTo(INPUT_DEMO_1_PART2_RESULT);
    }

    @Test
    public void givenDemoInput2_whenRunningPart1_thenExpectedResultIsFound() {
        assertThat(day6.part1(INPUT_DEMO_2)).isEqualTo(INPUT_DEMO_2_PART1_RESULT);
    }

    @Test
    public void givenDemoInput2_whenRunningPart2_thenExpectedResultIsFound() {
        assertThat(day6.part2(INPUT_DEMO_2)).isEqualTo(INPUT_DEMO_2_PART2_RESULT);
    }

    @Test
    public void givenFinalInput_whenRunningPart1_thenExpectedResultIsFound() throws IOException, InterruptedException {
        assertThat(day6.part1(getFinalInput())).isEqualTo(FINAL_PART1_RESULT);
    }

    @Test
    public void givenFinalInput_whenRunningPart2_thenExpectedResultIsFound() throws IOException, InterruptedException {
        assertThat(day6.part2(getFinalInput())).isEqualTo(FINAL_PART2_RESULT);
    }
}