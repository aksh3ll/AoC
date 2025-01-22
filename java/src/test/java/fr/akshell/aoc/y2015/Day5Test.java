package fr.akshell.aoc.y2015;

import static org.assertj.core.api.Assertions.assertThat;

import fr.akshell.aoc.base.BaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Day5Test extends BaseTest<Integer> {

    private final static String INPUT_DEMO_1 = """
ugknbfddgicrmopn
aaa
jchzalrnumimnmhp
haegwjzuvuyypxyu
dvszwmarrgswjxmb
""";
    private final static int INPUT_DEMO_1_PART1_RESULT = 2;
    private final static int INPUT_DEMO_1_PART2_RESULT = 0;

   private final static String INPUT_DEMO_2 = """
qjhvhtzxzqqjkmpb
xxyxx
uurcxstgmygtbstg
ieodomkazucvgmuy
""";
    private final static int INPUT_DEMO_2_PART1_RESULT = 0;
    private final static int INPUT_DEMO_2_PART2_RESULT = 2;

    private final static int FINAL_PART1_RESULT = 236;
    private final static int FINAL_PART2_RESULT = 51;

    public Day5Test() {
        super(2015, 5, new Day5(),
                INPUT_DEMO_1, INPUT_DEMO_1_PART1_RESULT, INPUT_DEMO_1_PART2_RESULT,
                FINAL_PART1_RESULT, FINAL_PART2_RESULT);
    }

    @Test
    public void givenDemoInput2_whenRunningPart1_thenExpectedResultIsFound() {
        assertThat(getDay().part1(INPUT_DEMO_2)).isEqualTo(INPUT_DEMO_2_PART1_RESULT);
    }

    @Test
    public void givenDemoInput2_whenRunningPart2_thenExpectedResultIsFound() {
        assertThat(getDay().part2(INPUT_DEMO_2)).isEqualTo(INPUT_DEMO_2_PART2_RESULT);
    }
}
