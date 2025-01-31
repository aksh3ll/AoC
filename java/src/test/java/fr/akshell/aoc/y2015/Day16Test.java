package fr.akshell.aoc.y2015;

import static org.assertj.core.api.Assertions.assertThat;

import fr.akshell.aoc.base.BaseTest;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Day16Test extends BaseTest<Integer> {

    private static final String SAMPLE = """
children: 3
cats: 7
samoyeds: 2
pomeranians: 3
akitas: 0
vizslas: 0
goldfish: 5
trees: 3
cars: 2
perfumes: 1
""";

    private static final int FINAL_PART1_RESULT = 103;
    private static final int FINAL_PART2_RESULT = 405;

    public Day16Test() {
        super(2015, 16, new Day16(),
                "", 0, 0,
                FINAL_PART1_RESULT, FINAL_PART2_RESULT);
    }

    @Disabled
    @Test
    public void givenDemoInput1_whenRunningPart1_thenExpectedResultIsFound() {}

    @Disabled
    @Test
    public void givenDemoInput1_whenRunningPart2_thenExpectedResultIsFound() {}

    @Test
    public void givenFinalInput_whenRunningPart1_thenExpectedResultIsFound() {
        Day16 day16 = (Day16) getDay();
        day16.setSamples(SAMPLE);
        assertThat(day16.part1(getFinalInput())).isEqualTo(FINAL_PART1_RESULT);
    }

    @Test
    public void givenFinalInput_whenRunningPart2_thenExpectedResultIsFound() {
        Day16 day16 = (Day16) getDay();
        day16.setSamples(SAMPLE);
        assertThat(day16.part2(getFinalInput())).isEqualTo(FINAL_PART2_RESULT);
    }
}
