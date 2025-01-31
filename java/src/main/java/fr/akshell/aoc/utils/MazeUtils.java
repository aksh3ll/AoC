package fr.akshell.aoc.utils;

import fr.akshell.aoc.pojo.Maze;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MazeUtils {

    public static Maze convertInputToMaze(String input) {
        String[] map_lines = input.split("\n");
        assert map_lines.length > 0;
        char[][] map = new char[map_lines.length][];
        for (int i = 0; i < map_lines.length; i++) {
            map[i] = map_lines[i].trim().toCharArray();
        }
        return new Maze(map, map[0].length, map.length);
    }
}
