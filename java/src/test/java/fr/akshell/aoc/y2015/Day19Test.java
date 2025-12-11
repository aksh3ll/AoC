package fr.akshell.aoc.y2015;

import static org.assertj.core.api.Assertions.assertThat;

import fr.akshell.aoc.base.BaseTest;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Day19Test extends BaseTest<Integer> {

    private static final String INPUT_DEMO_1 = """
H => HO
H => OH
O => HH

HOH
""";
    private static final int INPUT_DEMO_1_PART1_RESULT = 4;
    private static final int INPUT_DEMO_1_PART2_RESULT = 3;

    private static final String INPUT_DEMO_2 = """
H => HO
H => OH
O => HH

HOHOHO
""";
    private static final int INPUT_DEMO_2_PART1_RESULT = 7;
    private static final int INPUT_DEMO_2_PART2_RESULT = 6;

    private static final int FINAL_PART1_RESULT = 576;
    private static final int FINAL_PART2_RESULT = 886;

    public Day19Test() {
        super(2015, 19, new Day19(),
                INPUT_DEMO_1, INPUT_DEMO_1_PART1_RESULT, INPUT_DEMO_1, INPUT_DEMO_1_PART2_RESULT,
                FINAL_PART1_RESULT, FINAL_PART2_RESULT);
    }

    @Test
    void givenDemoInput2_whenRunningPart1_thenExpectedResultIsFound() {
        assertThat(getDay().part1(INPUT_DEMO_2)).isEqualTo(INPUT_DEMO_2_PART1_RESULT);
    }

    @Disabled("This test is disabled because part 2 is not yet solved")
    @Test
    @Override
    public void givenDemoInput2_whenRunningPart2_thenExpectedResultIsFound() {
        assertThat(getDay().part2(INPUT_DEMO_2)).isEqualTo(INPUT_DEMO_2_PART2_RESULT);
    }

    @Disabled("This test is disabled because part 2 is not yet solved")
    @Test
    @Override
    public void givenFinalInput_whenRunningPart2_thenExpectedResultIsFound() {
        assertThat(DUMMY).isTrue();
    }
}
