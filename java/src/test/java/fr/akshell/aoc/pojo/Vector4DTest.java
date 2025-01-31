package fr.akshell.aoc.pojo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

@SpringBootTest
class Vector4DTest {

    @Test
    public void givenPosition_whenTurnLeft_thenExpectedPositionIsFound() {
        // Facing East
        Vector4D position = new Vector4D(0, 0, 1, 0);
        // From East to North
        position = position.turnLeft();
        assertThat(position)
                .isNotNull()
                .extracting(Vector4D::x, Vector4D::y, Vector4D::dX, Vector4D::dY)
                .containsExactly(0, 0, 0, -1);
        // From North to West
        position = position.turnLeft();
        assertThat(position)
                .isNotNull()
                .extracting(Vector4D::x, Vector4D::y, Vector4D::dX, Vector4D::dY)
                .containsExactly(0, 0, -1, 0);
        // From West to South
        position = position.turnLeft();
        assertThat(position)
                .isNotNull()
                .extracting(Vector4D::x, Vector4D::y, Vector4D::dX, Vector4D::dY)
                .containsExactly(0, 0, 0, 1);
        // From South to East
        position = position.turnLeft();
        assertThat(position)
                .isNotNull()
                .extracting(Vector4D::x, Vector4D::y, Vector4D::dX, Vector4D::dY)
                .containsExactly(0, 0, 1, 0);
    }

    @Test
    public void givenPosition_whenTurnRight_thenExpectedPositionIsFound() {
        // Facing East
        Vector4D position = new Vector4D(0, 0, 1, 0);
        // From East to South
        position = position.turnRight();
        assertThat(position)
                .isNotNull()
                .extracting(Vector4D::x, Vector4D::y, Vector4D::dX, Vector4D::dY)
                .containsExactly(0, 0, 0, 1);
        // From South to West
        position = position.turnRight();
        assertThat(position)
                .isNotNull()
                .extracting(Vector4D::x, Vector4D::y, Vector4D::dX, Vector4D::dY)
                .containsExactly(0, 0, -1, 0);
        // From West to North
        position = position.turnRight();
        assertThat(position)
                .isNotNull()
                .extracting(Vector4D::x, Vector4D::y, Vector4D::dX, Vector4D::dY)
                .containsExactly(0, 0, 0, -1);
        // From North to East
        position = position.turnRight();
        assertThat(position)
                .isNotNull()
                .extracting(Vector4D::x, Vector4D::y, Vector4D::dX, Vector4D::dY)
                .containsExactly(0, 0, 1, 0);
    }

    @Test
    public void givenPosition_whenTurnAround_thenExpectedPositionIsFound() {
        // Facing East
        Vector4D position = new Vector4D(0, 0, 1, 0);
        // From East to West
        position = position.turnAround();
        assertThat(position)
                .isNotNull()
                .extracting(Vector4D::x, Vector4D::y, Vector4D::dX, Vector4D::dY)
                .containsExactly(0, 0, -1, 0);
        // From West to East
        position = position.turnAround();
        assertThat(position)
                .isNotNull()
                .extracting(Vector4D::x, Vector4D::y, Vector4D::dX, Vector4D::dY)
                .containsExactly(0, 0, 1, 0);

        // Facing North
        position = new Vector4D(0, 0, 0, -1);
        // From North to South
        position = position.turnAround();
        assertThat(position)
                .isNotNull()
                .extracting(Vector4D::x, Vector4D::y, Vector4D::dX, Vector4D::dY)
                .containsExactly(0, 0, 0, 1);
        // From South to North
        position = position.turnAround();
        assertThat(position)
                .isNotNull()
                .extracting(Vector4D::x, Vector4D::y, Vector4D::dX, Vector4D::dY)
                .containsExactly(0, 0, 0, -1);
    }

    @Test
    void givenVector_whenRequestId_thenExpectedIdIsFound() {
        Vector4D vector = new Vector4D(6, 3, -1, 1);
        assertThat(vector.id()).isEqualTo("(6, 3, -1, 1)");
    }

    @Test
    void givenGuard_whenSearchInSet_thenExpectedResultIsFound() {
        Vector4D guard = new Vector4D(4, 6, 0, -1);
        Set<Vector4D> moves = Set.of(guard);
        assertThat(moves.contains(new Vector4D(4, 6, 0, -1))).isTrue();
    }
}
