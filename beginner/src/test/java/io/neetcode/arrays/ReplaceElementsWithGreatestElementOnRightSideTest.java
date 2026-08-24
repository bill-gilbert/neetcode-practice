package io.neetcode.arrays;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;

class ReplaceElementsWithGreatestElementOnRightSideTest {

    private final ReplaceElementsWithGreatestElementOnRightSide solution = 
        new ReplaceElementsWithGreatestElementOnRightSide();

    /**
     * Helper: проверяет, что результат совпадает с ожидаемым
     */
    private void assertReplace(int[] arr, int[] expected) {
        int[] original = arr.clone(); // сохраняем копию для отладки
        int[] result = solution.replaceElements(arr);
        
        assertArrayEquals(expected, result,
            () -> "Для массива " + Arrays.toString(original) + 
                  " ожидалось " + Arrays.toString(expected) + 
                  ", но получили " + Arrays.toString(result));
    }

    // ============ EDGE CASES ============

    @Test
    void testSingleElement() {
        // Единственный элемент должен стать -1
        assertReplace(new int[]{42}, new int[]{-1});
    }

    @Test
    void testTwoElements() {
        // [a, b] → [b, -1]
        assertReplace(new int[]{5, 3}, new int[]{3, -1});
    }

    @Test
    void testTwoElementsReversed() {
        // [a, b] → [b, -1]
        assertReplace(new int[]{3, 5}, new int[]{5, -1});
    }

    // ============ СОРТИРОВАННЫЕ МАССИВЫ ============

    @Test
    void testSortedAscending() {
        // [1, 2, 3, 4, 5] → [5, 5, 5, 5, -1]
        // Каждый элемент заменяется на максимум справа (это всегда следующий элемент)
        assertReplace(new int[]{1, 2, 3, 4, 5}, new int[]{5, 5, 5, 5, -1});
    }

    @Test
    void testSortedDescending() {
        // [5, 4, 3, 2, 1] → [4, 3, 2, 1, -1]
        // Каждый элемент заменяется на максимум справа (это всегда следующий элемент)
        assertReplace(new int[]{5, 4, 3, 2, 1}, new int[]{4, 3, 2, 1, -1});
    }

    // ============ ВСЕ ОДИНАКОВЫЕ ============

    @Test
    void testAllSameElements() {
        // [7, 7, 7, 7] → [7, 7, 7, -1]
        assertReplace(new int[]{7, 7, 7, 7}, new int[]{7, 7, 7, -1});
    }

    // ============ МАКСИМУМ В РАЗНЫХ ПОЗИЦИЯХ ============

    @Test
    void testMaxAtBeginning() {
        // [100, 1, 2, 3] → [3, 3, 3, -1]
        // Максимум в начале, но он не влияет на замену (смотрим только справа)
        assertReplace(new int[]{100, 1, 2, 3}, new int[]{3, 3, 3, -1});
    }

    @Test
    void testMaxAtEnd() {
        // [1, 2, 3, 100] → [100, 100, 100, -1]
        // Максимум в конце, все элементы слева становятся 100
        assertReplace(new int[]{1, 2, 3, 100}, new int[]{100, 100, 100, -1});
    }

    @Test
    void testMaxInMiddle() {
        // [1, 2, 100, 3, 4] → [100, 100, 4, 4, -1]
        // Максимум в середине, элементы слева от него становятся 100
        assertReplace(new int[]{1, 2, 100, 3, 4}, new int[]{100, 100, 4, 4, -1});
    }

    // ============ ОТРИЦАТЕЛЬНЫЕ ЧИСЛА ============

    @Test
    void testAllNegative() {
        // [-5, -3, -1, -2] → [-1, -1, -2, -1]
        assertReplace(new int[]{-5, -3, -1, -2}, new int[]{-1, -1, -2, -1});
    }

    @Test
    void testMixedPositiveNegative() {
        // [-1, 2, -3, 4, -5] → [4, 4, 4, -5, -1]
        assertReplace(new int[]{-1, 2, -3, 4, -5}, new int[]{4, 4, 4, -5, -1});
    }

    // ============ ПОВТОРЯЮЩИЕСЯ МАКСИМУМЫ ============

    @Test
    void testMultipleMaxValues() {
        // [1, 5, 2, 5, 3] → [5, 5, 5, 3, -1]
        // Максимум (5) встречается дважды
        assertReplace(new int[]{1, 5, 2, 5, 3}, new int[]{5, 5, 5, 3, -1});
    }

    @Test
    void testConsecutiveMaxValues() {
        // [1, 5, 5, 5, 2] → [5, 5, 5, 2, -1]
        // Несколько максимумов подряд
        assertReplace(new int[]{1, 5, 5, 5, 2}, new int[]{5, 5, 5, 2, -1});
    }

    // ============ БОЛЬШИЕ ЗНАЧЕНИЯ ============

    @Test
    void testLargeValues() {
        // [Integer.MAX_VALUE, 1, 2] → [2, 2, -1]
        assertReplace(new int[]{Integer.MAX_VALUE, 1, 2}, new int[]{2, 2, -1});
    }

    @Test
    void testMinValue() {
        // [Integer.MIN_VALUE, 1, 2] → [2, 2, -1]
        assertReplace(new int[]{Integer.MIN_VALUE, 1, 2}, new int[]{2, 2, -1});
    }

    // ============ ЧЕРЕДОВАНИЕ ============

    @Test
    void testAlternatingHighLow() {
        // [10, 1, 10, 1, 10] → [10, 10, 10, 10, -1]
        assertReplace(new int[]{10, 1, 10, 1, 10}, new int[]{10, 10, 10, 10, -1});
    }

    @Test
    void testAlternatingLowHigh() {
        // [1, 10, 1, 10, 1] → [10, 10, 10, 1, -1]
        assertReplace(new int[]{1, 10, 1, 10, 1}, new int[]{10, 10, 10, 1, -1});
    }

    // ============ ПЛАТО ============

    @Test
    void testPlateau() {
        // [1, 5, 5, 5, 2, 5, 5] → [5, 5, 5, 5, 5, 5, -1]
        assertReplace(new int[]{1, 5, 5, 5, 2, 5, 5}, new int[]{5, 5, 5, 5, 5, 5, -1});
    }

    // ============ ПРИМЕР ИЗ УСЛОВИЯ ============

    @Test
    void testExampleFromDescription() {
        // [17, 18, 5, 4, 6, 1] → [18, 6, 6, 6, 1, -1]
        assertReplace(new int[]{17, 18, 5, 4, 6, 1}, new int[]{18, 6, 6, 6, 1, -1});
    }
}