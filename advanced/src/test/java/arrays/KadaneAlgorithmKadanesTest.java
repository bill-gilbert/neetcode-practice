package arrays;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class KadaneAlgorithmKadanesTest {

    @Test
    public void testAllPositiveNumbers() {
        int[] nums = {1, 2, 3, 4};
        assertEquals(10, KadaneAlgorithm.kadanes(nums));
    }

    @Test
    public void testAllNegativeNumbers() {
        int[] nums = {-3, -5, -2, -9};
        assertEquals(-2, KadaneAlgorithm.kadanes(nums));
    }

    @Test
    public void testMixedNumbers() {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        assertEquals(6, KadaneAlgorithm.kadanes(nums));
    }

    @Test
    public void testSinglePositiveElement() {
        int[] nums = {5};
        assertEquals(5, KadaneAlgorithm.kadanes(nums));
    }

    @Test
    public void testSingleNegativeElement() {
        int[] nums = {-7};
        assertEquals(-7, KadaneAlgorithm.kadanes(nums));
    }

    @Test
    public void testWithZeros() {
        int[] nums = {0, 0, 0};
        assertEquals(0, KadaneAlgorithm.kadanes(nums));
    }
}