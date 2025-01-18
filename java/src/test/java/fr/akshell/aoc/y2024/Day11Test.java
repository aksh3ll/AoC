package fr.akshell.aoc.y2024;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.util.List;

import fr.akshell.aoc.base.BaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Day11Test extends BaseTest {
    private static final String INPUT_DEMO_1 = "125 17";

    Day11 day11 = new Day11();

    Day11Test() {
        super(2024, 11);
    }

    @Test
    void givenNumber_whenCountingDigits_thenExpectedResultIsRight() {
        assertThat(Day11.countDigits(123456789)).isEqualTo(9);
        assertThat(Day11.countDigits(9)).isEqualTo(1);
        assertThat(Day11.countDigits(0)).isEqualTo(1);
    }

    @Test
    void givenAStone_whenApplyingRules_thenIGetAListOfStones() {
        assertThat(day11.apply_rules(0)).isEqualTo(List.of(1L));
        assertThat(day11.apply_rules(1)).isEqualTo(List.of(2024L));
        assertThat(day11.apply_rules(10)).isEqualTo(List.of(1L, 0L));
        assertThat(day11.apply_rules(11)).isEqualTo(List.of(1L, 1L));
        assertThat(day11.apply_rules(3)).isEqualTo(List.of(3 * 2024L));
    }

    @Test
    void givenABasicListOfStones_whenIExecutePart1_thenIGetTheTotalOfStones() {
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
        assertThat(day11.apply_rules2(0, 0)).isEqualTo(1);
        assertThat(day11.apply_rules2(0, 1)).isEqualTo(1);
        assertThat(day11.apply_rules2(1, 1)).isEqualTo(1);
        assertThat(day11.apply_rules2(10, 1)).isEqualTo(2);
        assertThat(day11.apply_rules2(10, 2)).isEqualTo(2);
        assertThat(day11.apply_rules2(10, 3)).isEqualTo(3);
    }

    @Test
    public void givenDemoInput1_whenRunningPart1_thenExpectedResultIsFound() {
        day11.setIteration(25);
        assertThat(day11.part1(INPUT_DEMO_1)).isEqualTo(55312);
    }

    @Test
    public void givenDemoInput1_whenRunningPart2_thenExpectedResultIsFound() {
        day11.setIteration(25);
        assertThat(day11.part2(INPUT_DEMO_1)).isEqualTo(55312);
    }

    @Test
    public void givenFinalInput_whenRunningPart1_thenExpectedResultIsFound() throws IOException, InterruptedException {
        day11.setIteration(25);
        assertThat(day11.part1(getFinalInput())).isEqualTo(193899);
    }

    @Test
    public void givenFinalInput_whenRunningPart2_thenExpectedResultIsFound() throws IOException, InterruptedException {
        day11.setIteration(75);
        assertThat(day11.part2(getFinalInput())).isEqualTo(229682160383225L);
    }
}