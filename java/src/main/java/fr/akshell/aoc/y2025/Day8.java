package fr.akshell.aoc.y2025;

import fr.akshell.aoc.base.BaseDay;
import java.util.*;
import java.util.stream.Collectors;

import lombok.Setter;

@Setter
public class Day8 extends BaseDay<Long> {
    private int limit = 1000;

    public record Point3D(int x, int y, int z) {
        public static Point3D fromString(String s) {
            String[] parts = s.split(",");
            return new Point3D(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
        }
    }

    public record Circuit(Set<Point3D> boxes) {}

    public record Distance(Point3D from, Point3D to, double distance) {
        private static double calcDistance(Point3D from, Point3D to) {
            return Math.sqrt(Math.pow((double) from.x - to.x, 2) +
                    Math.pow((double) from.y - to.y, 2) +
                    Math.pow((double) from.z - to.z, 2));
        }

        public static Distance of(Point3D from, Point3D to) {
            return new Distance(from, to, calcDistance(from, to));
        }
    }

    private List<Point3D> parseInput(String input) {
        return Arrays.stream(input.split("\n"))
                .filter(s -> !s.isBlank())
                .map(Point3D::fromString)
                .toList();
    }

    private List<Distance> getDistances(List<Point3D> points) {
        List<Distance> distances = new ArrayList<>();
        for (int i = 0; i < points.size(); i++) {
            for (int j = i + 1; j < points.size(); j++) {
                distances.add(Distance.of(points.get(i), points.get(j)));
            }
        }
        return distances;
    }

    private void connectCircuits(List<Circuit> circuits, List<Distance> distances, int limit) {
        Map<Point3D, Circuit> reverseCircuits = new HashMap<>();
        circuits.forEach(circuit -> {
            for (Point3D point : circuit.boxes()) {
                reverseCircuits.put(point, circuit);
            }
        });

        int count = 1;
        for (Distance pair : distances) {
            if (reverseCircuits.get(pair.from).equals(reverseCircuits.get(pair.to))) {
                if (limit == 1000) {
                    count++;
                }
                continue;
            } else {
                Circuit circuitFrom = reverseCircuits.get(pair.from);
                Circuit circuitTo = reverseCircuits.get(pair.to);
                circuitFrom.boxes().addAll(circuitTo.boxes());
                circuits.remove(circuitTo);
                for (Point3D point : circuitTo.boxes()) {
                    reverseCircuits.put(point, circuitFrom);
                }
                count++;
            }

            if (count >= limit) {
                return;
            }
        }
    }

    @Override
    public Long part1(String input) {
        // parse input to get boxes coordinates
        List<Point3D> boxes = parseInput(input);
        // calculate distances between boxes
        List<Distance> distances = getDistances(boxes).stream()
                .sorted(Comparator.comparingDouble(d -> d.distance))
                .toList();
        // create circuits with a dynamic list because they will be merged
        List<Circuit> circuits = boxes.stream()
                .map(p -> new Circuit(new HashSet<>(List.of(p))))
                .collect(Collectors.toList());
        // connect circuits based on distances until the limit is reached
        connectCircuits(circuits, distances, limit);
        // sort circuits by size descending
        circuits = circuits.stream()
                .sorted(Comparator.comparingInt(c -> - c.boxes().size()))
                .toList();

        // return the product of the 3 largest circuits sizes
        return (long) circuits.get(0).boxes().size() * circuits.get(1).boxes().size() * circuits.get(2).boxes().size();
    }

    @Override
    public Long part2(String input) {
        return -1L;
    }
}
