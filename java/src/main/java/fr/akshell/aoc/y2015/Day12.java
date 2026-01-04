package fr.akshell.aoc.y2015;

import fr.akshell.aoc.base.BaseDay;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.ObjectMapper;

import java.util.Map;

public class Day12 extends BaseDay<Integer> {
    ObjectMapper objectMapper = new ObjectMapper();

    private static class DecodingException extends RuntimeException {
        public DecodingException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    private Iterable<?> deserializeJsonInput(String input) {
        input = input.trim().replaceAll("\n$", "").trim();
        try {
            return objectMapper.readValue(String.format("[%s]", input), Iterable.class);
        } catch (JacksonException e) {
            throw new DecodingException("Failing to decode input", e);
        }
    }

    private Integer sum(Object iterable, boolean redFlag) {
        if (iterable instanceof Integer integer) {
            return integer;
        }
        if (iterable instanceof Map<?,?>) {
            return sum(((Map<?,?>) iterable).values(), redFlag);
        }
        int sum = 0;

        for (Object next : (Iterable<?>) iterable) {
            if (next instanceof Integer integer) {
                sum += integer;
            }
            if (next instanceof Iterable) {
                sum += sum(next, redFlag);
            }
            if (next instanceof Map<?, ?> map && !(redFlag && map.containsValue("red"))) {
                sum += sum(map.values(), redFlag);
            }
        }
        return sum;
    }

    public Integer part1(String input) {
        return sum(deserializeJsonInput(input), false);
    }

    public Integer part2(String input) {
        return sum(deserializeJsonInput(input), true);
    }
}
