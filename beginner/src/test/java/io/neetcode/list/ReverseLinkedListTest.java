package io.neetcode.list;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ReverseLinkedListTest {

    private final ReverseLinkedList solution = new ReverseLinkedList();

    // --- Вспомогательные методы ---

    // Создает связный список из массива чисел
    private ListNode createList(int... values) {
        if (values.length == 0) return null;
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        for (int val : values) {
            current.next = new ListNode(val);
            current = current.next;
        }
        return dummy.next;
    }

    // Преобразует связный список в массив для сравнения
    private int[] listToArray(ListNode head) {
        java.util.List<Integer> result = new java.util.ArrayList<>();
        while (head != null) {
            result.add(head.val);
            head = head.next;
        }
        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    // Метод для удобной проверки
    private void assertListReversed(int[] input, int[] expected) {
        ListNode head = createList(input);
        ListNode reversedHead = solution.reverseList(head);
        assertArrayEquals(expected, listToArray(reversedHead));
    }

    // --- Тест-кейсы ---

    @Test
    void testNullList() {
        // Пустой список (null)
        assertNull(solution.reverseList(null));
    }

    @Test
    void testSingleNode() {
        // Список из одного элемента
        assertListReversed(new int[]{1}, new int[]{1});
    }

    @Test
    void testTwoNodes() {
        // Список из двух элементов
        assertListReversed(new int[]{1, 2}, new int[]{2, 1});
    }

    @Test
    void testMultipleNodesOdd() {
        // Нечетное количество элементов
        assertListReversed(new int[]{1, 2, 3, 4, 5}, new int[]{5, 4, 3, 2, 1});
    }

    @Test
    void testMultipleNodesEven() {
        // Четное количество элементов
        assertListReversed(new int[]{1, 2, 3, 4}, new int[]{4, 3, 2, 1});
    }

    @Test
    void testDuplicateValues() {
        // Список с дубликатами
        assertListReversed(new int[]{1, 1, 2, 2, 3}, new int[]{3, 2, 2, 1, 1});
    }

    @Test
    void testNegativeValues() {
        // Список с отрицательными числами и нулем
        assertListReversed(new int[]{-1, 0, 1, -5}, new int[]{-5, 1, 0, -1});
    }

    @Test
    void testLargeList() {
        // Большой список (проверка на отсутствие StackOverflow и корректность работы циклов)
        int size = 10000;
        int[] input = new int[size];
        int[] expected = new int[size];
        for (int i = 0; i < size; i++) {
            input[i] = i;
            expected[size - 1 - i] = i;
        }
        assertListReversed(input, expected);
    }
}
