package fr.akshell.aoc.y2015;

import fr.akshell.aoc.base.BaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class Day10Test extends BaseTest {

    private final static String INPUT_DEMO_1 = "1";
    private final static long INPUT_DEMO_1_PART1_RESULT = 82350;
    private final static long INPUT_DEMO_1_PART2_RESULT = 1166642;
    private final static long FINAL_PART1_RESULT = 492982;
    private final static long FINAL_PART2_RESULT = 6989950;

    Day10 day10 = new Day10();

    public Day10Test() {
        super(2015, 10);
    }

    @Test
    public void givenDemoInput1_whenRunningConvertToAscii_thenExpectedResultIsFound() {
        assertThat(day10.lookAndSay("1")).isEqualTo("11");
        assertThat(day10.lookAndSay("11")).isEqualTo("21");
        assertThat(day10.lookAndSay("21")).isEqualTo("1211");
        assertThat(day10.lookAndSay("1211")).isEqualTo("111221");
        assertThat(day10.lookAndSay("111221")).isEqualTo("312211");
    }

    @Test
    public void givenDemoInput1_whenRunningPart1_thenExpectedResultIsFound() {
        assertThat(day10.part1(INPUT_DEMO_1)).isEqualTo(INPUT_DEMO_1_PART1_RESULT);
    }

    @Test
    public void givenDemoInput1_whenRunningPart2_thenExpectedResultIsFound() {
        assertThat(day10.part2(INPUT_DEMO_1)).isEqualTo(INPUT_DEMO_1_PART2_RESULT);
    }

    @Test
    public void givenFinalInput_whenRunningPart1_thenExpectedResultIsFound() throws IOException, InterruptedException {
        assertThat(day10.part1(getFinalInput())).isEqualTo(FINAL_PART1_RESULT);
    }

    @Test
    public void givenFinalInput_whenRunningPart2_thenExpectedResultIsFound() throws IOException, InterruptedException {
        assertThat(day10.part2(getFinalInput())).isEqualTo(FINAL_PART2_RESULT);
    }
}