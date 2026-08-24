package io.neetcode.arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RemoveElementTest {

    private final RemoveElement solution = new RemoveElement();

    /**
     * Helper: проверяет, что:
     * 1. Возвращаемое значение k корректно
     * 2. Первые k элементов массива не содержат val
     */
    private void assertRemove(int[] nums, int val, int expectedK) {
        int[] original = nums.clone(); // сохраняем копию для отладки
        int k = solution.removeElement(nums, val);

        Assertions.assertEquals(expectedK, k,
                () -> "Ожидалось k=" + expectedK + " для массива " + java.util.Arrays.toString(original));

        for (int i = 0; i < k; i++) {
            final int currentIndex = i; // <-- Делаем переменную effectively final

            Assertions.assertNotEquals(val, nums[i],
                    () -> "В первых " + k + " элементах нашёлся val=" + val
                            + " на позиции " + currentIndex
                            + " (массив: " + java.util.Arrays.toString(nums) + ")");
        }
    }

    // ============ EDGE CASES ============

    @Test
    void testEmptyArray() {
        assertRemove(new int[]{}, 1, 0);
    }

    @Test
    void testSingleElementEqualsVal() {
        assertRemove(new int[]{5}, 5, 0);
    }

    @Test
    void testSingleElementNotEqualsVal() {
        assertRemove(new int[]{5}, 3, 1);
    }

    // ============ ВСЕ ЭЛЕМЕНТЫ ОДИНАКОВЫЕ ============

    @Test
    void testAllElementsEqualVal() {
        // Ловит баги, когда решение "забывает" уменьшить счётчик
        assertRemove(new int[]{2, 2, 2, 2, 2}, 2, 0);
    }

    @Test
    void testAllElementsNotEqualVal() {
        // Ловит баги, когда решение всё равно что-то меняет в массиве
        assertRemove(new int[]{1, 1, 1, 1, 1}, 9, 5);
    }

    // ============ VAL В РАЗНЫХ ПОЗИЦИЯХ ============

    @Test
    void testValAtBothEndsAndMiddle() {
        // val и в начале, и в конце, и в середине — ловит баги двух указателей
        assertRemove(new int[]{2, 2, 2, 3, 3, 3, 2, 2, 2}, 2, 3);
    }

    @Test
    void testAlternatingVal() {
        // Чередование — ловит баги, когда указатели "перепрыгивают"
        assertRemove(new int[]{2, 3, 2, 3, 2, 3}, 2, 3);
    }

    @Test
    void testValOnOddPositions() {
        assertRemove(new int[]{3, 1, 3, 1, 3}, 3, 2);
    }

    @Test
    void testValOnEvenPositions() {
        assertRemove(new int[]{1, 3, 1, 3, 1}, 3, 3);
    }

    // ============ VAL ВООБЩЕ НЕТ В МАССИВЕ ============

    @Test
    void testValNotPresent() {
        // Ловит баги, когда решение всё равно модифицирует массив
        assertRemove(new int[]{1, 2, 3, 4, 5}, 9, 5);
    }

    // ============ ОТРИЦАТЕЛЬНЫЕ ЧИСЛА ============

    @Test
    void testNegativeVal() {
        assertRemove(new int[]{-3, -2, -1, 0, 1, 2, 3}, -3, 6);
    }

    @Test
    void testAllNegative() {
        assertRemove(new int[]{-5, -5, -5, -1, -1}, -5, 2);
    }

    // ============ ЧАСТЫЕ ВСТРЕЧАЕМОСТИ ============

    @Test
    void testValAppearsFrequently() {
        // val встречается часто, но не везде — ловит баги подсчёта
        assertRemove(new int[]{1, 2, 2, 3, 2, 4, 2, 5}, 2, 4);
    }

    @Test
    void testConsecutiveValInMiddle() {
        // Длинная серия val подряд в середине
        assertRemove(new int[]{1, 2, 2, 2, 2, 2, 3}, 2, 2);
    }

    // ============ БОЛЬШИЕ ЗНАЧЕНИЯ ============

    @Test
    void testLargeValues() {
        assertRemove(new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE}, 
                     Integer.MAX_VALUE, 1);
    }
}