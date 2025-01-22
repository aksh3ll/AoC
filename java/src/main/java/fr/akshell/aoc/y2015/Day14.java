package fr.akshell.aoc.y2015;

import fr.akshell.aoc.base.BaseDay;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Setter
public class Day14 extends BaseDay<Integer> {
    private final static Pattern RE_REINDEER = Pattern.compile(
            "^(\\w+) can fly (\\d+) km/s for (\\d+) seconds, but then must rest for (\\d+) seconds\\.$");

    private record Reindeer(String name, int speed, int flyTime, int restTime) {}
    private record Track(Reindeer reindeer, int distance, int points) {}

    private int time;

    private List<Reindeer> convertInput(String input) {
        return Arrays
                .stream(input.split("\n"))
                .filter(s -> !s.isBlank())
                .map(s -> {
                    var matcher = RE_REINDEER.matcher(s);
                    if (matcher.matches()) {
                        return new Reindeer(matcher.group(1), Integer.parseInt(matcher.group(2)),
                                Integer.parseInt(matcher.group(3)), Integer.parseInt(matcher.group(4)));
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .toList();
    }
    
    private Map<String, Integer> racePart1(List<Reindeer> reindeers, int timeLimit) {
        return reindeers.stream()
                .collect(Collectors.toMap(Reindeer::name, reindeer -> {
                    int cycleTime = reindeer.flyTime + reindeer.restTime;
                    int cycles = timeLimit / cycleTime;
                    int remainingTime = timeLimit % cycleTime;
                    int distance = cycles * reindeer.speed * reindeer.flyTime;
                    distance += Math.min(reindeer.flyTime, remainingTime) * reindeer.speed;
                    return distance;
                }));
    }

    private Map<String, Integer> racePart2(List<Reindeer> reindeers, int timeLimit) {
        Map<String, Integer> scores = reindeers.stream().collect(Collectors.toMap(Reindeer::name, r -> 0));
        for (int time = 1; time <= timeLimit; time++) {
            var positions = racePart1(reindeers, time);
            int maxDistance = positions.values().stream().mapToInt(Integer::intValue).max().orElse(0);
            for (var entry : positions.entrySet()) {
                if (entry.getValue() == maxDistance) {
                    scores.put(entry.getKey(), scores.get(entry.getKey()) + 1);
                }
            }
        }
        return scores;
    }

    @Override
    public Integer part1(String input) {
        List<Reindeer> reindeers = convertInput(input);
        Map<String, Integer> positions = racePart1(reindeers, time);
        return positions.values().stream().mapToInt(Integer::intValue).max().orElse(0);
    }

    @Override
    public Integer part2(String input) {
        List<Reindeer> reindeers = convertInput(input);
        Map<String, Integer> positions = racePart2(reindeers, time);
        return positions.values().stream().mapToInt(Integer::intValue).max().orElse(0);
    }
}
