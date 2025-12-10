package fr.akshell.aoc.y2025;

import fr.akshell.aoc.base.BaseDay;
import java.util.List;
import java.util.Map;

public class Day9 extends BaseDay<Long> {

    public record Point(int x, int y) {
        public static Point of(String line) {
            String[] parts = line.split(",");
            return new Point(Integer.parseInt(parts[0].trim()), Integer.parseInt(parts[1].trim()));
        }
    }
    private final Map<Point, Boolean> insideCache = new java.util.HashMap<>();
    private List<Point> redTiles;

    List<Point> parseInput(String input) {
        return input.lines().map(Point::of).toList();
    }

    @Override
    public Long part1(String input) {
        redTiles = parseInput(input);
        long maxArea = 0;
        for (int i = 0; i <= redTiles.size() - 1; i++) {
            for (int j = i + 1; j <= redTiles.size() - 1; j++) {
                Point p1 = redTiles.get(i);
                Point p2 = redTiles.get(j);
                int width = Math.abs(p2.x - p1.x) + 1;
                int height = Math.abs(p2.y - p1.y) + 1;
                long area = (long) width * height;
                if (area > maxArea) {
                    maxArea = area;
                }
            }
        }
        return maxArea;
    }

    public boolean contains(Point p) {
        if (redTiles == null || redTiles.size() < 3 || p == null) {
            return false;
        }
        if  (insideCache.containsKey(p)) {
            return insideCache.get(p);
        }

        int n = redTiles.size();
        double px = p.x();
        double py = p.y();
        boolean inside = false;

        for (int i = 0, j = n - 1; i < n; j = i++) {
            Point a = redTiles.get(i);
            Point b = redTiles.get(j);

            // si le point est sur un segment, considérer comme dedans
            if (isOnSegment(a, b, p)) {
                return true;
            }

            double ax = a.x();
            double ay = a.y();
            double bx = b.x();
            double by = b.y();

            // test d'intersection du rayon horizontal vers +inf avec le segment (a,b)
            boolean intersects = ((ay > py) != (by > py))
                    && (px < (bx - ax) * (py - ay) / (by - ay) + ax);
            if (intersects) {
                inside = !inside;
            }
        }

        insideCache.put(p, inside);
        return inside;
    }

    private static boolean isOnSegment(Point a, Point b, Point p) {
        long cross = (long)(p.x() - a.x()) * (b.y() - a.y()) - (long)(p.y() - a.y()) * (b.x() - a.x());
        if (cross != 0) {
            return false;
        }
        int minX = Math.min(a.x(), b.x());
        int maxX = Math.max(a.x(), b.x());
        int minY = Math.min(a.y(), b.y());
        int maxY = Math.max(a.y(), b.y());
        return p.x() >= minX && p.x() <= maxX && p.y() >= minY && p.y() <= maxY;
    }

    private boolean isAllPointsInRedTiles(Point p1, Point p2) {
        int minX = Math.min(p1.x, p2.x);
        int maxX = Math.max(p1.x, p2.x);
        int minY = Math.min(p1.y, p2.y);
        int maxY = Math.max(p1.y, p2.y);
        for  (int i = minX; i <= maxX; i++) {
            for (int j = minY; j <= maxY; j++) {
                Point p = new Point(i, j);
                if (!contains(p)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public Long part2(String input) {
        redTiles = parseInput(input);
        long maxArea = 0;
        for (int i = 0; i <= redTiles.size() - 1; i++) {
            for (int j = i + 1; j <= redTiles.size() - 1; j++) {
                Point p1 = redTiles.get(i);
                Point p2 = redTiles.get(j);
                long area = (long) (Math.abs(p2.x - p1.x) + 1) * (Math.abs(p2.y - p1.y) + 1);
                if (area > maxArea && isAllPointsInRedTiles(p1, p2)) {
                    maxArea = area;
                }
            }
        }
        return maxArea;
    }
}
