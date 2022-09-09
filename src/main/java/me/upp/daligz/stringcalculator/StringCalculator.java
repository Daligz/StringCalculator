package me.upp.daligz.stringcalculator;

import java.util.Arrays;

public class StringCalculator {

    public int add(String numbers) {
        // Check if the string is empty
        if (numbers.isEmpty()) return 0;

        // Remove spaces and new lines from expression
        numbers = numbers
                .replaceAll(" ", "")
                .replaceAll("\n", ",");

        // Computing
        return Arrays.stream(numbers.split(","))
                .filter(numStr -> !numStr.isEmpty())
                .map(Integer::valueOf)
                .mapToInt(Integer::intValue)
                .sum();
    }
}
