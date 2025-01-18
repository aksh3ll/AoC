package fr.akshell.aoc.y2015;

import fr.akshell.aoc.base.BaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class Day1Test extends BaseTest {

    Day1 day1 = new Day1();

    Day1Test() {
        super(2015, 1);
    }

    @Test
    public void givenDemoInput1_whenRunningPart1_thenExpectedResultIsFound() {
        assertThat(day1.part1("(())")).isEqualTo(0);
        assertThat(day1.part1("()()")).isEqualTo(0);
        assertThat(day1.part1("(((")).isEqualTo(3);
        assertThat(day1.part1("(()(()(")).isEqualTo(3);
        assertThat(day1.part1("))(((((")).isEqualTo(3);
        assertThat(day1.part1("())")).isEqualTo(-1);
        assertThat(day1.part1("))(")).isEqualTo(-1);
        assertThat(day1.part1(")))")).isEqualTo(-3);
        assertThat(day1.part1(")())())")).isEqualTo(-3);
    }

    @Test
    public void givenDemoInput1_whenRunningPart2_thenExpectedResultIsFound() {
        assertThat(day1.part2(")")).isEqualTo(1);
        assertThat(day1.part2("()())")).isEqualTo(5);
    }

    @Test
    public void givenFinalInput_whenRunningPart1_thenExpectedResultIsFound() throws IOException, InterruptedException {
        assertThat(day1.part1(getFinalInput())).isEqualTo(232);
    }

    @Test
    public void givenFinalInput_whenRunningPart2_thenExpectedResultIsFound() throws IOException, InterruptedException {
        assertThat(day1.part2(getFinalInput())).isEqualTo(1783);
    }
}