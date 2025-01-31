package fr.akshell.aoc.y2015;

import fr.akshell.aoc.base.BaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class Day17Test extends BaseTest<Integer> {

    private static final String INPUT_DEMO_1 = """
20
15
10
5
5
""";
    private static final int INPUT_DEMO_1_PART1_RESULT = 4;
    private static final int INPUT_DEMO_1_PART2_RESULT = 3;
    private static final int FINAL_PART1_RESULT = 4372;
    private static final int FINAL_PART2_RESULT = 4;

    public Day17Test() {
        super(2015, 17, new Day17(),
                INPUT_DEMO_1, INPUT_DEMO_1_PART1_RESULT, INPUT_DEMO_1_PART2_RESULT,
                FINAL_PART1_RESULT, FINAL_PART2_RESULT);
    }

    @Test
    @Override
    public void givenDemoInput1_whenRunningPart1_thenExpectedResultIsFound() {
        Day17 day17 = (Day17) getDay();
        day17.setContainerSize(25);
        assertThat(day17.part1(INPUT_DEMO_1)).isEqualTo(INPUT_DEMO_1_PART1_RESULT);
    }

    @Test
    @Override
    public void givenDemoInput1_whenRunningPart2_thenExpectedResultIsFound() {
        Day17 day17 = (Day17) getDay();
        day17.setContainerSize(25);
        assertThat(day17.part2(INPUT_DEMO_1)).isEqualTo(INPUT_DEMO_1_PART2_RESULT);
    }

    @Test
    @Override
    public void givenFinalInput_whenRunningPart1_thenExpectedResultIsFound() {
        Day17 day17 = (Day17) getDay();
        day17.setContainerSize(150);
        assertThat(day17.part1(getFinalInput())).isEqualTo(FINAL_PART1_RESULT);
    }

    @Test
    @Override
    public void givenFinalInput_whenRunningPart2_thenExpectedResultIsFound() {
        Day17 day17 = (Day17) getDay();
        day17.setContainerSize(150);
        assertThat(day17.part2(getFinalInput())).isEqualTo(FINAL_PART2_RESULT);
    }
}
