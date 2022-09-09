package me.upp.daligz.stringcalculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.IntStream;

public class StringCalculator {

    public int add(String numbers) {

        // Check if the string is empty
        if (numbers.isEmpty()) return 0;

        final String defaultDelimiter = ",";
        final List<String> delimiters = new ArrayList<>();

        // Detect new delimiter
        if (numbers.startsWith("//")) {
            numbers = numbers.replaceFirst("//", "")
                    .replaceAll("\\[", "");
            delimiters.addAll(Arrays.stream(numbers.split("(]|\n)")).toList());
            numbers = numbers.replaceAll("]", "");
        }

        // Remove spaces and new lines from expression
        numbers = numbers
                .replaceAll(" ", "")
                .replaceAll("\n", defaultDelimiter);
        for (final String delimiter : delimiters) numbers = numbers.replace(delimiter, ",");

        // Computing
        final String finalNumbers = numbers;
        Supplier<IntStream> streamSupplier = () -> Arrays.stream(finalNumbers.split(defaultDelimiter))
                .filter(numStr -> !numStr.isEmpty())
                .map(Integer::valueOf)
                // Remove numbers bigger than 1000
                .filter(integer -> integer <= 1000)
                .mapToInt(Integer::intValue);

        // Check negatives
        final int[] ints = streamSupplier.get().toArray();
        final StringBuilder negatives = new StringBuilder();
        final int length = ints.length;
        for (int i = 0; i < length; i++) {
            final int anInt = ints[i];
            if (anInt > 0) continue;
            negatives.append(anInt);
            if ((i + 2) < length) negatives.append(", ");
        }

        if (!negatives.isEmpty()) {
            try {
                throw new Exception("Negatives not allowed - (" + negatives + ")");
            } catch (final Exception exception) {
                exception.printStackTrace();
            }
        }

        return streamSupplier.get().sum();
    }
}
