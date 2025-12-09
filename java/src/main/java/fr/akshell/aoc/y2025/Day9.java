package fr.akshell.aoc.y2025;

import fr.akshell.aoc.base.BaseDay;

import java.util.List;

public class Day9 extends BaseDay<Long> {

    public record Point(int x, int y) {}

    List<Point> parseInput(String input) {;
        return input.lines()
                .map(line -> {
                    String[] parts = line.split(",");
                    return new Point(Integer.parseInt(parts[0].trim()), Integer.parseInt(parts[1].trim()));
                })
                .toList();
    }

    @Override
    public Long part1(String input) {
        List<Point> redTiles = parseInput(input);
        int maxArea = 0;
        for (int i = 0; i <= redTiles.size() - 1; i++) {
            for (int j = i + 1; j <= redTiles.size() - 1; j++) {
                Point p1 = redTiles.get(i);
                Point p2 = redTiles.get(j);
                int width = Math.abs(p2.x - p1.x) + 1;
                int height = Math.abs(p2.y - p1.y) + 1;
                int area = width * height;
                if (area > maxArea) {
                    maxArea = area;
                }
            }
        }
        return (long) maxArea;
    }

    @Override
    public Long part2(String input) {
        return -1L;
    }
}
