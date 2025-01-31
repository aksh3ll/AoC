package fr.akshell.aoc.y2015;

import fr.akshell.aoc.base.BaseDay;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day19 extends BaseDay<Integer> {

    private record Replacement(String from, String to) {}
    private record Calibration(String start, List<Replacement> replacements) {}

    private Calibration parseInput(String input) {
        String[] parts = input.split("\n\n");
        List<Replacement> replacements = parts[0]
                .lines()
                .map(replacement -> replacement.split(" => "))
                .map(replacement -> new Replacement(replacement[0], replacement[1]))
                .toList();
        return new Calibration(parts[1].strip(), replacements);
    }

    public Integer part1(String input) {
        Calibration calibration = parseInput(input);
        Set<String> molecules = new HashSet<>();
        calibration.replacements().forEach(replacement -> {
            String from = replacement.from();
            String to = replacement.to();
            int index = calibration.start().indexOf(from);
            while (index != -1) {
                String newMolecule = calibration.start().substring(0, index) + to + calibration.start().substring(index + from.length());
                molecules.add(newMolecule);
                index = calibration.start().indexOf(from, index + 1);
            }
        });
        return molecules.size();
    }

    public Integer part2(String input) {
        Calibration calibration = parseInput(input);
        return 0;
    }
}
