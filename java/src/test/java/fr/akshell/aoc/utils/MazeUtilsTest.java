package fr.akshell.aoc.utils;

import fr.akshell.aoc.pojo.Maze;
import fr.akshell.aoc.pojo.Vector2D;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MazeUtilsTest {

    private static final String INPUT_DEMO_1 = """
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

    @Test
    void givenInput_whenSearchingGuard_thenExpectedResultIsFound() {
        Maze maze = MazeUtils.convertInputToMaze(INPUT_DEMO_1);
        Vector2D guardPos = maze.find('^');
        assertThat(guardPos)
                .isNotNull()
                .extracting(Vector2D::x, Vector2D::y)
                .containsExactly(4, 6);
    }

}