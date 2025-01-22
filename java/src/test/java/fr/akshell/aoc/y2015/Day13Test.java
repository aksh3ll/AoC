package fr.akshell.aoc.y2015;

import fr.akshell.aoc.base.BaseTest;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Day13Test extends BaseTest<Integer> {

    private final static String INPUT_DEMO_1 = """
Alice would gain 54 happiness units by sitting next to Bob.
Alice would lose 79 happiness units by sitting next to Carol.
Alice would lose 2 happiness units by sitting next to David.
Bob would gain 83 happiness units by sitting next to Alice.
Bob would lose 7 happiness units by sitting next to Carol.
Bob would lose 63 happiness units by sitting next to David.
Carol would lose 62 happiness units by sitting next to Alice.
Carol would gain 60 happiness units by sitting next to Bob.
Carol would gain 55 happiness units by sitting next to David.
David would gain 46 happiness units by sitting next to Alice.
David would lose 7 happiness units by sitting next to Bob.
David would gain 41 happiness units by sitting next to Carol.
""";
    private final static int INPUT_DEMO_1_PART1_RESULT = 330;
    private final static int INPUT_DEMO_1_PART2_RESULT = 286;
    private final static int FINAL_PART1_RESULT = 664;
    private final static int FINAL_PART2_RESULT = 640;

    public Day13Test() {
        super(2015, 13, new Day13(),
                INPUT_DEMO_1, INPUT_DEMO_1_PART1_RESULT, INPUT_DEMO_1_PART2_RESULT,
                FINAL_PART1_RESULT, FINAL_PART2_RESULT);
    }
}
