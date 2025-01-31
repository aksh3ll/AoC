package fr.akshell.aoc.utils;

import fr.akshell.aoc.pojo.Maze;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class MazeUtils {

    public static Maze convertInputToMaze(String input) {
        List<String> lines = input.lines().toList();
        if (lines.isEmpty()) {
            throw new IllegalArgumentException("Invalid input");
        }
        char[][] maze = new char[lines.size()][];
        for (int i = 0; i < lines.size(); i++) {
            maze[i] = lines.get(i).trim().toCharArray();
        }
        return new Maze(maze, maze[0].length, maze.length);
    }
}
