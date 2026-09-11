package arrays;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class KadaneAlgorithmSlidingWindowTest {

    @Test
    public void testAllPositiveNumbers() {
        int[] nums = {1, 2, 3, 4};
        int[] result = KadaneAlgorithm.slidingWindow(nums);
        assertArrayEquals(new int[]{0, 3}, result);
        assertEquals(10, calculateSum(nums, result[0], result[1]));
    }

    @Test
    public void testAllNegativeNumbers() {
        int[] nums = {-3, -5, -2, -9};
        int[] result = KadaneAlgorithm.slidingWindow(nums);
        assertArrayEquals(new int[]{2, 2}, result); // -2 находится на индексе 2
        assertEquals(-2, calculateSum(nums, result[0], result[1]));
    }

    @Test
    public void testMixedNumbers() {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int[] result = KadaneAlgorithm.slidingWindow(nums);
        assertArrayEquals(new int[]{3, 6}, result); // Подмассив [4, -1, 2, 1]
        assertEquals(6, calculateSum(nums, result[0], result[1]));
    }

    @Test
    public void testSinglePositiveElement() {
        int[] nums = {5};
        int[] result = KadaneAlgorithm.slidingWindow(nums);
        assertArrayEquals(new int[]{0, 0}, result);
        assertEquals(5, calculateSum(nums, result[0], result[1]));
    }

    @Test
    public void testSingleNegativeElement() {
        int[] nums = {-7};
        int[] result = KadaneAlgorithm.slidingWindow(nums);
        assertArrayEquals(new int[]{0, 0}, result);
        assertEquals(-7, calculateSum(nums, result[0], result[1]));
    }

    @Test
    public void testWithZeros() {
        int[] nums = {0, 0, 0};
        int[] result = KadaneAlgorithm.slidingWindow(nums);
        assertArrayEquals(new int[]{0, 0}, result);
        assertEquals(0, calculateSum(nums, result[0], result[1]));
    }

    // Вспомогательный метод для проверки корректности найденных индексов
    private int calculateSum(int[] nums, int start, int end) {
        int sum = 0;
        for (int i = start; i <= end; i++) {
            sum += nums[i];
        }
        return sum;
    }
}