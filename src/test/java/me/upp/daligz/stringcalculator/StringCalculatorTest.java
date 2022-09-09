package me.upp.daligz.stringcalculator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringCalculatorTest {

    private static final StringCalculator INSTANCE = new StringCalculator();

    @Test
    public void emptyAdd() {
        assertEquals(0, INSTANCE.add(""));
    }

    @Test
    public void upToTwoNumbersAdd() {
        assertEquals(500, INSTANCE.add("250, 250"));
    }

    @Test
    public void detectNewLinesAdd() {
        assertEquals(750, INSTANCE.add("250, 250 \n 250"));
    }

    @Test
    public void changeDelimiterAdd() {
        assertEquals(500, INSTANCE.add("//;\n250;250"));
    }

    @Test
    public void detectNegativesAdd() {
        assertEquals(2, INSTANCE.add("-50, -1, -2, 55"));
    }
}