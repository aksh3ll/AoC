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
    void givenMaze_whenCheckingValid_thenExpectedResultIsFound() {
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
    void givenMaze_whenCheckingWall_thenExpectedResultIsFound() {
        assertThat(maze.find('@'))
                .isNotNull()
                .extracting(Vector2D::x, Vector2D::y).containsExactly(3, 3);
        assertThat(maze.find('^'))
                .isNull();
    }

    @Test
    void givenTwoMazes_whenTestEquals_thenExpectedResultIsFound() {
        Maze m1 = Maze.of(1, 1);
        m1.set(0, 0, '#');
        // Totally the same
        Maze m2 = Maze.of(1, 1);
        m2.set(0, 0, '#');
        assertThat(m1).isEqualTo(m2);
        // Null
        Object m3 = null;
        assertThat(m1).isNotEqualTo(m3);
        // Different type
        Object m4 = "string";
        assertThat(m1).isNotEqualTo(m4);
        // Different height
        Maze m5 = Maze.of(1, 2);
        m2.set(0, 0, '#');
        assertThat(m1).isNotEqualTo(m5);
        // Different width
        Maze m6 = Maze.of(2, 1);
        m2.set(0, 0, '#');
        assertThat(m1).isNotEqualTo(m6);
        // Different content
        Maze m7 = Maze.of(1, 1);
        m2.set(0, 0, '.');
        assertThat(m1).isNotEqualTo(m7);
    }

    @Test
    void givenMaze_whenComputeHashCode_thenExpectedResultIsFound() {
        Maze m1 = Maze.of(1, 1);
        m1.set(0, 0, '#');
        assertThat(m1.hashCode()).isEqualTo(123040);
    }
}
