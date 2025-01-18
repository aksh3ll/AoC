package fr.akshell.aoc.y2015;

import fr.akshell.aoc.base.BaseDay;
import fr.akshell.aoc.pojo.Vector2D;

import java.util.HashSet;
import java.util.Set;

public class Day3 extends BaseDay {

    public Vector2D move(Vector2D position, char c) {
        return switch (c) {
            case '^' -> new Vector2D(position.x(), position.y() + 1);
            case 'v' -> new Vector2D(position.x(), position.y() - 1);
            case '>' -> new Vector2D(position.x() + 1, position.y());
            case '<' -> new Vector2D(position.x() - 1, position.y());
            default -> position;
        };
    }

    public long part1(String input) {
        Set<Vector2D> visited = new HashSet<>();
        var position = new Vector2D(0, 0);
        visited.add(position);
        for (char c : input.toCharArray()) {
            position = move(position, c);
            visited.add(position);
        }
        return visited.size();
    }

    public long part2(String input) {
        Set<Vector2D> visited = new HashSet<>();
        var santa = new Vector2D(0, 0);
        var robot = new Vector2D(0, 0);
        visited.add(santa);
        for (int i = 0; i < input.length(); i++) {
            var position = i % 2 == 0 ? santa : robot;
            char c = input.charAt(i);
            position = move(position, c);
            visited.add(position);
            if (i % 2 == 0) {
                santa = position;
            } else {
                robot = position;
            }
        }
        return visited.size();
    }
}
