package me.upp.daligz.stringcalculator;

import java.util.Arrays;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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
        final String finalNumbers = numbers;
        final String finalDelimiter = delimiter;
        Supplier<IntStream> streamSupplier = () -> Arrays.stream(finalNumbers.split(finalDelimiter))
                .filter(numStr -> !numStr.isEmpty())
                .map(Integer::valueOf)
                .mapToInt(Integer::intValue);

        final int sum = streamSupplier.get().sum();

        // Check negatives
        final int[] ints = streamSupplier.get().toArray();
        final StringBuilder negatives = new StringBuilder();
        final int length = ints.length;
        for (int i = 0; i < length; i++){
            final int anInt = ints[i];
            if (anInt > 0) continue;
            negatives.append(anInt);
            if ((i + 1) < length) negatives.append(", ");
        }

        if (!negatives.isEmpty()) {
            try {
                throw new Exception("Negatives not allowed - (" + negatives + ")");
            } catch (final Exception exception) {
                exception.printStackTrace();
            }
        }

        return sum;

        /*
        .filter(s -> {
                    try {
                        if (Integer.parseInt(s) < 0) throw new Exception("asd");
                    } catch (Exception e) {
                        e.printStackTrace();
                        return false;
                    }
                    return true;
                })
         */
    }
}
