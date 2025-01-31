package fr.akshell.aoc.y2024;

import fr.akshell.aoc.base.BaseDay;
import fr.akshell.aoc.pojo.Vector2D;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.stream.Collectors;

@Getter
@Setter
public class Day14 extends BaseDay<Integer> {
    public static int MAX_TIME = 10000;
    public static Pattern RE_ROBOT = Pattern.compile("^p=(\\d+),(\\d+)\\s+v=([-\\d]+),([-\\d]+)$");

    private int width;
    private int height;
    private int time;

    public record Robot(int x, int y, int vx, int vy) {
        public Robot copy() {
            return new Robot(x, y, vx, vy);
        }
    }

    public List<Robot> read_input(String input) {
        return Arrays.stream(input.split("\\R"))
                .map(RE_ROBOT::matcher)
                .filter(Matcher::matches)
                .map(matcher -> new Robot(
                        Integer.parseInt(matcher.group(1)),
                        Integer.parseInt(matcher.group(2)),
                        Integer.parseInt(matcher.group(3)),
                        Integer.parseInt(matcher.group(4))
                ))
                .toList();
    }

    private int mod(int value, int modulo) {
        int result = value % modulo;
        return result < 0? result + modulo : result;
    }

    public int prod(int [] list) {
        return Arrays.stream(list).reduce(1, (a, b) -> a * b);
    }

    public int[][] robots_to_grid(List<Robot> robots, int width, int height) {
        int[][] matrix = new int[height][width];
        for (Robot robot : robots) {
            matrix[robot.y()][robot.x()] = 1;
        }
        return matrix;
    }

    public String gridToString(int[][] matrix) {
        return Arrays.stream(matrix)
                .map(row -> Arrays
                        .stream(row)
                        .mapToObj(c -> c == 0 ? "." : "*")
                        .collect(Collectors.joining())
                )
                .collect(Collectors.joining("\n"));
    }

    public int[] read_quadrants(List<Robot> robots, int width, int height) {
        int[] quadrants = {0, 0, 0, 0};
        int half_width = width / 2;
        int half_height = height / 2;
        for (Robot robot : robots) {
            if (robot.x() >= 0 && robot.x() < half_width && robot.y() >= 0 && robot.y() < half_height)
                quadrants[0] += 1;
            if (robot.x() > half_width && robot.x() < width && robot.y() >= 0 && robot.y() < half_height)
                quadrants[1] += 1;
            if (robot.x() >= 0 && robot.x() < half_width && robot.y() > half_height && robot.y() < height)
                quadrants[2] += 1;
            if (robot.x() > half_width && robot.x() < width && robot.y() > half_height && robot.y() < height)
                quadrants[3] += 1;
        }
        return quadrants;
    }

    public int getSegments(int[][] matrix) {
        var segments = new ArrayList<Vector2D>();
        int size = 0;
        int current = 0;
        for (int[] row : matrix) {
            for (int cell : row) {
                if (cell == current) {
                    size++;
                } else {
                    segments.add(new Vector2D(current, size));
                    current = cell;
                }
            }
        }
        return segments.size();
    }

    public List<Robot> move_robots(List<Robot> robots, int width, int height, int time) {
        return robots
                .stream()
                .map(r -> new Robot(
                        mod(r.x() + r.vx() * time, width),
                        mod(r.y() + r.vy() * time, height), r.vx(), r.vy()))
                .toList();
    }

    public Integer part1(String input) {
        List<Robot> robots = read_input(input);
        List<Robot> new_robots = move_robots(robots, width, height, time);
        var quadrants = read_quadrants(new_robots, width, height);
        int score = prod(quadrants);
        LOGGER.info("score: {}", score);
        return score;
    }

    public Integer part2(String input) {
        List<Robot> robots = read_input(input);
        var i_robots = robots.stream().map(Robot::copy).toList();
        int min_segments = Integer.MAX_VALUE;
        int min_time_segments = 0;
        int time = 0;
        while (time < MAX_TIME) {
            int n_segments = getSegments(robots_to_grid(i_robots, width, height));
            if (n_segments < min_segments) {
                min_segments = n_segments;
                min_time_segments = time;
            }
            i_robots = move_robots(i_robots, width, height, 1);
            time++;
        }

        LOGGER.info("min segments: {}\nmin time segments: {}\nrobots:\n{}",
                min_segments, min_time_segments,
                gridToString(robots_to_grid(move_robots(robots, width, height, min_time_segments), width, height)));

        return min_time_segments;
    }
}
