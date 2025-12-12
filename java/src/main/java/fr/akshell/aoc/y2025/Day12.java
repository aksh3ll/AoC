package fr.akshell.aoc.y2025;

import fr.akshell.aoc.base.BaseDay;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Day12 extends BaseDay<Long> {

    public record Shape(char[][] parts) {}
    public record Dimension(int width, int height) {}
    public record Region(Dimension dimension, List<Integer> shapeQuantities) {}
    public record Problem(List<Shape> shapes, List<Region> regions) {}

    private static Shape parseShape(String rawShape) {
        String[] lines = rawShape.lines().toList().toArray(new String[0]);
        char[][] parts = new char[lines.length - 1][];
        for (int i = 0; i < lines.length - 1; i++) {
            parts[i] = lines[i + 1].toCharArray();
        }
        return new Shape(parts);
    }

    private static Region parseRegion(String rawRegion) {
        String[] parts = rawRegion.split(":");
        String[] dimParts = parts[0].split("x");
        Dimension dimension = new Dimension(Integer.parseInt(dimParts[0].trim()), Integer.parseInt(dimParts[1].trim()));
        List<Integer> quantities = Stream.of(parts[1].trim().split(" "))
                .map(Integer::parseInt)
                .toList();
        return new Region(dimension, quantities);
    }

    private static Problem parseInput(String input) {
        String[] sections = input.split("\n\n");
        // Parsing logic to be implemented
        List<Shape> shapes = new ArrayList<>();
        for (int i = 0; i < sections.length - 1; i++) {
            shapes.add(parseShape(sections[i]));
        }
        List<Region> regions = sections[sections.length - 1].lines().map(Day12::parseRegion).toList();
        return new Problem(shapes, regions);
    }

    @Override
    public Long part1(String input) {
        Problem problem = parseInput(input);
        return -1L;
    }

    @Override
    public Long part2(String input) {
        return -1L;
    }
}
