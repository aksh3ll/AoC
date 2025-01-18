package fr.akshell.aoc.pojo;

import fr.akshell.aoc.utils.MazeUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MazeTest {
    public static final String INPUT_DEMO_1 = """
#####
#...#
#.#.#
#..@#
#####
""";
    Maze maze = MazeUtils.convertInputToMaze(INPUT_DEMO_1);

    @Test
    public void givenMaze_whenCheckingValid_thenExpectedResultIsFound() {
        assertThat(maze.isValid(new Vector2D(0, 0))).isTrue();
        assertThat(maze.isValid(new Vector2D(1, 1))).isTrue();
        assertThat(maze.isValid(new Vector2D(2, 2))).isTrue();
        assertThat(maze.isValid(new Vector2D(3, 3))).isTrue();
        assertThat(maze.isValid(new Vector2D(4, 4))).isTrue();
        assertThat(maze.isValid(new Vector2D(-1, 0))).isFalse();
        assertThat(maze.isValid(new Vector2D(0, -1))).isFalse();
        assertThat(maze.isValid(new Vector2D(5, 0))).isFalse();
        assertThat(maze.isValid(new Vector2D(0, 5))).isFalse();
    }

    @Test
    public void givenMaze_whenCheckingWall_thenExpectedResultIsFound() {
        assertThat(maze.find('@'))
                .isNotNull()
                .extracting(Vector2D::x, Vector2D::y).containsExactly(3, 3);
        assertThat(maze.find('^'))
                .isNull();
    }
}
