package fr.akshell.aoc.y2015;

import static org.assertj.core.api.Assertions.assertThat;

import fr.akshell.aoc.base.BaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Day6Test extends BaseTest<Integer> {

    private static final String INPUT_DEMO_1 = """
turn on 0,0 through 999,999
toggle 0,0 through 999,0
turn off 499,499 through 500,500
""";
private static final int INPUT_DEMO_1_PART1_RESULT = 998996;
private static final int INPUT_DEMO_1_PART2_RESULT = 1001996;

private static final String INPUT_DEMO_2 = """
turn on 0,0 through 0,0
toggle 0,0 through 999,999
""";
private static final int INPUT_DEMO_2_PART1_RESULT = 999999;
private static final int INPUT_DEMO_2_PART2_RESULT = 2000001;

private static final int FINAL_PART1_RESULT = 400410;
private static final int FINAL_PART2_RESULT = 15343601;

    public Day6Test() {
        super(2015, 6, new Day6(),
                INPUT_DEMO_1, INPUT_DEMO_1_PART1_RESULT, INPUT_DEMO_1_PART2_RESULT,
                FINAL_PART1_RESULT, FINAL_PART2_RESULT);
    }

    @Test
    void givenDemoInput2_whenRunningPart1_thenExpectedResultIsFound() {
        assertThat(getDay().part1(INPUT_DEMO_2)).isEqualTo(INPUT_DEMO_2_PART1_RESULT);
    }

    @Test
    void givenDemoInput2_whenRunningPart2_thenExpectedResultIsFound() {
        assertThat(getDay().part2(INPUT_DEMO_2)).isEqualTo(INPUT_DEMO_2_PART2_RESULT);
    }
}