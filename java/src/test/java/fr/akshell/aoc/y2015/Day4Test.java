package fr.akshell.aoc.y2015;

import static org.assertj.core.api.Assertions.assertThat;

import fr.akshell.aoc.base.BaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Day4Test extends BaseTest<Integer> {

    private final static String INPUT_DEMO_1 = "abcdef";
    private final static int INPUT_DEMO_1_PART1_RESULT = 609043;
    private final static int INPUT_DEMO_1_PART2_RESULT = 6742839;
    private final static String INPUT_DEMO_2 = "pqrstuv";
    private final static int INPUT_DEMO_2_PART1_RESULT = 1048970;
    private final static int INPUT_DEMO_2_PART2_RESULT = 5714438;
    private final static int FINAL_PART1_RESULT = 117946;
    private final static int FINAL_PART2_RESULT = 3938038;

    public Day4Test() {
        super(2015, 4, new Day4(),
                INPUT_DEMO_1, INPUT_DEMO_1_PART1_RESULT, INPUT_DEMO_1_PART2_RESULT,
                FINAL_PART1_RESULT, FINAL_PART2_RESULT);
    }

    @Test
    public void givenDemoInput2_whenRunningPart1_thenExpectedResultIsFound() {
        assertThat(getDay().part1(INPUT_DEMO_2)).isEqualTo(INPUT_DEMO_2_PART1_RESULT);
    }

    @Test
    public void givenDemoInput2_whenRunningPart2_thenExpectedResultIsFound() {
        assertThat(getDay().part2(INPUT_DEMO_2)).isEqualTo(INPUT_DEMO_2_PART2_RESULT);
    }
}
