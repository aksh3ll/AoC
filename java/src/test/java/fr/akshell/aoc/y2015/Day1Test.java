package fr.akshell.aoc.y2015;

import static org.assertj.core.api.Assertions.assertThat;

import fr.akshell.aoc.base.BaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Day1Test extends BaseTest<Integer> {

    private final static String INPUT_DEMO_1 = """
)())())
""";
    private final static int INPUT_DEMO_1_PART1_RESULT = -3;
    private final static int INPUT_DEMO_1_PART2_RESULT = 0;
    private final static int FINAL_PART1_RESULT = 232;
    private final static int FINAL_PART2_RESULT = 1783;


    Day1Test() {
        super(2015, 1, new Day1(),
                INPUT_DEMO_1, INPUT_DEMO_1_PART1_RESULT, INPUT_DEMO_1_PART2_RESULT,
                FINAL_PART1_RESULT, FINAL_PART2_RESULT);
    }

    @Test
    public void givenDemoInput1_whenRunningPart1_thenExpectedResultIsFound() {
        assertThat(getDay().part1("(())")).isEqualTo(0);
        assertThat(getDay().part1("()()")).isEqualTo(0);
        assertThat(getDay().part1("(((")).isEqualTo(3);
        assertThat(getDay().part1("(()(()(")).isEqualTo(3);
        assertThat(getDay().part1("))(((((")).isEqualTo(3);
        assertThat(getDay().part1("())")).isEqualTo(-1);
        assertThat(getDay().part1("))(")).isEqualTo(-1);
        assertThat(getDay().part1(")))")).isEqualTo(-3);
        assertThat(getDay().part1(")())())")).isEqualTo(-3);
    }

    @Test
    public void givenDemoInput1_whenRunningPart2_thenExpectedResultIsFound() {
        assertThat(getDay().part2(")")).isEqualTo(1);
        assertThat(getDay().part2("()())")).isEqualTo(5);
    }
}
