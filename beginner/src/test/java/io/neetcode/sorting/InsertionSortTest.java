package io.neetcode.sorting;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class InsertionSortTest {
    @Test
    void testEmptyArray() {
        int[] arr = {};
        int[] result = InsertionSort.insertionSort(arr);
        assertArrayEquals(new int[]{}, result, "Пустой массив должен оставаться пустым");
    }

    @Test
    void testSingleElement() {
        int[] arr = {5};
        int[] result = InsertionSort.insertionSort(arr);
        assertArrayEquals(new int[]{5}, result, "Один элемент должен остаться без изменений");
    }

    @Test
    void testAlreadySorted() {
        int[] arr = {1, 2, 3, 4, 5};
        int[] result = InsertionSort.insertionSort(arr);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, result, "Уже отсортированный массив не должен измениться");
    }

    @Test
    void testReverseOrder() {
        int[] arr = {5, 4, 3, 2, 1};
        int[] result = InsertionSort.insertionSort(arr);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, result, "Массив должен быть отсортирован по возрастанию");
    }

    @Test
    void testWithDuplicates() {
        int[] arr = {4, 2, 4, 3, 2, 1, 4, 1};
        int[] result = InsertionSort.insertionSort(arr);
        assertArrayEquals(new int[]{1, 1, 2, 2, 3, 4, 4, 4}, result, "Массив с дубликатами должен быть отсортирован");
    }

    @Test
    void testMixedPositiveNegative() {
        int[] arr = {3, -1, 4, -5, 2, 0, -3};
        int[] result = InsertionSort.insertionSort(arr);
        assertArrayEquals(new int[]{-5, -3, -1, 0, 2, 3, 4}, result, "Массив с положительными и отрицательными числами должен быть отсортирован");
    }

    @Test
    void testAllEqual() {
        int[] arr = {7, 7, 7, 7, 7};
        int[] result = InsertionSort.insertionSort(arr);
        assertArrayEquals(new int[]{7, 7, 7, 7, 7}, result, "Массив из одинаковых элементов не должен измениться");
    }

    @Test
    void testRandomOrder() {
        int[] arr = {64, 34, 25, 12, 22, 11, 90};
        int[] result = InsertionSort.insertionSort(arr);
        assertArrayEquals(new int[]{11, 12, 22, 25, 34, 64, 90}, result, "Случайный массив должен быть отсортирован");
    }
}