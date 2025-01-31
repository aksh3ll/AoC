package fr.akshell.aoc.y2015;

import static org.assertj.core.api.Assertions.assertThat;

import fr.akshell.aoc.base.BaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Day12Test extends BaseTest<Integer> {

    private static final String INPUT_DEMO_1 = """
{"a":{"b":4},"c":-1}
""";
    private static final int INPUT_DEMO_1_PART1_RESULT = 3;
    private static final int INPUT_DEMO_1_PART2_RESULT = 3;
    private static final String INPUT_DEMO_2 = """
{"d":"red","e":[1,2,3,4],"f":5}
""";
    private static final int INPUT_DEMO_2_PART1_RESULT = 15;
    private static final int INPUT_DEMO_2_PART2_RESULT = 0;
    private static final int FINAL_PART1_RESULT = 156366;
    private static final int FINAL_PART2_RESULT = 96852;

    Day12Test() {
        super(2015, 12, new Day12(),
                INPUT_DEMO_1, INPUT_DEMO_1_PART1_RESULT, INPUT_DEMO_1_PART2_RESULT,
                FINAL_PART1_RESULT, FINAL_PART2_RESULT);
    }

    @Test
    void givenExamples_whenInvokePart1_thenReturnExpectedSize() {
        assertThat(getDay().part1("[1,2,3]")).isEqualTo(6);
        assertThat(getDay().part1("{\"a\":2,\"b\":4}")).isEqualTo(6);
        assertThat(getDay().part1("[[[3]]]")).isEqualTo(3);
        assertThat(getDay().part1("{\"a\":{\"b\":4},\"c\":-1}")).isEqualTo(3);
        assertThat(getDay().part1("{\"a\":[-1,1]}")).isZero();
        assertThat(getDay().part1("[-1,{\"a\":1}]")).isZero();
        assertThat(getDay().part1("[]")).isZero();
        assertThat(getDay().part1("{}")).isZero();
    }

    @Test
    void givenExamples_whenInvokePart2_thenReturnExpectedSize() {
        assertThat(getDay().part2("[1,2,3]")).isEqualTo(6);
        assertThat(getDay().part2("[1,{\"c\":\"red\",\"b\":2},3]")).isEqualTo(4);
        assertThat(getDay().part2("{\"d\":\"red\",\"e\":[1,2,3,4],\"f\":5}")).isZero();
        assertThat(getDay().part2("[1,\"red\",5]")).isEqualTo(6);
    }

    @Test
    void givenDemoInput2_whenRunningPart1_thenExpectedResultIsFound() {
        assertThat(getDay().part1(INPUT_DEMO_2)).isEqualTo(INPUT_DEMO_2_PART1_RESULT);
    }

    @Test
    void givenDemoInput2_whenRunningPart2_thenExpectedResultIsFound() {
        assertThat(getDay().part2(INPUT_DEMO_2)).isEqualTo(INPUT_DEMO_2_PART2_RESULT);
    }
}