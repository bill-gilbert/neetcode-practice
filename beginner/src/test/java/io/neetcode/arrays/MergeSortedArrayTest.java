package io.neetcode.arrays;

import io.neetcode.sorting.MergeSortedArray;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MergeSortedArrayTest {

    @Test
    public void testMerge() {
        MergeSortedArray merger = new MergeSortedArray();
        int[] nums1 = {1, 2, 3, 0, 0, 0};
        int[] nums2 = {2, 5, 6};
        merger.merge(nums1, 3, nums2, 3);
        Assertions.assertArrayEquals(new int[]{1, 2, 2, 3, 5, 6}, nums1);
    }

    @Test
    public void testMergeEmptyNums2() {
        MergeSortedArray merger = new MergeSortedArray();
        int[] nums1 = {1};
        int[] nums2 = {};
        merger.merge(nums1, 1, nums2, 0);
        Assertions.assertArrayEquals(new int[]{1}, nums1);
    }

    @Test
    public void testMergeEmptyNums1() {
        MergeSortedArray merger = new MergeSortedArray();
        int[] nums1 = {0};
        int[] nums2 = {1};
        merger.merge(nums1, 0, nums2, 1);
        Assertions.assertArrayEquals(new int[]{1}, nums1);
    }

    @Test
    public void testMergeBothEmpty() {
        MergeSortedArray merger = new MergeSortedArray();
        int[] nums1 = {};
        int[] nums2 = {};
        merger.merge(nums1, 0, nums2, 0);
        Assertions.assertArrayEquals(new int[]{}, nums1);
    }
}