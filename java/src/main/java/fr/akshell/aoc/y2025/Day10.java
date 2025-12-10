package fr.akshell.aoc.y2025;

import fr.akshell.aoc.base.BaseDay;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.NonNull;

public class Day10 extends BaseDay<Long> {

    public record Button(List<Integer> toggleIndices) {}
    public record Machine(List<Boolean> lights, List<Button> buttons, List<Integer> joltages) {
        public static Machine fromString(String line) {
            String[] parts = line.split(" ");
            List<Boolean> lights = getLights(parts[0]);
            List<Button> buttons = getButtons(parts);
            List<Integer> joltageValues = getJoltageValues(parts[parts.length - 1]);
            return new Machine(lights, buttons, joltageValues);
        }

        private static @NonNull List<Boolean> getLights(String lightsPart) {
            return lightsPart.substring(1, lightsPart.length() - 1).chars()
                    .mapToObj(c -> c == '#')
                    .toList();
        }

        private static @NonNull List<Button> getButtons(String[] parts) {
            List<Button> buttons = new ArrayList<>();
            for (int i = 1; i < parts.length - 1; i++) {
                String buttonPart = parts[i];
                String togglesStr = buttonPart.substring(1, buttonPart.length() - 1);
                List<Integer> toggleIndices = new ArrayList<>();
                for (String indexStr : togglesStr.split(",")) {
                    toggleIndices.add(Integer.parseInt(indexStr.trim()));
                }
                buttons.add(new Button(toggleIndices));
            }
            return buttons;
        }

        private static @NonNull List<Integer> getJoltageValues(String joltagePart) {
            return Arrays.stream(joltagePart
                            .substring(1, joltagePart.length() - 1)
                            .split(","))
                    .map(Integer::parseInt)
                    .toList();
        }
    }

    private List<Machine> parseInput(String input) {
        return input.lines().map(Machine::fromString).toList();
    }

    public static int findFewestButtonPresses(Machine machine) {
        int lightCount = machine.lights().size();
        int buttonCount = machine.buttons().size();
        int minPresses = Integer.MAX_VALUE;

        for (int combo = 0; combo < (1 << buttonCount); combo++) {
            List<Boolean> lightsState = new ArrayList<>(machine.lights());
            int presses = 0;

            for (int buttonIndex = 0; buttonIndex < buttonCount; buttonIndex++) {
                if ((combo & (1 << buttonIndex)) != 0) {
                    presses++;
                    for (int toggleIndex : machine.buttons().get(buttonIndex).toggleIndices()) {
                        lightsState.set(toggleIndex, !lightsState.get(toggleIndex));
                    }
                }
            }

            boolean allOn = lightsState.stream().allMatch(state -> state);
            if (allOn && presses < minPresses) {
                minPresses = presses;
            }
        }

        return minPresses == Integer.MAX_VALUE ? -1 : minPresses;
    }

    @Override
    public Long part1(String input) {
        return (long) parseInput(input).stream().map(Day10::findFewestButtonPresses).reduce(0, Integer::sum);
    }

    @Override
    public Long part2(String input) {
        return -1L;
    }
}
