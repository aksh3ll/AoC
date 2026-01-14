package fr.akshell.aoc.y2015;

import fr.akshell.aoc.base.BaseTest;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Day15Test extends BaseTest<Long> {

    private static final String INPUT_DEMO_1 = """
Butterscotch: capacity -1, durability -2, flavor 6, texture 3, calories 8
Cinnamon: capacity 2, durability 3, flavor -2, texture -1, calories 3
""";
    private static final long INPUT_DEMO_1_PART1_RESULT = 62842880L;
    private static final long INPUT_DEMO_1_PART2_RESULT = 57600000L;
    private static final long FINAL_PART1_RESULT = 21367368L;
    private static final long FINAL_PART2_RESULT = 1766400L;

    public Day15Test() {
        super(2015, 15, new Day15(),
                INPUT_DEMO_1, INPUT_DEMO_1_PART1_RESULT, INPUT_DEMO_1, INPUT_DEMO_1_PART2_RESULT,
                FINAL_PART1_RESULT, FINAL_PART2_RESULT);
    }
}

