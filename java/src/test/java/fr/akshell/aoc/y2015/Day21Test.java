package fr.akshell.aoc.y2015;

import static org.assertj.core.api.Assertions.assertThat;

import fr.akshell.aoc.base.BaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Day21Test extends BaseTest<Integer> {
    private static final String INPUT_DEMO_1 = """
Hit Points: 12
Damage: 7
Armor: 2
""";
    private static final int INPUT_DEMO_1_PART1_RESULT = 8;
    private static final int INPUT_DEMO_1_PART2_RESULT = -1;
    private static final int FINAL_PART1_RESULT = 121;
    private static final int FINAL_PART2_RESULT = 201;

    public Day21Test() {
        super(2015, 21, new Day21(),
                INPUT_DEMO_1, INPUT_DEMO_1_PART1_RESULT, INPUT_DEMO_1, INPUT_DEMO_1_PART2_RESULT,
                FINAL_PART1_RESULT, FINAL_PART2_RESULT);
    }

    @Override
    @Test
    public void givenDemoInput1_whenRunningPart1_thenExpectedResultIsFound() {
        assertThat(((Day21) getDay()).part1Test(INPUT_DEMO_1)).isEqualTo(INPUT_DEMO_1_PART1_RESULT);
    }

    @Override
    @Test
    public void givenDemoInput2_whenRunningPart2_thenExpectedResultIsFound() {
        assertThat(((Day21) getDay()).part2Test(INPUT_DEMO_1)).isEqualTo(INPUT_DEMO_1_PART2_RESULT);
    }
}
