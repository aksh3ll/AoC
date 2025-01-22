package fr.akshell.aoc.y2015;

import fr.akshell.aoc.base.BaseDay;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;


public class Day16 extends BaseDay<Integer> {

    private Map<String, Integer> samples;

    public void setSamples(String samples) {
        this.samples = Arrays
                .stream(samples.strip().split("\n"))
                .filter(s -> !s.isBlank())
                .map(p -> p.split(": "))
                .collect(Collectors
                        .toMap(
                        p -> p[0],
                        p -> Integer.parseInt(p[1])));
    }

    public Map<Integer, Map<String, Integer>> getSues(String input) {
        return Arrays
                .stream(input.strip().split("\n"))
                .filter(s -> !s.isBlank())
                .collect(Collectors
                        .toMap(
                        s -> Integer.parseInt(s.split(" ")[1].replace(":", "")),
                        s -> Arrays
                                .stream(s.split(": ", 2)[1].split(", "))
                                .map(p -> p.split(": "))
                                .collect(Collectors
                                        .toMap(
                                        p -> p[0],
                                        p -> Integer.parseInt(p[1])))));
    }

    private int scorePart1(Map<String, Integer> sue) {
        return sue.entrySet().stream()
                .filter(e -> !samples.containsKey(e.getKey()) || samples.get(e.getKey()).equals(e.getValue()))
                .mapToInt(e -> 1)
                .sum();
    }

    private int scorePart2(Map<String, Integer> sue) {
        int score = 0;
        for (Map.Entry<String, Integer> e : sue.entrySet()) {
            if (!samples.containsKey(e.getKey())) {
                score++;
            } else if (e.getKey().equals("cats") || e.getKey().equals("trees")) {
                if (samples.get(e.getKey()) < e.getValue()) {
                    score++;
                }
            } else if (e.getKey().equals("pomeranians") || e.getKey().equals("goldfish")) {
                if (samples.get(e.getKey()) > e.getValue()) {
                    score++;
                }
            } else if (samples.get(e.getKey()).equals(e.getValue())) {
                score++;
            }
        }
        return score;
    }

    @Override
    public Integer part1(String input) {
        Map<Integer, Map<String, Integer>> sues = getSues(input);
        return sues.entrySet().stream()
                .map(e -> Map.entry(e.getKey(), scorePart1(e.getValue())))
                .sorted((e1, e2) -> e2.getValue() - e1.getValue())
                .map(Map.Entry::getKey)
                .findFirst()
                .orElseThrow();
    }

    @Override
    public Integer part2(String input) {
        Map<Integer, Map<String, Integer>> sues = getSues(input);
        return sues.entrySet().stream()
                .map(e -> Map.entry(e.getKey(), scorePart2(e.getValue())))
                .sorted((e1, e2) -> e2.getValue() - e1.getValue())
                .map(Map.Entry::getKey)
                .findFirst()
                .orElseThrow();
    }
}
