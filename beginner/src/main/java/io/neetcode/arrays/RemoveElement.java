package io.neetcode.arrays;

/**
 * Given an integer array nums and an integer val, remove all occurrences of val in nums
 * in-place. The order of the elements may be changed. Then return the number of elements
 * in nums which are not equal to val.
 * <p>
 * Consider the number of elements in nums which are not equal to val be k,
 * to get accepted, you need to do the following things:
 * <p>
 * Change the array nums such that the first k elements of nums contain the elements
 * which are not equal to val. The remaining elements of nums are not important as
 * well as the size of nums.
 * <p>
 * Return k.
 *
 *
 */
public class RemoveElement {
    /**
     * Начальное решение
     *
     * @param nums
     * @param val
     * @return
     */

    public int removeElementV2(int[] nums, int val) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int currentIndex = 0;
        int nextIndex = 0;
        while (nextIndex < nums.length) {
            if (nums[nextIndex] != val) {
                nums[currentIndex++] = nums[nextIndex++];
            } else {
                while (nextIndex < nums.length && nums[nextIndex] == val) {
                    ++nextIndex;
                }
                if (nextIndex < nums.length) {
                    nums[currentIndex++] = nums[nextIndex++];
                }
            }

        }

        return currentIndex;
    }

    /**
     * Финальное решение
     *
     * @param nums
     * @param val
     * @return
     */
    public int removeElement(int[] nums, int val) {
        if (nums == null) return 0; // length == 0 обработается циклом автоматически
        int k = 0; // Это твой currentIndex
        for (int i = 0; i < nums.length; i++) { // Это твой nextIndex
            if (nums[i] != val) {
                nums[k++] = nums[i];
            }
            // Если nums[i] == val, мы просто ничего не делаем.
            // i увеличится на следующей итерации, а k останется ждать "хороший" элемент.
        }

        return k;
    }
}