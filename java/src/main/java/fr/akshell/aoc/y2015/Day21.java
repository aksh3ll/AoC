package fr.akshell.aoc.y2015;

import fr.akshell.aoc.base.BaseDay;

import java.util.ArrayList;
import java.util.List;

public class Day21 extends BaseDay<Integer> {
    public record Equipment(int cost, int damage, int armor) {
        public static Equipment of(int cost, int damage, int armor) {
            return new Equipment(cost, damage, armor);
        }
        public static Equipment of(Equipment... equipments) {
            int costs = 0;
            int damages = 0;
            int armors = 0;
            for (Equipment equipment : equipments) {
                costs += equipment.cost;
                damages += equipment.damage;
                armors += equipment.armor;
            }
            return new Equipment(costs, damages, armors);
        }
    }
    public record Character(int hitPoints, int damage, int armor) {
        public static Character of(int hitPoints, int damage, int armor) {
            return new Character(hitPoints, damage, armor);
        }
    }

    /**
     Weapons:    Cost  Damage  Armor
     Dagger        8     4       0
     Shortsword   10     5       0
     Warhammer    25     6       0
     Longsword    40     7       0
     Greataxe     74     8       0
    */
    private static final Equipment[] WEAPONS = {
        Equipment.of(8, 4, 0),
        Equipment.of(10, 5, 0),
        Equipment.of(25, 6, 0),
        Equipment.of(40, 7, 0),
        Equipment.of(74, 8, 0)
    };

    /**
     Armor:      Cost  Damage  Armor
     Leather      13     0       1
     Chainmail    31     0       2
     Splintmail   53     0       3
     Bandedmail   75     0       4
     Platemail   102     0       5
    */
    private static final Equipment[] ARMORS = {
        Equipment.of(0, 0, 0),
        Equipment.of(13, 0, 1),
        Equipment.of(31, 0, 2),
        Equipment.of(53, 0, 3),
        Equipment.of(75, 0, 4),
        Equipment.of(102, 0, 5)
    };
    /**
     Rings:      Cost  Damage  Armor
     Damage +1    25     1       0
     Damage +2    50     2       0
     Damage +3   100     3       0
     Defense +1   20     0       1
     Defense +2   40     0       2
     Defense +3   80     0       3
     */
    private static final Equipment[] RINGS = {
        Equipment.of(0, 0, 0),
        Equipment.of(0, 0, 0),
        Equipment.of(25, 1, 0),
        Equipment.of(50, 2, 0),
        Equipment.of(100, 3, 0),
        Equipment.of(20, 0, 1),
        Equipment.of(40, 0, 2),
        Equipment.of(80, 0, 3)
    };

    private List<Equipment> generateEquipments() {
        List<Equipment> equipments = new ArrayList<>();
        for (Equipment weapon : WEAPONS) {
            for (Equipment armor : ARMORS) {
                for (int i = 0; i < RINGS.length - 1; i++) {
                    for (int j = i + 1; j < RINGS.length; j++) {
                        equipments.add(Equipment.of(weapon, armor, RINGS[i], RINGS[j]));
                    }
                }
            }
        }
        return equipments;
    }

    private Character parseInput(String input) {
        String[] lines = input.split("\n");
        int hitPoints = Integer.parseInt(lines[0].split(": ")[1]);
        int damage = Integer.parseInt(lines[1].split(": ")[1]);
        int armor = Integer.parseInt(lines[2].split(": ")[1]);
        return Character.of(hitPoints, damage, armor);
    }

    private boolean doesPlayerWin(Character player, Character boss) {
        int playerEffectiveDamage = Math.max(1, player.damage - boss.armor);
        int bossEffectiveDamage = Math.max(1, boss.damage - player.armor);

        int playerTurnsToWin = (int) Math.ceil((double) boss.hitPoints / playerEffectiveDamage);
        int bossTurnsToWin = (int) Math.ceil((double) player.hitPoints / bossEffectiveDamage);

        return playerTurnsToWin <= bossTurnsToWin;
    }

    private int getLowestCostToWin(List<Equipment> equipments, Character boss, int playerHitPoints) {
        return equipments.stream()
                .filter(equipment -> {
                    Character player = Character.of(playerHitPoints, equipment.damage, equipment.armor);
                    return doesPlayerWin(player, boss);
                })
                .mapToInt(equipment -> equipment.cost)
                .min()
                .orElse(-1);
    }

    private int getHighestCostToLose(List<Equipment> equipments, Character boss, int playerHitPoints) {
        return equipments.stream()
                .filter(equipment -> {
                    Character player = Character.of(playerHitPoints, equipment.damage, equipment.armor);
                    return !doesPlayerWin(player, boss);
                })
                .mapToInt(equipment -> equipment.cost)
                .max()
                .orElse(-1);
    }

    public Integer part1Test(String input) {
        Character boss = parseInput(input);
        return getLowestCostToWin(List.of(Equipment.of(8, 5, 5)), boss, 8);
    }

    @Override
    public Integer part1(String input) {
        Character boss = parseInput(input);
        return getLowestCostToWin(generateEquipments(), boss, 100);
    }

    public Integer part2Test(String input) {
        Character boss = parseInput(input);
        return getHighestCostToLose(List.of(Equipment.of(8, 5, 5)), boss, 8);
    }

    @Override
    public Integer part2(String input) {
        Character boss = parseInput(input);
        return getHighestCostToLose(generateEquipments(), boss, 100);
    }
}
