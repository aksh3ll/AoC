package fr.akshell.aoc.y2015;

import fr.akshell.aoc.base.BaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class Day3Test extends BaseTest<Integer> {
    private static final String INPUT_DEMO_1 = "^>v<";
    private static final int INPUT_DEMO_1_PART1_RESULT = 4;
    private static final int INPUT_DEMO_1_PART2_RESULT = 3;
    private static final int FINAL_PART1_RESULT = 2592;
    private static final int FINAL_PART2_RESULT = 2360;

    public Day3Test() {
        super(2015, 3, new Day3(),
                INPUT_DEMO_1, INPUT_DEMO_1_PART1_RESULT, INPUT_DEMO_1_PART2_RESULT,
                FINAL_PART1_RESULT, FINAL_PART2_RESULT);
    }

    @Test
    public void givenDemoInput1_whenRunningPart1_thenExpectedResultIsFound() {
        assertThat(getDay().part1(">")).isEqualTo(2);
        assertThat(getDay().part1("^>v<")).isEqualTo(4);
        assertThat(getDay().part1("^v^v^v^v^v")).isEqualTo(2);
    }

    @Test
    public void givenDemoInput1_whenRunningPart2_thenExpectedResultIsFound() {
        assertThat(getDay().part2("^v")).isEqualTo(3);
        assertThat(getDay().part2("^>v<")).isEqualTo(3);
        assertThat(getDay().part2("^v^v^v^v^v")).isEqualTo(11);
    }
}