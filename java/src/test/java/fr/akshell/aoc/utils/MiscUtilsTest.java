package fr.akshell.aoc.utils;

import static fr.akshell.aoc.utils.MazeUtils.convertInputToMaze;
import static fr.akshell.aoc.utils.MiscUtils.permute;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

import fr.akshell.aoc.pojo.Maze;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

class MiscUtilsTest {

    @Test
    void givenString_whenIsNumber_thenTypeChecked() {
        assertThat(MiscUtils.isNumber("123")).isTrue();
        assertThat(MiscUtils.isNumber("123.0")).isFalse();
        assertThat(MiscUtils.isNumber("abc")).isFalse();
        assertThat(MiscUtils.isNumber("1.23E2")).isFalse();
        assertThat(MiscUtils.isNumber(null)).isFalse();
        assertThat(MiscUtils.isNumber("")).isFalse();
    }

    @Test
    void givenComplexObject_whenDeepCopy_thenTotallyNewObjectReturned() {
        Maze literal = convertInputToMaze("abcd\nbcde\ncdef\ndefg");
        Maze copy = MiscUtils.deepCopy(literal);
        assertThat(copy).isNotSameAs(literal).isEqualTo(literal);
    }

    @Test
    void givenBrokenObject_whenDeepCopy_thenExceptionIsReturned() {
        try (MockedConstruction<ObjectInputStream> _ = mockConstruction(ObjectInputStream.class,
                (mock, _) -> when(mock.readObject()).thenThrow(new IOException()))) {
            Maze literal = convertInputToMaze("abcd\nbcde\ncdef\ndefg");
            assertThatThrownBy(() -> MiscUtils.deepCopy(literal)).isInstanceOf(MiscUtils.DeepCopyException.class);
        }
    }

    @Test
    void givenListInteger_whenPermute_thenAllPermutationsReturned() {
        List<Integer> nums = List.of(1, 2, 3);
        List<List<Integer>> permutations = permute(nums);
        assertThat(permutations).hasSize(6);
    }

    @Test
    void givenListString_whenPermute_thenAllPermutationsReturned() {
        List<String> nums = List.of("Paris", "London", "Berlin");
        List<List<String>> permutations = permute(nums);
        assertThat(permutations).hasSize(6);
    }

    @Test
    void givenString_whenDecodeHex_thenDecodedStringReturned() {
        assertThat(MiscUtils.decodeHex("a\\x20b")).isEqualTo("a b");
        assertThat(MiscUtils.decodeHex("a\\x20b\\x20c")).isEqualTo("a b c");
        assertThat(MiscUtils.decodeHex("a\\x20b\\x20c\\x20d")).isEqualTo("a b c d");
        assertThat(MiscUtils.decodeHex("abc")).isEqualTo("abc");
        assertThat(MiscUtils.decodeHex("aaa\\\"aaa")).isEqualTo("aaa\"aaa");
        assertThat(MiscUtils.decodeHex("\\x27")).isEqualTo("'");
        assertThat(MiscUtils.decodeHex("\\n\\r\\t\\b\\f\\z\\")).isEqualTo("\n\r\t\b\f\\z\\");
        assertThat(MiscUtils.decodeHex("x\\\"\\xcaj\\\\xwwvpdldz")).isEqualTo("x\"Êj\\xwwvpdldz");
    }
}