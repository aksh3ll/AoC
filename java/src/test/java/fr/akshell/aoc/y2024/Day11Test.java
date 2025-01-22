package fr.akshell.aoc.y2024;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.util.List;

import fr.akshell.aoc.base.BaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Day11Test extends BaseTest<Long> {
    private static final String INPUT_DEMO_1 = "125 17";
    private final static long INPUT_DEMO_1_PART1_RESULT = 55312;
    private final static long INPUT_DEMO_1_PART2_RESULT = 55312;
    private final static long FINAL_PART1_RESULT = 193899;
    private final static long FINAL_PART2_RESULT = 229682160383225L;

    Day11Test() {
        super(2024, 11, new Day11(),
                INPUT_DEMO_1, INPUT_DEMO_1_PART1_RESULT, INPUT_DEMO_1_PART2_RESULT,
                FINAL_PART1_RESULT, FINAL_PART2_RESULT);
    }

    @Test
    void givenNumber_whenCountingDigits_thenExpectedResultIsRight() {
        assertThat(Day11.countDigits(123456789)).isEqualTo(9);
        assertThat(Day11.countDigits(9)).isEqualTo(1);
        assertThat(Day11.countDigits(0)).isEqualTo(1);
    }

    @Test
    void givenAStone_whenApplyingRules_thenIGetAListOfStones() {
        Day11 day11 = (Day11) getDay();
        assertThat(day11.apply_rules(0)).isEqualTo(List.of(1L));
        assertThat(day11.apply_rules(1)).isEqualTo(List.of(2024L));
        assertThat(day11.apply_rules(10)).isEqualTo(List.of(1L, 0L));
        assertThat(day11.apply_rules(11)).isEqualTo(List.of(1L, 1L));
        assertThat(((Day11) getDay()).apply_rules(3)).isEqualTo(List.of(3 * 2024L));
    }

    @Test
    void givenABasicListOfStones_whenIExecutePart1_thenIGetTheTotalOfStones() {
        Day11 day11 = (Day11) getDay();
        day11.setIteration(1);
        assertThat(day11.part1(INPUT_DEMO_1)).isEqualTo(3);
        day11.setIteration(2);
        assertThat(day11.part1(INPUT_DEMO_1)).isEqualTo(4);
        day11.setIteration(3);
        assertThat(day11.part1(INPUT_DEMO_1)).isEqualTo(5);
        day11.setIteration(4);
        assertThat(day11.part1(INPUT_DEMO_1)).isEqualTo(9);
        day11.setIteration(5);
        assertThat(day11.part1(INPUT_DEMO_1)).isEqualTo(13);
        day11.setIteration(6);
        assertThat(day11.part1(INPUT_DEMO_1)).isEqualTo(22);
    }

    @Test
    void givenAStone_whenApplyingRules2_thenIGetTheTotalOfStones() {
        Day11 day11 = (Day11) getDay();
        assertThat(day11.apply_rules2(0, 0)).isEqualTo(1);
        assertThat(day11.apply_rules2(0, 1)).isEqualTo(1);
        assertThat(day11.apply_rules2(1, 1)).isEqualTo(1);
        assertThat(day11.apply_rules2(10, 1)).isEqualTo(2);
        assertThat(day11.apply_rules2(10, 2)).isEqualTo(2);
        assertThat(day11.apply_rules2(10, 3)).isEqualTo(3);
    }

    @Test
    public void givenDemoInput1_whenRunningPart1_thenExpectedResultIsFound() {
        Day11 day11 = (Day11) getDay();
        day11.setIteration(25);
        assertThat(day11.part1(INPUT_DEMO_1)).isEqualTo(INPUT_DEMO_1_PART1_RESULT);
    }

    @Test
    public void givenDemoInput1_whenRunningPart2_thenExpectedResultIsFound() {
        Day11 day11 = (Day11) getDay();
        day11.setIteration(25);
        assertThat(day11.part2(INPUT_DEMO_1)).isEqualTo(INPUT_DEMO_1_PART2_RESULT);
    }

    @Test
    public void givenFinalInput_whenRunningPart1_thenExpectedResultIsFound() {
        Day11 day11 = (Day11) getDay();
        day11.setIteration(25);
        assertThat(day11.part1(getFinalInput())).isEqualTo(FINAL_PART1_RESULT);
    }

    @Test
    public void givenFinalInput_whenRunningPart2_thenExpectedResultIsFound() {
        Day11 day11 = (Day11) getDay();
        day11.setIteration(75);
        assertThat(day11.part2(getFinalInput())).isEqualTo(FINAL_PART2_RESULT);
    }
}
