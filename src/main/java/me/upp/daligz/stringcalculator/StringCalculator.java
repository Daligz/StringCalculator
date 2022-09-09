package me.upp.daligz.stringcalculator;

import java.util.Arrays;

public class StringCalculator {

    public int add(String numbers) {

        String delimiter = ",";

        // Detect new delimiter
        if (numbers.startsWith("//")) {
            numbers = numbers.replaceFirst("//", "");
            delimiter = numbers.split("\n")[0];
        }

        // Check if the string is empty
        if (numbers.isEmpty()) return 0;

        // Remove spaces and new lines from expression
        numbers = numbers
                .replaceAll(" ", "")
                .replaceAll("\n", delimiter);

        // Computing
        return Arrays.stream(numbers.split(delimiter))
                .filter(numStr -> !numStr.isEmpty())
                .map(Integer::valueOf)
                .mapToInt(Integer::intValue)
                .sum();
    }
}
