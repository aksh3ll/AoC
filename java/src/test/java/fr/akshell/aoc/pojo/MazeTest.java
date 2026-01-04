package fr.akshell.aoc.pojo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MazeTest {
    public static final String INPUT_DEMO_1 = """
#####
#...#
#.#.#
#..@#
#####
""";

    private static final String INPUT_DEMO_2 = """
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

    Maze maze = Maze.of(INPUT_DEMO_1);
    Maze maze2 = Maze.of(INPUT_DEMO_2);

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
        Maze m2 = Maze.of(1, 1);
        m2.set(0, 0, '#');
        // Not null & totally the same
        assertThat(m1).isNotNull().isEqualTo(m2);
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

    @Test
    void givenMaze_whenToString_thenExpectedResultIsFound() {
        Maze m1 = Maze.of(3, 2);
        m1.set(0, 0, '#');
        m1.set(1, 0, '.');
        m1.set(2, 0, '@');
        m1.set(0, 1, '.');
        m1.set(1, 1, '#');
        m1.set(2, 1, '.');
        String expected = """
                #.@
                .#.""";
        assertThat(m1).hasToString(expected);
    }

    @Test
    void givenInput_whenSearchingGuard_thenExpectedResultIsFound() {
        Vector2D guardPos = maze2.find('^');
        assertThat(guardPos)
                .isNotNull()
                .extracting(Vector2D::x, Vector2D::y)
                .containsExactly(4, 6);
    }

    @ParameterizedTest
    @NullAndEmptySource
    void givenNullInput_whenConvertToMaze_thenExpectedIllegalArgumentException(String input) {
        assertThatThrownBy(() -> Maze.of(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Invalid input");
    }
}
