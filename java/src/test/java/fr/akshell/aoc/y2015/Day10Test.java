package fr.akshell.aoc.y2015;

import static org.assertj.core.api.Assertions.assertThat;

import fr.akshell.aoc.base.BaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Day10Test extends BaseTest<Integer> {

    private final static String INPUT_DEMO_1 = "1";
    private final static int INPUT_DEMO_1_PART1_RESULT = 82350;
    private final static int INPUT_DEMO_1_PART2_RESULT = 1166642;
    private final static int FINAL_PART1_RESULT = 492982;
    private final static int FINAL_PART2_RESULT = 6989950;

    public Day10Test() {
        super(2015, 10, new Day10(),
                INPUT_DEMO_1, INPUT_DEMO_1_PART1_RESULT, INPUT_DEMO_1_PART2_RESULT,
                FINAL_PART1_RESULT, FINAL_PART2_RESULT);
    }

    @Test
    public void givenDemoInput1_whenRunningConvertToAscii_thenExpectedResultIsFound() {
        assertThat(((Day10) getDay()).lookAndSay("1")).isEqualTo("11");
        assertThat(((Day10) getDay()).lookAndSay("11")).isEqualTo("21");
        assertThat(((Day10) getDay()).lookAndSay("21")).isEqualTo("1211");
        assertThat(((Day10) getDay()).lookAndSay("1211")).isEqualTo("111221");
        assertThat(((Day10) getDay()).lookAndSay("111221")).isEqualTo("312211");
    }
}