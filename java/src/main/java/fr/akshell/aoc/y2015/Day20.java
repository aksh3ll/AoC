package fr.akshell.aoc.y2015;

import fr.akshell.aoc.base.BaseDay;

public class Day20 extends BaseDay<Integer> {

    public static long sumOfDivisors(long n) {
        if (n <= 0) return 0;
        long result = 1;
        long temp = n;

        for (long p = 2; p * p <= temp; p++) {
            if (temp % p == 0) {
                long power = 1;
                while (temp % p == 0) {
                    temp /= p;
                    power *= p;
                }
                // term = (p^(a+1) - 1) / (p - 1) = 1 + p + ... + p^a
                long term = (power * p - 1) / (p - 1);
                result *= term;
            }
        }

        if (temp > 1) {
            // reste premier
            long term = 1 + temp;
            result *= term;
        }

        return result;
    }

    public static int sumFactors(int number) {
        int sum = 0;
        for (int i = 1; 2 * i <= number; i++) {
            if (number % i == 0) {
                sum += i;
            }
        }
        sum += number;
        return sum;
    }

    public static int sumFactorsWithLimit(int number, int limit) {
        int sum = 0;
        for (int i = 1; 2 * i <= number; i++) {
            if (number % i == 0) {
                if (i * limit >= number)
                    sum += i;
            }
        }
        if (limit * limit >= number)
            sum += number;
        return sum;
    }

    public static int findHouseNumberLinear(int from, int limit) {
        int houseNumber = from;
        while (true) {
            if (sumOfDivisors(houseNumber) * 10 >= limit) {
                return houseNumber;
            }
            houseNumber++;
        }
    }

    public static int findHouseNumberWithLimit(int limit) {
        int houseNumber = 1;
        while (true) {
            if (sumFactorsWithLimit(houseNumber, 10) * 11 >= limit) {
                return houseNumber;
            }
            houseNumber++;
        }
    }

    public Integer part1(String input) {
        int presents = Integer.parseInt(input.trim());
        return findHouseNumberLinear(1, presents);
    }

    public Integer part2(String input) {
        int limit = Integer.parseInt(input.trim());
        return findHouseNumberWithLimit(limit);
    }
}
