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

    public record Circuit(Set<Point3D> boxes) {
        public int size() {
            return boxes.size();
        }
    }

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
        return distances.stream()
                .sorted(Comparator.comparingDouble(d -> d.distance))
                .toList();
    }

    private Distance findShortestDistanceBetweenCircuits(List<Circuit> circuits, List<Distance> distances) {
        return distances.stream()
                .filter(distance -> (circuits.getFirst().boxes().contains(distance.from) || circuits.getFirst().boxes().contains(distance.to)) &&
                        (circuits.getLast().boxes().contains(distance.from) || circuits.getLast().boxes().contains(distance.to)))
                .min(Comparator.comparingDouble(Distance::distance))
                .orElse(null);
    }

    private void mergeCircuit(Map<Point3D, Circuit> reverseCircuits, List<Circuit> circuits, Distance distance) {
        Circuit circuitFrom = reverseCircuits.get(distance.from);
        Circuit circuitTo = reverseCircuits.get(distance.to);
        circuitFrom.boxes().addAll(circuitTo.boxes());
        circuits.remove(circuitTo);
        for (Point3D point : circuitTo.boxes()) {
            reverseCircuits.put(point, circuitFrom);
        }
    }

    private Distance connectCircuits(List<Circuit> circuits, List<Distance> distances, int limit) {
        Map<Point3D, Circuit> reverseCircuits = new HashMap<>();
        circuits.forEach(circuit -> {
            for (Point3D point : circuit.boxes()) {
                reverseCircuits.put(point, circuit);
            }
        });

        int count = 1;
        for (Distance pair : distances) {
            count++;
            if (reverseCircuits.get(pair.from).equals(reverseCircuits.get(pair.to))) {
                continue;
            } else {
                mergeCircuit(reverseCircuits, circuits, pair);
            }
            if (circuits.size() == 2) {
                return findShortestDistanceBetweenCircuits(circuits, distances);
            }
            if (limit > 0 && count >= limit) {
                return null;
            }
        }
        return null;
    }

    private List<Circuit> createCircuits(List<Point3D> boxes) {
        return boxes.stream()
                .map(p -> new Circuit(new HashSet<>(List.of(p))))
                .collect(Collectors.toList());
    }

    @Override
    public Long part1(String input) {
        // parse input to get boxes coordinates
        List<Point3D> boxes = parseInput(input);
        // calculate distances between boxes
        List<Distance> distances = getDistances(boxes);
        // create circuits with a dynamic list because they will be merged
        List<Circuit> circuits = createCircuits(boxes);
        // connect circuits based on distances until the limit is reached
        connectCircuits(circuits, distances, limit);
        // sort circuits by size descending
        circuits = circuits.stream().sorted(Comparator.comparingInt(Circuit::size).reversed()).toList();
        // return the product of the 3 largest circuits sizes
        return (long) circuits.get(0).boxes().size() * circuits.get(1).boxes().size() * circuits.get(2).boxes().size();
    }

    @Override
    public Long part2(String input) {
        // parse input to get boxes coordinates
        List<Point3D> boxes = parseInput(input);
        // calculate distances between boxes
        List<Distance> distances = getDistances(boxes);
        // create circuits with a dynamic list because they will be merged
        List<Circuit> circuits = createCircuits(boxes);
        // connect circuits based on distances until the limit is reached
        Distance shortest = connectCircuits(circuits, distances, -1);
        assert shortest != null;
        return (long) shortest.from.x() * shortest.to.x();
    }
}
