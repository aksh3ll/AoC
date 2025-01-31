package fr.akshell.aoc.y2015;

import static org.assertj.core.api.Assertions.assertThat;

import fr.akshell.aoc.base.BaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Day11Test extends BaseTest<String> {

    private static final String INPUT_DEMO_1 = """
abcdefgh
""";
    private static final String INPUT_DEMO_1_PART1_RESULT = "abcdffaa";
    private static final String INPUT_DEMO_1_PART2_RESULT = "abcdffbb";
    private static final String INPUT_DEMO_2 = """
ghijklmn
""";
    private static final String INPUT_DEMO_2_PART1_RESULT = "ghjaabcc";
    private static final String INPUT_DEMO_2_PART2_RESULT = "ghjbbcdd";
    private static final String FINAL_PART1_RESULT = "hepxxyzz";
    private static final String FINAL_PART2_RESULT = "heqaabcc";

    public Day11Test() {
        super(2015, 11, new Day11(),
                INPUT_DEMO_1, INPUT_DEMO_1_PART1_RESULT, INPUT_DEMO_1_PART2_RESULT,
                FINAL_PART1_RESULT, FINAL_PART2_RESULT);
    }

    @Test
    void givenDemoInputs_whenRunningBaseFunctions_thenExpectedResultIsFound() {
        Day11 day11 = (Day11) getDay();
        assertThat(day11.rule1("abbcegjk")).isFalse();
        assertThat(day11.rule1("abcdffaa")).isTrue();
        assertThat(day11.rule2("hijklmmn")).isFalse();
        assertThat(day11.rule2("abcdffaa")).isTrue();
        assertThat(day11.rule3("hijklmmn")).isFalse();
        assertThat(day11.rule3("abcdffaa")).isTrue();
        assertThat(day11.validatePassword("hijklmmn")).isFalse();
        assertThat(day11.validatePassword("abbceffg")).isFalse();
        assertThat(day11.validatePassword("abbcegjk")).isFalse();
        assertThat(day11.validatePassword("abcdffaa")).isTrue();
        assertThat(day11.findNextValidPassword("abcdefgh")).isEqualTo("abcdffaa");
        assertThat(day11.findNextValidPassword("ghijklmn")).isEqualTo("ghjaabcc");
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
