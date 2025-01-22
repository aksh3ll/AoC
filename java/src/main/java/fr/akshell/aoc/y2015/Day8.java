package fr.akshell.aoc.y2015;

import fr.akshell.aoc.base.BaseDay;

import java.text.Normalizer;
import java.util.Arrays;
import java.util.function.Function;
import java.util.regex.Pattern;

import static fr.akshell.aoc.utils.MiscUtils.decodeHex;


public class Day8 extends BaseDay<Integer> {

    private record StringSize(int sizeDecoded, int sizeEncoded) {}

    private static final Function<String, String> ANTISLASH_ESCAPE =
            string -> string
                    .replace("\\", "\\\\")
                    .replace("\"", "\\\"");

    public static String convertToAscii(String input) {
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(normalized)
                .replaceAll("")
                .replaceAll("[^\\p{ASCII}]", "")
                .translateEscapes();
    }

    private StringSize stringSizeDecoded(String line) {
        return new StringSize(line.length(), decodeHex(line.substring(1, line.length() - 1)).length());
    }

    private StringSize stringSizeEncoded(String line) {
        return new StringSize(line.length(), ('"' + ANTISLASH_ESCAPE.apply(line) + '"').length());
    }

    public Integer part1(String input) {
        return Arrays.stream(input.strip().split("\n"))
                .map(this::stringSizeDecoded)
                .mapToInt(size -> size.sizeDecoded() - size.sizeEncoded())
                .sum();
    }

    public Integer part2(String input) {
        return Arrays.stream(input.strip().split("\n"))
                .map(this::stringSizeEncoded)
                .mapToInt(size -> size.sizeEncoded() - size.sizeDecoded())
                .sum();
    }
}
