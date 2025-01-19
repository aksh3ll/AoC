package fr.akshell.aoc.utils;

import static fr.akshell.aoc.utils.MiscUtils.permute;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

class MiscUtilsTest {

    @Test
    void givenString_whenIsNumber_thenTypeChecked() {
        assertThat(MiscUtils.isNumber("123")).isTrue();
        assertThat(MiscUtils.isNumber("123.0")).isFalse();
        assertThat(MiscUtils.isNumber("abc")).isFalse();
        assertThat(MiscUtils.isNumber("1.23E2")).isFalse();
        assertThat(MiscUtils.isNumber("")).isFalse();
    }

    @Test
    void givenComplexObject_whenDeepCopy_thenTotallyNewObjectReturned() {
    }

    @Test
    void givenListInteger_whenPermute_thenAllPermutationsReturned() {
        List<Integer> nums = List.of(1, 2, 3);
        List<List<Integer>> permutations = permute(nums);
        assertThat(permutations.size()).isEqualTo(6);
    }

    @Test
    void givenListString_whenPermute_thenAllPermutationsReturned() {
        List<String> nums = List.of("Paris", "London", "Berlin");
        List<List<String>> permutations = permute(nums);
        assertThat(permutations.size()).isEqualTo(6);
    }

    @Test
    void givenString_whenDecodeHex_thenDecodedStringReturned() {
        assertThat(MiscUtils.decodeHex("a\\x20b")).isEqualTo("a b");
        assertThat(MiscUtils.decodeHex("a\\x20b\\x20c")).isEqualTo("a b c");
        assertThat(MiscUtils.decodeHex("a\\x20b\\x20c\\x20d")).isEqualTo("a b c d");
    }
}