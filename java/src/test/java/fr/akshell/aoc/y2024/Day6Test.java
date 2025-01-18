package fr.akshell.aoc.y2024;

import static org.assertj.core.api.Assertions.assertThat;

import fr.akshell.aoc.base.BaseTest;
import fr.akshell.aoc.pojo.Maze;
import fr.akshell.aoc.pojo.Vector2D;
import fr.akshell.aoc.pojo.Vector4D;
import fr.akshell.aoc.utils.MazeUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.Set;

@SpringBootTest
class Day6Test extends BaseTest {

    private final static String INPUT_DEMO_1 = """
....#.....
.........#
..........
..#.......
.......#..
..........
.#..^.....
........#.
#.........
......#...
""";
    private final static int INPUT_DEMO_1_PUZZLE1_RESULT = 41;
    private final static int INPUT_DEMO_1_PUZZLE2_RESULT = 6;
    Day6 day6 = new Day6();

    public Day6Test() {
        super(2024, 6);
    }

    @Test
    void givenInput_whenSearchingGuard_thenExpectedResultIsFound() {
        Maze maze = MazeUtils.convertInputToMaze(INPUT_DEMO_1);
        Vector2D guardPos = maze.find('^');
        assertThat(guardPos)
                .isNotNull()
                .extracting(Vector2D::x, Vector2D::y)
                .containsExactly(4, 6);
    }

    @Test
    void givenGuard_whenSearchInSet_thenExpectedResultIsFound() {
        Vector4D guard = new Vector4D(4, 6, 0, -1);
        Set<Vector4D> moves = Set.of(guard);
        assertThat(moves.contains(new Vector4D(4, 6, 0, -1))).isTrue();
    }

    @Test
    public void givenDemoInput1_whenRunningPart1_thenExpectedResultIsFound() {
        assertThat(day6.part1(INPUT_DEMO_1)).isEqualTo(INPUT_DEMO_1_PUZZLE1_RESULT);
    }

    @Test
    public void givenDemoInput1_whenRunningPart2_thenExpectedResultIsFound() {
        assertThat(day6.part2(INPUT_DEMO_1)).isEqualTo(INPUT_DEMO_1_PUZZLE2_RESULT);
    }

    @Test
    public void givenFinalInput_whenRunningPart1_thenExpectedResultIsFound() throws IOException, InterruptedException {
        assertThat(day6.part1(getFinalInput())).isEqualTo(5444);
    }

    @Test
    public void givenFinalInput_whenRunningPart2_thenExpectedResultIsFound() throws IOException, InterruptedException {
        assertThat(day6.part2(getFinalInput())).isEqualTo(1946);
    }
}
