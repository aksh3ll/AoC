package fr.akshell.aoc.y2015;

import fr.akshell.aoc.base.BaseTest;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Day9Test extends BaseTest<Integer> {

    private final static String INPUT_DEMO_1 = """
London to Dublin = 464
London to Belfast = 518
Dublin to Belfast = 141
""";
    private final static int INPUT_DEMO_1_PART1_RESULT = 605;
    private final static int INPUT_DEMO_1_PART2_RESULT = 982;
    private final static int FINAL_PART1_RESULT = 117;
    private final static int FINAL_PART2_RESULT = 909;

    public Day9Test() {
        super(2015, 9, new Day9(),
                INPUT_DEMO_1, INPUT_DEMO_1_PART1_RESULT, INPUT_DEMO_1_PART2_RESULT,
                FINAL_PART1_RESULT, FINAL_PART2_RESULT);
    }
}