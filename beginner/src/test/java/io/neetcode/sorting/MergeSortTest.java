package io.neetcode.sorting;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class MergeSortTest {

    @Test
    public void testUnsortedArray() {
        int[] array = {5, 2, 9, 3, 8, 4};
        int[] expected = {2, 3, 4, 5, 8, 9};
        int[] result = MergeSort.mergeSort(array, 0, array.length - 1);
        assertArrayEquals(expected, result);
    }

    @Test
    public void testSortedArray() {
        int[] array = {1, 2, 3, 4, 5};
        int[] expected = {1, 2, 3, 4, 5};
        int[] result = MergeSort.mergeSort(array, 0, array.length - 1);
        assertArrayEquals(expected, result);
    }

    @Test
    public void testSortedArrayCase2() {
        int[] array = {5, 1, 3, 4, 2};
        int[] expected = {1, 2, 3, 4, 5};
        int[] result = MergeSort.mergeSort(array, 0, array.length - 1);
        assertArrayEquals(expected, result);
    }

    @Test
    public void testArrayWithDuplicates() {
        int[] array = {4, 1, 3, 4, 2};
        int[] expected = {1, 2, 3, 4, 4};
        int[] result = MergeSort.mergeSort(array, 0, array.length - 1);
        assertArrayEquals(expected, result);
    }

    @Test
    public void testArrayWithNegativeNumbers() {
        int[] array = {-1, 3, -2, 5, 4};
        int[] expected = {-2, -1, 3, 4, 5};
        int[] result = MergeSort.mergeSort(array, 0, array.length - 1);
        assertArrayEquals(expected, result);
    }

}