package io.neetcode.list;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class MergeKSortedLinkedListsTest {
    @Test
    void testMergeKLists() {
        MergeKSortedLinkedLists merger = new MergeKSortedLinkedLists();
        
        // Создаем списки для тестов
        ListNode l1 = new ListNode(1, new ListNode(4, new ListNode(5)));
        ListNode l2 = new ListNode(1, new ListNode(3, new ListNode(4)));
        ListNode l3 = new ListNode(2, new ListNode(6));

        ListNode[] lists = new ListNode[]{l1, l2, l3};
        
        // Ожидаемый результат после объединения всех списков
        ListNode expected = new ListNode(1, new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4,
                        new ListNode(4, new ListNode(5, new ListNode(6))))))));
        
        // Вызов тестируемого метода
        ListNode result = merger.mergeKLists(lists);

        // Проверка результатов
        while (expected != null && result != null) {
            assertEquals(expected.val, result.val);
            expected = expected.next;
            result = result.next;
        }

        // Проверяем, что оба списка кончились одновременно
        assertNull(expected);
        assertNull(result);
    }

    @Test
    void testEmptyLists() {
        MergeKSortedLinkedLists merger = new MergeKSortedLinkedLists();
        
        // Тестирование пустого массива списков
        ListNode[] emptyLists = new ListNode[]{};
        assertNull(merger.mergeKLists(emptyLists));

        // Тестирование массива, содержащего пустой список
        ListNode[] singleEmptyList = new ListNode[]{null};
        assertNull(merger.mergeKLists(singleEmptyList));
    }
}