package fr.akshell.aoc.y2015;

import fr.akshell.aoc.base.BaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class Day5Test extends BaseTest {

    private final static String INPUT_DEMO_1 = """
ugknbfddgicrmopn
aaa
jchzalrnumimnmhp
haegwjzuvuyypxyu
dvszwmarrgswjxmb
""";
    private final static long INPUT_DEMO_1_PART1_RESULT = 2;
    private final static long INPUT_DEMO_1_PART2_RESULT = 0;

   private final static String INPUT_DEMO_2 = """
qjhvhtzxzqqjkmpb
xxyxx
uurcxstgmygtbstg
ieodomkazucvgmuy
""";
    private final static long INPUT_DEMO_2_PART1_RESULT = 0;
    private final static long INPUT_DEMO_2_PART2_RESULT = 2;

    private final static long FINAL_PART1_RESULT = 236;
    private final static long FINAL_PART2_RESULT = 51;

    Day5 day5 = new Day5();

    public Day5Test() {
        super(2015, 5);
    }

    @Test
    public void givenDemoInput1_whenRunningPart1_thenExpectedResultIsFound() {
        assertThat(day5.part1(INPUT_DEMO_1)).isEqualTo(INPUT_DEMO_1_PART1_RESULT);
    }

    @Test
    public void givenDemoInput2_whenRunningPart1_thenExpectedResultIsFound() {
        assertThat(day5.part1(INPUT_DEMO_2)).isEqualTo(INPUT_DEMO_2_PART1_RESULT);
    }

    @Test
    public void givenDemoInput1_whenRunningPart2_thenExpectedResultIsFound() {
        assertThat(day5.part2(INPUT_DEMO_1)).isEqualTo(INPUT_DEMO_1_PART2_RESULT);
    }

    @Test
    public void givenDemoInput2_whenRunningPart2_thenExpectedResultIsFound() {
        assertThat(day5.part2(INPUT_DEMO_2)).isEqualTo(INPUT_DEMO_2_PART2_RESULT);
    }

    @Test
    public void givenFinalInput_whenRunningPart1_thenExpectedResultIsFound() throws IOException, InterruptedException {
        assertThat(day5.part1(getFinalInput())).isEqualTo(FINAL_PART1_RESULT);
    }

    @Test
    public void givenFinalInput_whenRunningPart2_thenExpectedResultIsFound() throws IOException, InterruptedException {
        assertThat(day5.part2(getFinalInput())).isEqualTo(FINAL_PART2_RESULT);
    }
}