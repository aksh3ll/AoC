package fr.akshell.aoc.y2015;

import fr.akshell.aoc.base.BaseDay;

public class Day21 extends BaseDay<Integer> {

    /**
     Weapons:    Cost  Damage  Armor
     Dagger        8     4       0
     Shortsword   10     5       0
     Warhammer    25     6       0
     Longsword    40     7       0
     Greataxe     74     8       0
    */
    private static final int[][] WEAPONS = {
            {8, 4, 0},
            {10, 5, 0},
            {25, 6, 0},
            {40, 7, 0},
            {74, 8, 0}
    };

    /**
     Armor:      Cost  Damage  Armor
     Leather      13     0       1
     Chainmail    31     0       2
     Splintmail   53     0       3
     Bandedmail   75     0       4
     Platemail   102     0       5
    */
    private static final int[][] ARMORS = {
            {0, 0, 0},
            {13, 0, 1},
            {31, 0, 2},
            {53, 0, 3},
            {75, 0, 4},
            {102, 0, 5}
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
    private static final int[][] RINGS = {
            {0, 0, 0},
            {0, 0, 0},
            {25, 1, 0},
            {50, 2, 0},
            {100, 3, 0},
            {20, 0, 1},
            {40, 0, 2},
            {80, 0, 3}
    };

    private record Equipment(int cost, int damage, int armor) {}

    private Equipment[] generateEquipments() {
        Equipment[] equipments = new Equipment[WEAPONS.length * ARMORS.length * (RINGS.length * (RINGS.length - 1)) / 2];
        int index = 0;
        for (int[] weapon : WEAPONS) {
            for (int[] armor : ARMORS) {
                for (int i = 0; i < RINGS.length - 1; i++) {
                    for (int j = i + 1; j < RINGS.length; j++) {
                        int cost = weapon[0] + armor[0] + RINGS[i][0] + RINGS[j][0];
                        int damage = weapon[1] + armor[1] + RINGS[i][1] + RINGS[j][1];
                        int arm = weapon[2] + armor[2] + RINGS[i][2] + RINGS[j][2];
                        equipments[index++] = new Equipment(cost, damage, arm);
                    }
                }
            }
        }
        return equipments;
    }



    @Override
    public Integer part1(String input) {
        return 0;
    }

    @Override
    public Integer part2(String input) {
        return 0;
    }
}
