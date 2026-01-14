package fr.akshell.aoc.y2015;

import fr.akshell.aoc.base.BaseDay;
import fr.akshell.aoc.utils.MiscUtils;

import java.io.Serializable;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day7 extends BaseDay<Integer> {

    private static String[] getGroups(Matcher matcher) {
        String[] groups = new String[matcher.groupCount()];
        for (int i = 1; i <= matcher.groupCount(); i++) {
            groups[i - 1] = matcher.group(i);
        }
        return groups;
    }

    private static Collection<String> getStrings(String... regs) {
        return Arrays.stream(regs).filter(reg -> !MiscUtils.isNumber(reg)).toList();
    }

    private static int getValue(String reg, Map<String, Integer> registers) {
        return MiscUtils.isNumber(reg) ? Integer.parseInt(reg) : registers.get(reg);
    }

    private static final String RE_OPERAND = "([\\da-z]+)";
    private static final Pattern RE_INSTRUCTIONS = Pattern.compile(
            "^(?:" + RE_OPERAND + " (AND|OR) " + RE_OPERAND + "|([a-z]+) (LSHIFT|RSHIFT) (\\d+)|NOT ([a-z]+)|"
                    + RE_OPERAND + ") -> ([a-z]+)$");

    public interface IOperation extends Serializable {
        String dst();
        Collection<String> sources();
        void execute(Map<String, Integer> registers);
    }

    private record AND(String reg1, String reg2, String dst) implements IOperation {
        public Collection<String> sources() {
            return getStrings(reg1, reg2);
        }
        public void execute(Map<String, Integer> registers) {
            registers.put(dst, getValue(reg1, registers) & getValue(reg2, registers));
        }
    }

    private record OR(String reg1, String reg2, String dst) implements IOperation {
        public Collection<String> sources() {
            return getStrings(reg1, reg2);
        }

        public void execute(Map<String, Integer> registers) {
            registers.put(dst, getValue(reg1, registers) | getValue(reg2, registers));
        }
    }

    private record LSHIFT(String reg, int value, String dst) implements IOperation {
        public Collection<String> sources() {
            return List.of(reg);
        }
        public void execute(Map<String, Integer> registers) {
            registers.put(dst, (registers.get(reg) << value) & 65535);
        }
    }

    private record RSHIFT(String reg, int value, String dst) implements IOperation {
        public Collection<String> sources() {
            return List.of(reg);
        }
        public void execute(Map<String, Integer> registers) {
            registers.put(dst, (registers.get(reg) >> value) & 65535);
        }
    }

    private record NOT(String reg, String dst) implements IOperation {
        public Collection<String> sources() {
            return List.of(reg);
        }
        public void execute(Map<String, Integer> registers) {
            registers.put(dst, (~registers.get(reg)) & 65535);
        }
    }
    private record SET(String src, String dst) implements IOperation {
        public Collection<String> sources() {
            return getStrings(src);
        }
        public void execute(Map<String, Integer> registers) {
            registers.put(dst, getValue(src, registers));
        }
    }

    protected static IOperation getOperation(String instruction) {
        var matcher = RE_INSTRUCTIONS.matcher(instruction);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Invalid instruction: " + instruction);
        }
        var parts = getGroups(matcher);
        var dst = parts[8];
        if (parts[7] != null) {
            return new SET(parts[7], dst);
        } else if (Objects.equals(parts[1], "AND")) {
            return new AND(parts[0], parts[2], dst);
        } else if (Objects.equals(parts[1], "OR")) {
            return new OR(parts[0], parts[2], dst);
        } else if (Objects.equals(parts[4], "LSHIFT")) {
            return new LSHIFT(parts[3], Integer.parseInt(parts[5]), dst);
        } else if (Objects.equals(parts[4], "RSHIFT")) {
            return new RSHIFT(parts[3], Integer.parseInt(parts[5]), dst);
        } else {
            return new NOT(parts[6], dst);
        }
    }

    private List<IOperation> getOperations(String input) {
        return input.lines()
                .map(Day7::getOperation)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private List<IOperation> executeOperations(Map<String, Integer> registers, List<IOperation> operations) {
        List<IOperation> executed = new ArrayList<>();
        while (!operations.isEmpty()) {
            var op = operations.removeFirst();
            if (op.sources().stream().allMatch(registers::containsKey)) {
                op.execute(registers);
                executed.add(op);
            } else {
                operations.add(op);
            }
        }
        return executed;
    }

    public Integer part1(String input) {
        // Parse input
        List<IOperation> operations = getOperations(input);
        // Empty registers
        Map<String, Integer> registers = new HashMap<>();
        // Execute operations
        executeOperations(registers, operations);
        // Return value of register a
        return registers.getOrDefault("a", 0);
    }

    public Integer part2(String input) {
        // Parse input
        List<IOperation> operations = getOperations(input);
        // Empty registers
        Map<String, Integer> registers = new HashMap<>();
        // Execute operations
        List<IOperation> executed = executeOperations(registers, operations);
        // Get value of register a
        int registerA = registers.getOrDefault("a", 0);
        // Remove operation that sets b
        executed = executed.stream()
                .filter(op -> !(op instanceof SET && "b".equals(op.dst())))
                .collect(Collectors.toCollection(ArrayList::new));
        // Reset registers
        registers = new HashMap<>();
        // Set b to the value of register a
        registers.put("b", registerA);
        // Execute previous operations in the same order
        executeOperations(registers, executed);
        // Return value of register a
        return registers.getOrDefault("a", 0);
    }
}
