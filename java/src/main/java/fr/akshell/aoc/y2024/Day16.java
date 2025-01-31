package fr.akshell.aoc.y2024;

import fr.akshell.aoc.base.BaseDay;
import fr.akshell.aoc.pojo.Maze;
import fr.akshell.aoc.pojo.Vector2D;
import fr.akshell.aoc.pojo.Vector4D;

import java.util.*;

import static fr.akshell.aoc.pojo.Maze.WALL;
import static fr.akshell.aoc.utils.MazeUtils.convertInputToMaze;

public class Day16 extends BaseDay<Integer> {
    public static final char START = 'S';
    public static final char END = 'E';

    public record Step(Vector4D position, Step previous, int distance) {}
    public record Step2(Vector4D position, boolean[][] visited, Step2 previous, int distance) {
        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            Step2 step2 = (Step2) o;
            return distance == step2.distance && Objects.equals(previous, step2.previous) && Objects.equals(position, step2.position) && Objects.deepEquals(visited, step2.visited);
        }

        @Override
        public int hashCode() {
            return Objects.hash(position, Arrays.deepHashCode(visited), previous, distance);
        }

        @Override
        public String toString() {
            return "Step2{position=" + position + ", visited=" + Arrays.deepToString(visited)
                    + ", previous=" + previous + ", distance=" + distance + "}";
        }
    }

    public Set<Vector2D> getPath(Step2 step) {
        Set<Vector2D> path = new HashSet<>();
        Step2 current = step;
        do {
            path.add(new Vector2D(current.position().x(), current.position().y()));
            current = current.previous();
        } while (current.previous() != null);
        return path;
    }

    public int bfs(Maze maze, Vector4D src, Vector2D dst) {
        if (maze.get(src.position()) == WALL || maze.get(dst) == WALL) {
            return -1;
        }

        boolean[][] visited = new boolean[maze.height()][maze.width()];
        visited[src.y()][src.x()] = true;

        PriorityQueue<Step> pq = new PriorityQueue<>(Comparator.comparingInt(Step::distance));
        pq.add(new Step(src, null, 0));

        while (!pq.isEmpty()) {
            var popped = pq.poll();
            Vector4D pt = popped.position();
            int dist = popped.distance();

            if (pt.x() == dst.x() && pt.y() == dst.y()) {
                return dist;
            }

            List<Step> moves = new ArrayList<>();
            moves.add(new Step(pt.moveForward(), popped, dist + 1));
            moves.add(new Step(pt.turnLeft().moveForward(), popped, dist + 1001));
            moves.add(new Step(pt.turnRight().moveForward(), popped, dist + 1001));

            for (Step move : moves) {
                Vector4D newPt = move.position();
                int row = newPt.y();
                int col = newPt.x();
                if (maze.isValid(newPt) && maze.get(newPt) != WALL && !visited[row][col]) {
                    visited[row][col] = true;
                    pq.add(move);
                }
            }
        }

        return -1;
    }

    public static boolean[][] copyMatrix(boolean[][] matrix) {
        boolean[][] newMatrix = new boolean[matrix.length][];
        for (int i = 0; i < matrix.length; i++) {
            newMatrix[i] = new boolean[matrix[i].length];
            System.arraycopy(matrix[i], 0, newMatrix[i], 0, matrix[i].length);
        }
        return newMatrix;
    }

    public List<Step2> bfsNew(Maze maze, Vector4D src, Vector2D dst) {
        if (maze.get(src.position()) == WALL || maze.get(dst) == WALL) {
            return Collections.emptyList();
        }

        boolean[][] visited = new boolean[maze.height()][maze.width()];
        visited[src.y()][src.x()] = true;

        PriorityQueue<Step2> pq = new PriorityQueue<>(Comparator.comparingInt(Step2::distance));
        pq.add(new Step2(src, visited, null, 0));

        List<Step2> allPaths = new ArrayList<>();

        while (!pq.isEmpty()) {
            var popped = pq.poll();
            Vector4D pt = popped.position();
            int dist = popped.distance();

            if (pt.x() == dst.x() && pt.y() == dst.y()) {
                allPaths.add(popped);
                continue;
            }

            List<Step2> moves = new ArrayList<>();
            moves.add(new Step2(pt.moveForward(), copyMatrix(popped.visited()), popped, dist + 1));
            moves.add(new Step2(pt.turnLeft().moveForward(), copyMatrix(popped.visited()), popped, dist + 1001));
            moves.add(new Step2(pt.turnRight().moveForward(), copyMatrix(popped.visited()), popped, dist + 1001));

            for (Step2 move : moves) {
                Vector4D newPt = move.position();
                int row = newPt.y();
                int col = newPt.x();
                if (maze.isValid(newPt) && maze.get(newPt) != WALL && !visited[row][col]) {
                    move.visited()[row][col] = true;
                    pq.add(move);
                }
            }
        }

        allPaths.sort(Comparator.comparingInt(Step2::distance));
        return allPaths;
    }

    public Integer part1(String input) {
        Maze maze = convertInputToMaze(input);
        Vector2D start = maze.find(START);
        Vector2D end = maze.find(END);
        assert start != null;
        Vector4D startPos =  Vector4D.of(start, 1, 0);
        int distance = bfs(maze, startPos, end);
        logger.info("distance: {}", distance);
        return distance;
    }

    public Integer part2(String input) {
        Maze maze = convertInputToMaze(input);
        Vector2D start = maze.find(START);
        Vector2D end = maze.find(END);
        assert start != null;
        Vector4D startPos =  Vector4D.of(start, 1, 0);
        List<Step2> allPaths = bfsNew(maze, startPos, end);
        assert !allPaths.isEmpty();
        int score = allPaths.getFirst().distance();

        for (Step2 step : allPaths) {
            Set<Vector2D> path = getPath(step);
            logger.info("path ({}): {}", path.size(), path);
        }
        logger.info("score: {}", score);
        return score;
    }
}
