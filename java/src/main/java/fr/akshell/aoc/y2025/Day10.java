package fr.akshell.aoc.y2025;

import fr.akshell.aoc.base.BaseDay;
import fr.akshell.aoc.utils.MathUtils;
import lombok.NonNull;
import java.util.*;

public class Day10 extends BaseDay<Long> {

    public record Button(List<Integer> toggleIndices) {}
    public record Machine(boolean[] lights, List<Button> buttons, int[] joltages) {
        public static Machine fromString(String line) {
            String[] parts = line.split(" ");
            return new Machine(getLights(parts[0]), getButtons(parts), getJoltageValues(parts[parts.length - 1]));
        }

        private static boolean @NonNull [] getLights(String lightsPart) {
            int[] chars = lightsPart.substring(1, lightsPart.length() - 1).chars().toArray();
            boolean[] lights = new boolean[chars.length];
            for (int i = 0; i < chars.length; i++) {
                lights[i] = chars[i] == '#';
            }
            return lights;
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

        private static int @NonNull [] getJoltageValues(String joltagePart) {
            String[] subParts = joltagePart.substring(1, joltagePart.length() - 1).split(",");
            int[] joltages = new int[subParts.length];
            for (int i = 0; i < subParts.length; i++) {
                joltages[i] = Integer.parseInt(subParts[i].trim());
            }
            return joltages;
        }

        public @NonNull String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Lights: ");
            for (boolean light : lights) {
                sb.append(light ? '#' : '.');
            }
            sb.append("\nButtons:\n");
            for (Button button : buttons) {
                sb.append(" - Toggles: ").append(button.toggleIndices).append("\n");
            }
            sb.append("Joltages: ").append(joltages).append("\n");
            return sb.toString();
        }

        public int hashCode() {
            return Arrays.hashCode(lights) + buttons.hashCode() + Arrays.hashCode(joltages);
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Machine other = (Machine) obj;
            return Arrays.equals(lights, other.lights) &&
                    buttons.equals(other.buttons) &&
                    Arrays.equals(joltages, other.joltages);
        }
    }

    private List<Machine> parseInput(String input) {
        return input.lines().map(Machine::fromString).toList();
    }

    private static boolean isEqualValues(boolean[] a, boolean[] b) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) {
                break;
            }
            if (i == a.length - 1) {
                return true;
            }
        }
        return false;
    }

    private static boolean isEqualValues(int[] a, int[] b) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) {
                break;
            }
            if (i == a.length - 1) {
                return true;
            }
        }
        return false;
    }

    public static int findFewestButtonPresses(Machine machine, boolean checkJoltageValues) {
        int presses = 1;
        boolean[] lightsState;
        int[] joltages;
        while (presses < 13) {
            MathUtils.Combinations<Button> combinations = new MathUtils.Combinations<>(machine.buttons, presses);
            while (combinations.hasNext()) {
                lightsState = new boolean[machine.lights.length];
                joltages = new int[machine.joltages.length];
                boolean doBreak = false;
                List<Button> buttons = combinations.next();
                for (Button button : buttons) {
                    for (int toggleIndex : button.toggleIndices) {
                        lightsState[toggleIndex] = !lightsState[toggleIndex];
                        if (checkJoltageValues) {
                            joltages[toggleIndex] += 1;
                            if (joltages[toggleIndex] > machine.joltages[toggleIndex]) {
                                doBreak = true;
                                break;
                            }
                        }
                    }
                    if (doBreak) {
                        break;
                    }
                }
                if (doBreak) {
                    continue;
                }
                if (isEqualValues(lightsState, machine.lights) && (!checkJoltageValues || isEqualValues(joltages, machine.joltages))) {
                    return presses;
                }
            }
            presses++;
        }
        return -1;
    }

    @Override
    public Long part1(String input) {
        return (long) parseInput(input).stream().map(m -> findFewestButtonPresses(m, false)).reduce(0, Integer::sum);
    }

    @Override
    public Long part2(String input) {
        return (long) parseInput(input).stream().map(m -> findFewestButtonPresses(m, true)).reduce(0, Integer::sum);
    }
}
