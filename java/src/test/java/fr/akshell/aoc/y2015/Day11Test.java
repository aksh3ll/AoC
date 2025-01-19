package fr.akshell.aoc.y2015;

import fr.akshell.aoc.base.BaseTest;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;


class Day11Test extends BaseTest {

    private final static String INPUT_DEMO_1 = """
abcdefgh
""";
    private final static String INPUT_DEMO_1_PART1_RESULT = "abcdffaa";
    private final static String INPUT_DEMO_1_PART2_RESULT = "abcdffbb";
    private final static String INPUT_DEMO_2 = """
ghijklmn
""";
    private final static String INPUT_DEMO_2_PART1_RESULT = "ghjaabcc";
    private final static String INPUT_DEMO_2_PART2_RESULT = "ghjbbcdd";
    private final static String FINAL_PART1_RESULT = "hepxxyzz";
    private final static String FINAL_PART2_RESULT = "heqaabcc";


    Day11 day11 = new Day11();

    public Day11Test() {
        super(2015, 11);
    }

    @Test
    public void givenDemoInputs_whenRunningBaseFunctions_thenExpectedResultIsFound() {
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
    public void givenDemoInput1_whenRunningPart1_thenExpectedResultIsFound() {
        assertThat(day11.part1(INPUT_DEMO_1)).isEqualTo(INPUT_DEMO_1_PART1_RESULT);
    }

    @Test
    public void givenDemoInput1_whenRunningPart2_thenExpectedResultIsFound() {
        assertThat(day11.part2(INPUT_DEMO_1)).isEqualTo(INPUT_DEMO_1_PART2_RESULT);
    }

    @Test
    public void givenDemoInput2_whenRunningPart1_thenExpectedResultIsFound() {
        assertThat(day11.part1(INPUT_DEMO_2)).isEqualTo(INPUT_DEMO_2_PART1_RESULT);
    }

    @Test
    public void givenDemoInput2_whenRunningPart2_thenExpectedResultIsFound() {
        assertThat(day11.part2(INPUT_DEMO_2)).isEqualTo(INPUT_DEMO_2_PART2_RESULT);
    }

    @Test
    public void givenFinalInput_whenRunningPart1_thenExpectedResultIsFound() throws IOException, InterruptedException {
        assertThat(day11.part1(getFinalInput())).isEqualTo(FINAL_PART1_RESULT);
    }

    @Test
    public void givenFinalInput_whenRunningPart2_thenExpectedResultIsFound() throws IOException, InterruptedException {
        assertThat(day11.part2(getFinalInput())).isEqualTo(FINAL_PART2_RESULT);
    }
}