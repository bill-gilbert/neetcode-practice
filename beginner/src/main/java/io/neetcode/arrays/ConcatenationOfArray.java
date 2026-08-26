package io.neetcode.arrays;

public class ConcatenationOfArray {
    public int[] getConcatenation(int[] nums) {
        int[] newArr = new int[nums.length * 2];
        for (int ind = 0; ind < nums.length; ind++) {
            newArr[ind] = newArr[nums.length + ind] = nums[ind];
        }
        return newArr;
    }
}
