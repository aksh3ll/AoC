package fr.akshell.aoc.y2015;

import fr.akshell.aoc.base.BaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class Day3Test extends BaseTest {

    Day3 day3 = new Day3();

    public Day3Test() {
        super(2015, 3);
    }

    @Test
    public void givenDemoInput1_whenRunningPart1_thenExpectedResultIsFound() {
        assertThat(day3.part1(">")).isEqualTo(2);
        assertThat(day3.part1("^>v<")).isEqualTo(4);
        assertThat(day3.part1("^v^v^v^v^v")).isEqualTo(2);
    }

    @Test
    public void givenDemoInput1_whenRunningPart2_thenExpectedResultIsFound() {
        assertThat(day3.part2("^v")).isEqualTo(3);
        assertThat(day3.part2("^>v<")).isEqualTo(3);
        assertThat(day3.part2("^v^v^v^v^v")).isEqualTo(11);
    }

    @Test
    public void givenFinalInput_whenRunningPart1_thenExpectedResultIsFound() throws IOException, InterruptedException {
        assertThat(day3.part1(getFinalInput())).isEqualTo(2592);
    }

    @Test
    public void givenFinalInput_whenRunningPart2_thenExpectedResultIsFound() throws IOException, InterruptedException {
        assertThat(day3.part2(getFinalInput())).isEqualTo(2360);
    }
}