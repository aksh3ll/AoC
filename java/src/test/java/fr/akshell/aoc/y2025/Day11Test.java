package fr.akshell.aoc.y2025;

import fr.akshell.aoc.base.BaseTest;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class Day11Test extends BaseTest<Long> {

    private static final String INPUT_DEMO_1 = """
aaa: you hhh
you: bbb ccc
bbb: ddd eee
ccc: ddd eee fff
ddd: ggg
eee: out
fff: out
ggg: out
hhh: ccc fff iii
iii: out
""";

    private static final String INPUT_DEMO_2 = """
svr: aaa bbb
aaa: fft
fft: ccc
bbb: tty
tty: ccc
ccc: ddd eee
ddd: hub
hub: fff
eee: dac
dac: fff
fff: ggg hhh
ggg: out
hhh: out
""";
    private static final long INPUT_DEMO_1_PART1_RESULT = 5L;
    private static final long INPUT_DEMO_2_PART2_RESULT = 2L;
    private static final long FINAL_PART1_RESULT = 658L;
    private static final long FINAL_PART2_RESULT = -1L;

    public Day11Test() {
        super(2025, 11, new Day11(),
                INPUT_DEMO_1, INPUT_DEMO_1_PART1_RESULT, INPUT_DEMO_2, INPUT_DEMO_2_PART2_RESULT,
                FINAL_PART1_RESULT, FINAL_PART2_RESULT);
    }

    //@Disabled("Execution takes too long")
    @Override
    @Test
    public void givenFinalInput_whenRunningPart2_thenExpectedResultIsFound() {
        assertThat(getDay().part2(getFinalInput())).isEqualTo(FINAL_PART2_RESULT);
    }
}
