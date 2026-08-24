package io.neetcode.arrays;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MaxConsecutiveOnesTest {
    private final MaxConsecutiveOnes solution = new MaxConsecutiveOnes();

    // 1. Обязательно требуемый тест: пустой массив
    @Test
    void testEmptyArray() {
        int[] nums = {};
        assertEquals(0, solution.findMaxConsecutiveOnes(nums),
                "Пустой массив должен возвращать 0");
    }

    // 2. Базовый сценарий: классическое чередование
    @Test
    void testMixedOnesAndZeros() {
        int[] nums = {1, 1, 0, 1, 1, 1, 0, 1};
        assertEquals(3, solution.findMaxConsecutiveOnes(nums),
                "Максимальная серия из 3-х единиц");
    }

    // 3. Edge case: массив вообще не содержит единиц
    @Test
    void testAllZeros() {
        int[] nums = {0, 0, 0, 0, 0};
        assertEquals(0, solution.findMaxConsecutiveOnes(nums),
                "Если единиц нет, максимум должен быть 0");
    }

    // 4. Edge case: массив заканчивается на единицы (ловушка для многих)
    @Test
    void testEndsWithOnes() {
        int[] nums = {1, 0, 1, 1, 1, 1};
        assertEquals(4, solution.findMaxConsecutiveOnes(nums),
                "Серия в самом конце массива должна быть учтена");
    }

    // 5. (Бонус) Edge case: массив состоит только из единиц
    @Test
    void testAllOnes() {
        int[] nums = {1, 1, 1, 1, 1};
        assertEquals(5, solution.findMaxConsecutiveOnes(nums),
                "Если все единицы, максимум равен длине массива");
    }
}
