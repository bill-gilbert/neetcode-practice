package io.neetcode.recursion;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ClimbingStairsTest {

    private ClimbingStairs solution;

    @BeforeEach
    void setUp() {
        solution = new ClimbingStairs();
    }

    @Test
    void climbStairs_Example1() {
        // Пример 1 из условия
        assertEquals(2, solution.climbStairs(2));
    }

    @Test
    void climbStairs_Example2() {
        // Пример 2 из условия
        assertEquals(3, solution.climbStairs(3));
    }

    @Test
    void climbStairs_MinConstraint() {
        // Минимальное возможное значение n
        assertEquals(1, solution.climbStairs(1));
    }

    @Test
    void climbStairs_MaxConstraint() {
        // Максимальное возможное значение n по условию (1 <= n <= 45)
        // Проверяем, что нет переполнения int (Integer.MAX_VALUE = 2147483647)
        assertEquals(1836311903, solution.climbStairs(45));
    }


    @ParameterizedTest(name = "Для n={0} ожидаемый результат={1}")
    @CsvSource({
            "1, 1",
            "2, 2",
            "3, 3",
            "4, 5",
            "5, 8",
            "6, 13",
            "7, 21",
            "10, 89",
            "20, 10946",
            "45, 1836311903"
    })
    void climbStairs_MultipleCases(int n, int expected) {
        assertEquals(expected, solution.climbStairs(n));
    }
}
