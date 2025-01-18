package fr.akshell.aoc.y2024;

import static org.assertj.core.api.Assertions.assertThat;

import fr.akshell.aoc.base.BaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class Day15Test extends BaseTest {

    private static final String INPUT_DEMO_1 = """
########
#..O.O.#
##@.O..#
#...O..#
#.#.O..#
#...O..#
#......#
########

<^^>>>vv<v>>v<<
""";
    private final static int INPUT_DEMO_1_PUZZLE1_RESULT = 2028;
    private final static int INPUT_DEMO_1_PUZZLE2_RESULT = 0;

    private static final String INPUT_DEMO_2 = """
##########
#..O..O.O#
#......O.#
#.OO..O.O#
#..O@..O.#
#O#..O...#
#O..O..O.#
#.OO.O.OO#
#....O...#
##########

<vv>^<v^>v>^vv^v>v<>v^v<v<^vv<<<^><<><>>v<vvv<>^v^>^<<<><<v<<<v^vv^v>^
vvv<<^>^v^^><<>>><>^<<><^vv^^<>vvv<>><^^v>^>vv<>v<<<<v<^v>^<^^>>>^<v<v
><>vv>v^v^<>><>>>><^^>vv>v<^^^>>v^v^<^^>v^^>v^<^v>v<>>v^v^<v>v^^<^^vv<
<<v<^>>^^^^>>>v^<>vvv^><v<<<>^^^vv^<vvv>^>v<^^^^v<>^>vvvv><>>v^<<^^^^^
^><^><>>><>^^<<^^v>>><^<v>^<vv>>v>>>^v><>^v><<<<v>>v<v<v>vvv>^<><<>^><
^>><>^v<><^vvv<^^<><v<<<<<><^v<<<><<<^^<v<^^^><^>>^<v^><<<^>>^v<v^v<v^
>^>>^v>vv>^<<^v<>><<><<v<<v><>v<^vv<<<>^^v^>^^>>><<^v>>v^v><^^>>^<>vv^
<><^^>^^^<><vvvvv^v<v<<>^v<v>v<<^><<><<><<<^^<<<^<<>><<><^^^>^^<>^>v<>
^^>vv<^v^v<vv>^<><v<^v>^^^>>>^^vvv^>vvv<>>>^<^>>>>>^<<^v>^vvv<>^<><<v>
v^^>>><<^^<>>^v^<v^vv<>v^<<>^<^v^v><^<<<><<^<v><v<>vv>>v><v^<vv<>v^<<^
""";
    private final static int INPUT_DEMO_2_PUZZLE1_RESULT = 10092;
    private final static int INPUT_DEMO_2_PUZZLE2_RESULT = 9021;

    private final static String INPUT_DEMO_3 = """
#######
#...#.#
#.....#
#..OO@#
#..O..#
#.....#
#######

<vv<<^^<<^^
""";
    private final static int INPUT_DEMO_3_PUZZLE2_RESULT = 618;

    Day15 day15 = new Day15();

    public Day15Test() {
        super(2024, 15);
    }

    @Test
    public void givenDemoInput1_whenRunningPart1_thenExpectedResultIsFound() {
        assertThat(day15.part1(INPUT_DEMO_1)).isEqualTo(INPUT_DEMO_1_PUZZLE1_RESULT);
    }

    @Test
    void givenDemoInput2_whenRunningPart1_thenExpectedResultIsFound() {
        assertThat(day15.part1(INPUT_DEMO_2)).isEqualTo(INPUT_DEMO_2_PUZZLE1_RESULT);
    }

    @Test
    public void givenDemoInput1_whenRunningPart2_thenExpectedResultIsFound() {
        assertThat(day15.part2(INPUT_DEMO_2)).isEqualTo(INPUT_DEMO_2_PUZZLE2_RESULT);
    }

    @Test
    void givenDemoInput3_whenRunningPart2_thenExpectedResultIsFound() {
        assertThat(day15.part2(INPUT_DEMO_3)).isEqualTo(INPUT_DEMO_3_PUZZLE2_RESULT);
    }

    @Test
    public void givenFinalInput_whenRunningPart1_thenExpectedResultIsFound() throws IOException, InterruptedException {
        assertThat(day15.part1(getFinalInput())).isEqualTo(1406628);
    }

    @Test
    public void givenFinalInput_whenRunningPart2_thenExpectedResultIsFound() throws IOException, InterruptedException {
        assertThat(day15.part2(getFinalInput())).isEqualTo(1432781);
    }
}