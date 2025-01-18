package fr.akshell.aoc.y2015;

import fr.akshell.aoc.base.BaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class Day7Test extends BaseTest {

    private final static String INPUT_DEMO_1 = """
123 -> x
456 -> y
x AND y -> d
x OR y -> e
x LSHIFT 2 -> f
y RSHIFT 2 -> g
NOT x -> h
NOT y -> i
""";
    private final static long INPUT_DEMO_1_PART1_RESULT = 0;
    private final static long INPUT_DEMO_1_PART2_RESULT = 0;
    private final static long FINAL_PART1_RESULT = 3176;
    private final static long FINAL_PART2_RESULT = 14710;

    Day7 day7 = new Day7();

    public Day7Test() {
        super(2015, 7);
    }

    @Test
    public void givenDemoInput1_whenRunningPart1_thenExpectedResultIsFound() {
        assertThat(day7.part1(INPUT_DEMO_1)).isEqualTo(INPUT_DEMO_1_PART1_RESULT);
    }

    @Test
    public void givenDemoInput1_whenRunningPart2_thenExpectedResultIsFound() {
        assertThat(day7.part2(INPUT_DEMO_1)).isEqualTo(INPUT_DEMO_1_PART2_RESULT);
    }

    @Test
    public void givenFinalInput_whenRunningPart1_thenExpectedResultIsFound() throws IOException, InterruptedException {
        assertThat(day7.part1(getFinalInput())).isEqualTo(FINAL_PART1_RESULT);
    }

    @Test
    public void givenFinalInput_whenRunningPart2_thenExpectedResultIsFound() throws IOException, InterruptedException {
        assertThat(day7.part2(getFinalInput())).isEqualTo(FINAL_PART2_RESULT);
    }
}
