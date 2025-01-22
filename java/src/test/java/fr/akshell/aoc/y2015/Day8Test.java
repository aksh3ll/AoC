package fr.akshell.aoc.y2015;

import fr.akshell.aoc.base.BaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class Day8Test extends BaseTest<Integer> {

    private final static String INPUT_DEMO_1 = """
""
"abc"
"aaa\\"aaa"
"\\x27"
""";

    private final static int INPUT_DEMO_1_PART1_RESULT = 12;
    private final static int INPUT_DEMO_1_PART2_RESULT = 19;
    private final static int FINAL_PART1_RESULT = 1342;
    private final static int FINAL_PART2_RESULT = 2074;

    public Day8Test() {
        super(2015, 8, new Day8(),
                INPUT_DEMO_1, INPUT_DEMO_1_PART1_RESULT, INPUT_DEMO_1_PART2_RESULT,
                FINAL_PART1_RESULT, FINAL_PART2_RESULT);
    }

    @Test
    public void givenDemoInput1_whenRunningConvertToAscii_thenExpectedResultIsFound() {
        assertThat(Day8.convertToAscii("abc")).isEqualTo("abc");
        assertThat(Day8.convertToAscii("aaa\\\"aaa")).isEqualTo("aaa\\\"aaa");
        assertThat(Day8.convertToAscii("\\x27")).isEqualTo("'");
    }
}
