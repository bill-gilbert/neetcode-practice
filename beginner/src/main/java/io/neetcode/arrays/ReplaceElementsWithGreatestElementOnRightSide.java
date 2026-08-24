package io.neetcode.arrays;

/**
 * Easy Topics Company Tags
 *
 * You are given an array arr, replace every element in that array with the greatest element among the
 * elements to its right, and replace the last element with -1.
 *
 *  After doing so, return the array.
 */
public class ReplaceElementsWithGreatestElementOnRightSide {
    public int[] replaceElements(int[] arr) {
        if (arr == null || arr.length == 0) {
            return new int[0];
        }

        int maxForRight = arr[arr.length - 1];
        for (int i = arr.length - 1; i > 0; i--) {
            int elem = arr[i - 1];
            arr[i - 1] = maxForRight;
            maxForRight = Math.max(maxForRight, elem);
        }
        arr[arr.length - 1] = -1;

        return arr;
    }
}
