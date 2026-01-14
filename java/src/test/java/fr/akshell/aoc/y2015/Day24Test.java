package fr.akshell.aoc.y2015;

import fr.akshell.aoc.base.BaseTest;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Day24Test extends BaseTest<Integer> {

    private static final String INPUT_DEMO_1 = """
1
2
3
4
5
7
8
9
10
11
""";
    private static final int INPUT_DEMO_1_PART1_RESULT = 99;
    private static final int INPUT_DEMO_1_PART2_RESULT = 0;
    private static final int FINAL_PART1_RESULT = 0;
    private static final int FINAL_PART2_RESULT = 0;

    public Day24Test() {
        super(2015, 24, new Day24(),
                INPUT_DEMO_1, INPUT_DEMO_1_PART1_RESULT, INPUT_DEMO_1, INPUT_DEMO_1_PART2_RESULT,
                FINAL_PART1_RESULT, FINAL_PART2_RESULT);
    }
}
