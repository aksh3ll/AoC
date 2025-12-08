package fr.akshell.aoc.y2025;

import fr.akshell.aoc.base.BaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class Day8Test extends BaseTest<Long> {

    private static final String INPUT_DEMO_1 = """
162,817,812
57,618,57
906,360,560
592,479,940
352,342,300
466,668,158
542,29,236
431,825,988
739,650,466
52,470,668
216,146,977
819,987,18
117,168,530
805,96,715
346,949,466
970,615,88
941,993,340
862,61,35
984,92,344
425,690,689
""";
    private static final long INPUT_DEMO_1_PART1_RESULT = 40L;
    private static final long INPUT_DEMO_1_PART2_RESULT = -1L;
    private static final long FINAL_PART1_RESULT = -1L;
    private static final long FINAL_PART2_RESULT = -1L;

    public Day8Test() {
        super(2025, 8, new Day8(),
                INPUT_DEMO_1, INPUT_DEMO_1_PART1_RESULT, INPUT_DEMO_1_PART2_RESULT,
                FINAL_PART1_RESULT, FINAL_PART2_RESULT);
    }

    @Override
    @Test
    public void givenDemoInput1_whenRunningPart1_thenExpectedResultIsFound() {
        Day8 dayInstance = (Day8) getDay();
        dayInstance.setLimit(10);
        assertThat(dayInstance.part1(INPUT_DEMO_1)).isEqualTo(INPUT_DEMO_1_PART1_RESULT);
    }

    @Override
    @Test
    public void givenDemoInput1_whenRunningPart2_thenExpectedResultIsFound() {
        Day8 dayInstance = (Day8) getDay();
        dayInstance.setLimit(10);
        assertThat(dayInstance.part2(INPUT_DEMO_1)).isEqualTo(INPUT_DEMO_1_PART2_RESULT);
    }

    @Override
    @Test
    public void givenFinalInput_whenRunningPart1_thenExpectedResultIsFound() {
        Day8 dayInstance = (Day8) getDay();
        dayInstance.setLimit(1000);
        assertThat(dayInstance.part1(getFinalInput())).isEqualTo(FINAL_PART1_RESULT);
    }

    @Override
    @Test
    public void givenFinalInput_whenRunningPart2_thenExpectedResultIsFound() {
        Day8 dayInstance = (Day8) getDay();
        dayInstance.setLimit(1000);
        assertThat(dayInstance.part2(getFinalInput())).isEqualTo(FINAL_PART2_RESULT);
    }
}
