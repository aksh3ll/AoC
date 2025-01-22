package fr.akshell.aoc.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MiscUtils {

    public static boolean isNumber(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    public static <T extends Serializable> List<T> deepCopy(List<T> original) {
        try {
            ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(byteOut);
            out.writeObject(original);
            out.flush();
            ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
            ObjectInputStream in = new ObjectInputStream(byteIn);
            return (List<T>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Failed to deep copy the list", e);
        }
    }


    public static <T> List<List<T>> permute(List<T> nums) {
        List<List<T>> result = new ArrayList<>();
        backtrack(result, new ArrayList<>(), nums);
        return result;
    }

    public static <T> List<List<T>> getAllCombinations(List<T> nums) {
        List<List<T>> result = new ArrayList<>();
        generateCombinations(nums, 0, new ArrayList<>(), result);
        return result;
    }

    private static <T> void generateCombinations(List<T> nums, int start, List<T> current, List<List<T>> result) {
        result.add(new ArrayList<>(current));
        for (int i = start; i < nums.size(); i++) {
            current.add(nums.get(i));
            generateCombinations(nums, i + 1, current, result);
            current.removeLast();
        }
    }

    private static <T> void backtrack(List<List<T>> result, List<T> tempList, List<T> nums) {
        if (tempList.size() == nums.size()) {
            result.add(new ArrayList<>(tempList));
        } else {
            for (int i = 0; i < nums.size(); i++) {
                if (tempList.contains(nums.get(i))) continue; // element already exists, skip
                tempList.add(nums.get(i));
                backtrack(result, tempList, nums);
                tempList.removeLast();
            }
        }
    }

    public static String decodeHex(String input) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '\\' && i + 3 < input.length() && input.charAt(i + 1) == 'x') {
                String hex = input.substring(i + 2, i + 4);
                output.append((char) Integer.parseInt(hex, 16));
                i += 3;
            } else {
                output.append(input.charAt(i));
            }
        }
        return output.toString();
    }
}
