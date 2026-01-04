package fr.akshell.aoc.y2025;

import fr.akshell.aoc.base.BaseDay;
import jakarta.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Day12 extends BaseDay<Long> {

    public record Shape(char[][][] parts) {

        public static Shape of(char[][] parts) {
            char[][][] rotatedParts = new char[4][][];
            rotatedParts[0] = parts;
            for (int i = 1; i < 4; i++) {
                rotatedParts[i] = rotate(rotatedParts[i - 1]);
            }
            return new Shape(rotatedParts);
        }

        private static char[][] rotate(char[][] parts) {
            int newHeight = parts[0].length;
            int newWidth = parts.length;
            char[][] rotated = new char[newHeight][newWidth];
            for (int y = 0; y < parts.length; y++) {
                for (int x = 0; x < parts[y].length; x++) {
                    rotated[x][newWidth - y - 1] = parts[y][x];
                }
            }
            return rotated;
        }

        public char[][] rotated(int direction) {
            return parts[direction % 4];
        }

        @Override
        public @Nonnull String toString() {
            StringBuilder sb = new StringBuilder();
            for (char[][] part : parts) {
                for (char[] line : part) {
                    sb.append(line).append("\n");
                }
                sb.append("\n");
            }
            return sb.toString();
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Shape other = (Shape) obj;
            if (parts.length != other.parts.length) return false;
            for (int i = 0; i < parts.length; i++) {
                if (parts[i].length != other.parts[i].length) return false;
                for (int j = 0; j < parts[i].length; j++) {
                    if (parts[i][j].length != other.parts[i][j].length) return false;
                    for (int k = 0; k < parts[i][j].length; k++) {
                        if (parts[i][j][k] != other.parts[i][j][k]) return false;
                    }
                }
            }
            return true;
        }

        @Override
        public int hashCode() {
            int result = 1;
            for (char[][] part : parts) {
                for (char[] line : part) {
                    for (char c : line) {
                        result = 31 * result + Character.hashCode(c);
                    }
                }
            }
            return result;
        }
    }

    public record Dimension(int width, int height) {}
    public record Region(Dimension dimension, List<Integer> shapeQuantities) {}
    public record Problem(List<Shape> shapes, List<Region> regions) {}

    private static Shape parseShape(String rawShape) {
        String[] lines = rawShape.lines().toList().toArray(new String[0]);
        char[][] parts = new char[lines.length - 1][];
        for (int i = 0; i < lines.length - 1; i++) {
            parts[i] = lines[i + 1].toCharArray();
        }
        return Shape.of(parts);
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

    private boolean canPlaceShape(char[][] regionGrid, char[][] shape, int startX, int startY) {
        for (int y = 0; y < shape.length; y++) {
            for (int x = 0; x < shape[y].length; x++) {
                if (shape[y][x] == '#') {
                    if (startY + y >= regionGrid.length || startX + x >= regionGrid[0].length || regionGrid[startY + y][startX + x] == '#') {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private long fitShapesInRegion(Region region, List<Shape> shapes) {
        // Logic to fit shapes in the region
        List<Integer> shapesToPlace = new ArrayList<>();
        for (int i = 0; i < region.shapeQuantities().size(); i++) {
            for (int j = 0; j < region.shapeQuantities().get(i); j++) {
                shapesToPlace.add(i);
            }
        }

        for (Integer shapeIndex : shapesToPlace) {
            Shape shape = shapes.get(shapeIndex);
            for (int dir = 0; dir < 4; dir++) {
                char[][] rotatedShape = shape.rotated(dir);
                int maxX = region.dimension().width - rotatedShape.length;
                int maxY = region.dimension().height - rotatedShape[0].length;
                for (int x = 0; x < maxX; x++) {
                    for (int y = 0; y < maxY; y++) {
                        if (canPlaceShape(new char[region.dimension().height][region.dimension().width], rotatedShape, x, y)) {
                            // Check if shape can be placed at (x, y)
                        }
                    }
                }
            }
        }

        return 0L;
    }

    @Override
    public Long part1(String input) {
        Problem problem = parseInput(input);
        return problem.regions.stream()
                .map(region -> fitShapesInRegion(region, problem.shapes()))
                .reduce(0L, Long::sum);
    }

    @Override
    public Long part2(String input) {
        return -1L;
    }
}
