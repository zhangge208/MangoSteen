package com.mangosteen.app.utils;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculatorTest {

    @Test //Test case
    //@RepeatedTest(10)
    @DisplayName("First Test Case")
    //@Disabled
    void testAdd() {
        // Arrange
        int num1 = 100;
        int num2 = 2;

        // Act
        val calculator = new Calculator();
        int result = calculator.add(num1, num2);
        System.out.println("First Test Case");

        // Assert
        assertEquals(102, result);
    }
}
