package fr.akshell.aoc.y2024;

import static org.assertj.core.api.Assertions.assertThat;

import fr.akshell.aoc.base.BaseTest;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Day14Test extends BaseTest<Integer> {

    private static final String INPUT_DEMO_1 = """
p=0,4 v=3,-3
p=6,3 v=-1,-3
p=10,3 v=-1,2
p=2,0 v=2,-1
p=0,0 v=1,3
p=3,0 v=-2,-2
p=7,6 v=-1,-3
p=3,0 v=-1,-2
p=9,3 v=2,3
p=7,3 v=-1,2
p=2,4 v=2,-3
p=9,5 v=-3,-3
""";

    Day14Test() {
        super(2024, 14, new Day14(),
                INPUT_DEMO_1, 12, 0,
                0, 0);
    }

    @Test
    public void givenDemoInput1_whenRunningPart1_thenExpectedResultIsFound() {
        Day14 day14 = (Day14) getDay();
        day14.setWidth(11);
        day14.setHeight(7);
        day14.setTime(100);
        assertThat(day14.part1(INPUT_DEMO_1)).isEqualTo(12);
    }

    /**
     * This test is disabled because it makes no sense here.
     * The puzzle 2 is about finding the Christmas tree when all the points are aligned.
     * And only works for the final input.
     */
    @Disabled
    @Test
    public void givenDemoInput1_whenRunningPart2_thenExpectedResultIsFound() {
    }

    @Test
    public void givenFinalInput_whenRunningPart1_thenExpectedResultIsFound() {
        Day14 day14 = (Day14) getDay();
        day14.setWidth(101);
        day14.setHeight(103);
        day14.setTime(100);
        assertThat(day14.part1(getFinalInput())).isEqualTo(229069152);
    }

    @Test
    public void givenFinalInput_whenRunningPart2_thenExpectedResultIsFound() {
        Day14 day14 = (Day14) getDay();
        day14.setWidth(101);
        day14.setHeight(103);
        assertThat(day14.part2(getFinalInput())).isEqualTo(7383);
    }
}
