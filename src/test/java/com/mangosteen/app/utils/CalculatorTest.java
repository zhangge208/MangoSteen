package com.mangosteen.app.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


class CalculatorTest {

    public static final int NUM_1 = 100;
    public static final int EXPECTED = 102;

    @Test //Test case
    //@RepeatedTest(10)
    @DisplayName("First Test Case")
    //@Disabled
    void testAdd() {
        // Arrange
        int num1 = NUM_1;
        int num2 = 2;

        // Act
        val calculator = new Calculator();
        int result = calculator.add(num1, num2);
        System.out.println("First Test Case");

        // Assert
        assertEquals(EXPECTED, result);
    }
}
