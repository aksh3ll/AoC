package fr.akshell.aoc.y2015;

import fr.akshell.aoc.base.BaseTest;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

class Day2Test extends BaseTest {

    Day2 day2 = new Day2();

    public Day2Test() {
        super(2015, 2);
    }

    @Test
    public void givenDemoInput1_whenRunningPart1_thenExpectedResultIsFound() {
        assertThat(day2.part1("2x3x4")).isEqualTo(58);
    }

    @Test
    public void givenDemoInput1_whenRunningPart2_thenExpectedResultIsFound() {
        assertThat(day2.part2("2x3x4")).isEqualTo(34);
    }

    @Test
    public void givenFinalInput_whenRunningPart1_thenExpectedResultIsFound() throws IOException, InterruptedException {
        assertThat(day2.part1(getFinalInput())).isEqualTo(1598415);
    }

    @Test
    public void givenFinalInput_whenRunningPart2_thenExpectedResultIsFound() throws IOException, InterruptedException {
        assertThat(day2.part2(getFinalInput())).isEqualTo(3812909);
    }
}