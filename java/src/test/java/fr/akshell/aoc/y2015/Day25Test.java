package fr.akshell.aoc.y2015;

import fr.akshell.aoc.base.BaseTest;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Day25Test extends BaseTest<Long> {

    private static final String INPUT_DEMO_1 = """
To continue, please consult the code grid in the manual.  Enter the code at row 6, column 2.
""";
    private static final long INPUT_DEMO_1_PART1_RESULT = 6796745L;
    private static final long INPUT_DEMO_1_PART2_RESULT = 0;

    private static final long FINAL_PART1_RESULT = 8997277L;
    private static final long FINAL_PART2_RESULT = 0;

    public Day25Test() {
        super(2015, 25, new Day25(),
                INPUT_DEMO_1, INPUT_DEMO_1_PART1_RESULT, INPUT_DEMO_1, INPUT_DEMO_1_PART2_RESULT,
                FINAL_PART1_RESULT, FINAL_PART2_RESULT);
    }
}
