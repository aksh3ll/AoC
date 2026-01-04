package fr.akshell.aoc.utils;

import static fr.akshell.aoc.utils.MiscUtils.permute;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

class MathUtilsTest {
    public static final List<Integer> ONE_TWO_THREE = List.of(1, 2, 3);
    public static final List<Integer> EMPTY_LIST = List.of();

    @Test
    void givenNullList_whenCombinations_thenThrowException() {
        assertThatThrownBy(() -> new MathUtils.Combinations<Integer>(null, 2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("elements must not be null or empty");
    }

    @Test
    void givenEmptyList_whenCombinations_thenThrowException() {
        assertThatThrownBy(() -> new MathUtils.Combinations<>(EMPTY_LIST, 2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("elements must not be null or empty");
    }

    @Test
    void givenList_whenCombinationsOf0_thenThrowException() {
        assertThatThrownBy(() -> new MathUtils.Combinations<>(ONE_TWO_THREE, 0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("k must be greater than 0");
    }

    @Test
    void givenListOf1_whenCombinationsOf1_thenReturnIterator() {
        MathUtils.Combinations<Integer> combinations = new MathUtils.Combinations<>(List.of(1), 1);
        assertThat(combinations.next()).isEqualTo(List.of(1));
        assertThatThrownBy(combinations::next)
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage(null);
    }

    @Test
    void givenList_whenCombinationsOf1_thenReturnIterator() {
        MathUtils.Combinations<Integer> combinations = new MathUtils.Combinations<>(ONE_TWO_THREE, 1);
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
    void givenList_whenCombinationsOf2_thenReturnIterator() {
        MathUtils.Combinations<Integer> combinations = new MathUtils.Combinations<>(ONE_TWO_THREE, 2);
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

    @Test
    void givenListString_whenPermute_thenAllPermutationsReturned() {
        List<String> nums = List.of("Paris", "London", "Berlin");
        List<List<String>> permutations = permute(nums);
        assertThat(permutations).hasSize(6);
    }
}