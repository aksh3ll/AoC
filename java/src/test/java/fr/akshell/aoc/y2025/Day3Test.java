package fr.akshell.aoc.y2025;

import static org.assertj.core.api.Assertions.assertThat;

import fr.akshell.aoc.base.BaseTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

class Day3Test extends BaseTest<Long> {

    private static final String INPUT_DEMO_1 = """
987654321111111
811111111111119
234234234234278
818181911112111
""";
    private static final long INPUT_DEMO_1_PART1_RESULT = 357L;
    private static final long INPUT_DEMO_1_PART2_RESULT = 3121910778619L;
    private static final long FINAL_PART1_RESULT = 16993L;
    private static final long FINAL_PART2_RESULT = -1L;

    public Day3Test() {
        super(2025, 3, new Day3(),
                INPUT_DEMO_1, INPUT_DEMO_1_PART1_RESULT, INPUT_DEMO_1_PART2_RESULT,
                FINAL_PART1_RESULT, FINAL_PART2_RESULT);
    }

    @ParameterizedTest
    @CsvSource({
            "987654321111111,987654321111", "811111111111119,811111111119",
            "234234234234278,434234234278", "818181911112111,888911112111"})
    void checkAlgo2(String input, Long expected) {
        Long result = getDay().part2(input);
        assertThat(result).isEqualTo(expected);
    }
}
