package me.upp.daligz.stringcalculator;

import java.util.Arrays;

public class StringCalculator {

    public int add(String numbers) {
        // Remove spaces from expression
        numbers = numbers.replaceAll(" ", "");
        if (numbers.isEmpty()) return 0;
        return Arrays.stream(numbers.split(","))
                .filter(numStr -> !(numStr.replace(",", "").isEmpty()))
                .map(Integer::valueOf)
                .mapToInt(Integer::intValue)
                .sum();
    }
}
