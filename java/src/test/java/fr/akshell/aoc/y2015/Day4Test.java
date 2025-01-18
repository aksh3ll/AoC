package fr.akshell.aoc.y2015;

import fr.akshell.aoc.base.BaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class Day4Test extends BaseTest {

    private final static String INPUT_DEMO_1 = "abcdef";
    private final static long INPUT_DEMO_1_PART1_RESULT = 609043;
    private final static long INPUT_DEMO_1_PART2_RESULT = 6742839;
    private final static String INPUT_DEMO_2 = "pqrstuv";
    private final static long INPUT_DEMO_2_PART1_RESULT = 1048970;
    private final static long INPUT_DEMO_2_PART2_RESULT = 5714438;
    private final static String FINAL_INPUT = "ckczppom";
    private final static long FINAL_PART1_RESULT = 117946;
    private final static long FINAL_PART2_RESULT = 3938038;

    Day4 day4 = new Day4();

    public Day4Test() {
        super(2015, 4);
    }

    @Test
    public void givenDemoInput1_whenRunningPart1_thenExpectedResultIsFound() {
        assertThat(day4.part1(INPUT_DEMO_1)).isEqualTo(INPUT_DEMO_1_PART1_RESULT);
    }

    @Test
    public void givenDemoInput2_whenRunningPart1_thenExpectedResultIsFound() {
        assertThat(day4.part1(INPUT_DEMO_2)).isEqualTo(INPUT_DEMO_2_PART1_RESULT);
    }

    @Test
    public void givenDemoInput1_whenRunningPart2_thenExpectedResultIsFound() {
        assertThat(day4.part2(INPUT_DEMO_1)).isEqualTo(INPUT_DEMO_1_PART2_RESULT);
    }

    @Test
    public void givenDemoInput2_whenRunningPart2_thenExpectedResultIsFound() {
        assertThat(day4.part2(INPUT_DEMO_2)).isEqualTo(INPUT_DEMO_2_PART2_RESULT);
    }

    @Test
    public void givenFinalInput_whenRunningPart1_thenExpectedResultIsFound() throws IOException, InterruptedException {
        assertThat(day4.part1(FINAL_INPUT)).isEqualTo(FINAL_PART1_RESULT);
    }

    @Test
    public void givenFinalInput_whenRunningPart2_thenExpectedResultIsFound() throws IOException, InterruptedException {
        assertThat(day4.part2(FINAL_INPUT)).isEqualTo(FINAL_PART2_RESULT);
    }
}
