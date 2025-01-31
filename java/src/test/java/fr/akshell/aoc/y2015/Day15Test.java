package fr.akshell.aoc.y2015;

import fr.akshell.aoc.base.BaseTest;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class Day15Test extends BaseTest<Integer> {

    private static final String INPUT_DEMO_1 = """
Butterscotch: capacity -1, durability -2, flavor 6, texture 3, calories 8
Cinnamon: capacity 2, durability 3, flavor -2, texture -1, calories 3
""";
    private static final int INPUT_DEMO_1_PART1_RESULT = 62842880;
    private static final int INPUT_DEMO_1_PART2_RESULT = Integer.MAX_VALUE;
    private static final int FINAL_PART1_RESULT = Integer.MAX_VALUE;
    private static final int FINAL_PART2_RESULT = Integer.MAX_VALUE;

    public Day15Test() {
        super(2015, 15, new Day15(),
                INPUT_DEMO_1, INPUT_DEMO_1_PART1_RESULT, INPUT_DEMO_1_PART2_RESULT,
                FINAL_PART1_RESULT, FINAL_PART2_RESULT);
    }

    @Disabled("This test is disabled because the part 1 is not yet implemented")
    @Test
    @Override
    public void givenDemoInput1_whenRunningPart1_thenExpectedResultIsFound() {
        assertThat(DUMMY).isTrue();
    }

    @Disabled("This test is disabled because the part 2 is not yet implemented")
    @Test
    @Override
    public void givenDemoInput1_whenRunningPart2_thenExpectedResultIsFound() {
        assertThat(DUMMY).isTrue();}

    @Disabled("This test is disabled because the part 1 is not yet implemented")
    @Test
    @Override
    public void givenFinalInput_whenRunningPart1_thenExpectedResultIsFound() {
        assertThat(DUMMY).isTrue();}

    @Disabled("This test is disabled because the part 2 is not yet implemented")
    @Test
    @Override
    public void givenFinalInput_whenRunningPart2_thenExpectedResultIsFound() {
        assertThat(DUMMY).isTrue();}
}

