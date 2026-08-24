package io.neetcode.arrays;

import java.util.HashSet;

public class ContainsDuplicate {
    public boolean hasDuplicate(int[] nums) {
        // 1. Защита от краша и очевидный случай
        if (nums == null || nums.length <= 1) {
            return false;
        }

        // 2. Инициализация (можно указать initial capacity, если ожидаешь большой массив,
        // но для интервью обычно пишут просто new HashSet<>())
        HashSet<Integer> seen = new HashSet<>();

        // 3. Один проход с ранним выходом
        for (int num : nums) {
            if (!seen.add(num)) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        var arr = new int[] { 1, 2, 3, 4, 5 };

        System.out.println(new ContainsDuplicate().hasDuplicate(arr));
    }
}
