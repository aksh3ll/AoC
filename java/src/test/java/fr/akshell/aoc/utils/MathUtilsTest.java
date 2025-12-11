package fr.akshell.aoc.utils;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import java.util.List;

class MathUtilsTest {


    @Test
    void permutation1Test() {
        MathUtils.Combinations<Integer> combinations = new MathUtils.Combinations<>(List.of(1, 2, 3), 1);
        java.util.List<java.util.List<Integer>> results = new java.util.ArrayList<>();
        while (combinations.hasNext()) {
            results.add(combinations.next());
        }
        assertThat(results).containsExactly(
                List.of(1),
                List.of(2),
                List.of(3)
        );
    }

    @Test
    void permutation2Test() {
        MathUtils.Combinations<Integer> combinations = new MathUtils.Combinations<>(List.of(1, 2, 3), 2);
        java.util.List<java.util.List<Integer>> results = new java.util.ArrayList<>();
        while (combinations.hasNext()) {
            results.add(combinations.next());
        }
        assertThat(results).containsExactly(
                List.of(1, 1),
                List.of(1, 2),
                List.of(1, 3),
                List.of(2, 1),
                List.of(2, 2),
                List.of(2, 3),
                List.of(3, 1),
                List.of(3, 2),
                List.of(3, 3)
        );
    }
}