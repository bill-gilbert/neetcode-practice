package io.neetcode.list;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MergeTwoSortedLinkedListsTest {

    private MergeTwoSortedLinkedLists merger;

    // ─── Вспомогательные методы ───────────────────────────────

    @BeforeEach
    void setUp() {
        merger = new MergeTwoSortedLinkedLists();
    }

    private ListNode buildList(int... vals) {
        if (vals.length == 0) return null;
        ListNode head = new ListNode(vals[0]);
        ListNode cur = head;
        for (int i = 1; i < vals.length; i++) {
            cur.next = new ListNode(vals[i]);
            cur = cur.next;
        }
        return head;
    }

    private List<Integer> toList(ListNode node) {
        List<Integer> result = new ArrayList<>();
        while (node != null) {
            result.add(node.val);
            node = node.next;
        }
        return result;
    }

    private void assertMerged(int[] l1, int[] l2, int[] expected) {
        ListNode result = merger.mergeTwoLists(buildList(l1), buildList(l2));
        assertEquals(List.of(toArray(expected)), toList(result),
                () -> String.format("merge(%s, %s)",
                        List.of(toArray(l1)), List.of(toArray(l2))));
    }

    // хелпер, потому что List.of(int[]) не работает напрямую
    private Integer[] toArray(int[] arr) {
        Integer[] boxed = new Integer[arr.length];
        for (int i = 0; i < arr.length; i++) boxed[i] = arr[i];
        return boxed;
    }

    // ─── Тесты ────────────────────────────────────────────────

    @Test
    @DisplayName("1. Оба списка пустые → пустой результат")
    void bothEmpty() {
        assertNull(merger.mergeTwoLists(null, null));
    }

    @Test
    @DisplayName("2. Первый пустой, второй нет")
    void firstEmpty() {
        assertMerged(new int[]{}, new int[]{1, 2, 3}, new int[]{1, 2, 3});
    }

    @Test
    @DisplayName("3. Второй пустой, первый нет")
    void secondEmpty() {
        assertMerged(new int[]{1, 2, 3}, new int[]{}, new int[]{1, 2, 3});
    }

    @Test
    @DisplayName("4. По одному элементу, l1 < l2")
    void singleElementsOrdered() {
        assertMerged(new int[]{1}, new int[]{2}, new int[]{1, 2});
    }

    @Test
    @DisplayName("5. По одному элементу, l1 > l2")
    void singleElementsReversed() {
        assertMerged(new int[]{5}, new int[]{3}, new int[]{3, 5});
    }

    @Test
    @DisplayName("6. По одному одинаковому элементу")
    void singleElementsEqual() {
        assertMerged(new int[]{7}, new int[]{7}, new int[]{7, 7});
    }

    @Test
    @DisplayName("7. Классический пример из условия")
    void classicExample() {
        assertMerged(new int[]{1, 2, 4}, new int[]{1, 3, 5}, new int[]{1, 1, 2, 3, 4, 5});
    }

    @Test
    @DisplayName("8. Один список полностью меньше другого")
    void oneListEntirelySmaller() {
        assertMerged(new int[]{1, 2, 3}, new int[]{4, 5, 6}, new int[]{1, 2, 3, 4, 5, 6});
    }

    @Test
    @DisplayName("9. Отрицательные числа")
    void negativeNumbers() {
        assertMerged(new int[]{-5, -3, -1}, new int[]{-4, -2, 0},
                     new int[]{-5, -4, -3, -2, -1, 0});
    }

    @Test
    @DisplayName("10. Много дубликатов")
    void manyDuplicates() {
        assertMerged(new int[]{2, 2, 2}, new int[]{2, 2}, new int[]{2, 2, 2, 2, 2});
    }

    @Test
    @DisplayName("11. Разная длина: длинный + короткий")
    void differentLengths() {
        assertMerged(new int[]{1, 3, 5, 7, 9}, new int[]{2, 4},
                     new int[]{1, 2, 3, 4, 5, 7, 9});
    }

    @Test
    @DisplayName("12. Граничные значения -100 и 100")
    void boundaryValues() {
        assertMerged(new int[]{-100, 0}, new int[]{0, 100},
                     new int[]{-100, 0, 0, 100});
    }
}