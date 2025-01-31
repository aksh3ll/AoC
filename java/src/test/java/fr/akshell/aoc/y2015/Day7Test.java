package fr.akshell.aoc.y2015;

import fr.akshell.aoc.base.BaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class Day7Test extends BaseTest<Integer> {

    private static final String INPUT_DEMO_1 = """
123 -> x
456 -> y
x AND y -> d
x OR y -> e
x LSHIFT 2 -> f
y RSHIFT 2 -> g
NOT x -> h
NOT y -> i
""";
    private static final int INPUT_DEMO_1_PART1_RESULT = 0;
    private static final int INPUT_DEMO_1_PART2_RESULT = 0;
    private static final int FINAL_PART1_RESULT = 3176;
    private static final int FINAL_PART2_RESULT = 14710;

    public Day7Test() {
        super(2015, 7, new Day7(),
                INPUT_DEMO_1, INPUT_DEMO_1_PART1_RESULT, INPUT_DEMO_1_PART2_RESULT,
                FINAL_PART1_RESULT, FINAL_PART2_RESULT);
    }
}
