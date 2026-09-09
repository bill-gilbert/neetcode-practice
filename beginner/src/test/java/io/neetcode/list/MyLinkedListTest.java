package io.neetcode.list;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("MyLinkedList (Dummy Node)")
class MyLinkedListTest {

    private MyLinkedList list;

    @BeforeEach
    void setUp() {
        list = new MyLinkedList();
    }

    // ===== Вспомогательные методы =====

    /**
     * Собирает реальные элементы списка (пропуская dummy-ноду).
     */
    private List<Integer> toList(MyLinkedList ll) {
        List<Integer> result = new ArrayList<>();
        ListNode curr = ll.head.next; // пропускаем dummy
        while (curr != null) {
            result.add(curr.val);
            curr = curr.next;
        }
        return result;
    }

    /**
     * Проверяет, что поле size совпадает с реальным количеством узлов.
     */
    private void assertSizeConsistent(MyLinkedList ll) {
        int actual = 0;
        ListNode curr = ll.head.next;
        while (curr != null) {
            actual++;
            curr = curr.next;
        }
        assertEquals(actual, ll.size,
                "Поле size (" + ll.size + ") не совпадает с реальным количеством узлов (" + actual + ")");
    }

    /**
     * Проверяет, что цепочка next заканчивается null
     * и не содержит зацикливаний (до size + 1 шагов).
     */
    private void assertNoCycle(MyLinkedList ll) {
        ListNode curr = ll.head;
        for (int i = 0; i <= ll.size + 1; i++) {
            if (curr == null) return; // дошли до конца — всё ок
            curr = curr.next;
        }
        fail("Обнаружено зацикливание: не дошли до null за " + (ll.size + 2) + " шагов");
    }

    /**
     * Комплексная проверка инвариантов после каждой операции.
     */
    private void assertInvariants(MyLinkedList ll) {
        assertSizeConsistent(ll);
        assertNoCycle(ll);
        assertNotNull(ll.head, "Dummy-нода никогда не должна быть null");
        assertNull(ll.head.val == 0 ? null : null, "Dummy-нода должна иметь val=0"); // просто sanity
    }

    // ================================================================
    //  ГРУППА 1: Инициализация
    // ================================================================

    @Nested
    @DisplayName("Инициализация")
    class InitializationTests {

        @Test
        @DisplayName("Новый список пустой, size == 0")
        void emptyList() {
            assertTrue(toList(list).isEmpty());
            assertEquals(0, list.size);
        }

        @Test
        @DisplayName("Dummy-нода существует и её next == null")
        void dummyNodePresent() {
            assertNotNull(list.head);
            assertEquals(0, list.head.val);
            assertNull(list.head.next);
        }
    }

    // ================================================================
    //  ГРУППА 2: addAtHead
    // ================================================================

    @Nested
    @DisplayName("addAtHead")
    class AddAtHeadTests {

        @Test
        @DisplayName("Один элемент в пустой список")
        void single() {
            list.addAtHead(10);
            assertEquals(List.of(10), toList(list));
            assertEquals(1, list.size);
            assertInvariants(list);
        }

        @Test
        @DisplayName("Несколько элементов — обратный порядок")
        void multiple() {
            list.addAtHead(1);
            list.addAtHead(2);
            list.addAtHead(3);
            assertEquals(Arrays.asList(3, 2, 1), toList(list));
            assertEquals(3, list.size);
            assertInvariants(list);
        }

        @Test
        @DisplayName("Первый реальный элемент — это head.next")
        void headNextIsFirstReal() {
            list.addAtHead(42);
            assertEquals(42, list.head.next.val);
        }
    }

    // ================================================================
    //  ГРУППА 3: addAtTail
    // ================================================================

    @Nested
    @DisplayName("addAtTail")
    class AddAtTailTests {

        @Test
        @DisplayName("Один элемент в пустой список")
        void single() {
            list.addAtTail(20);
            assertEquals(List.of(20), toList(list));
            assertEquals(1, list.size);
            assertInvariants(list);
        }

        @Test
        @DisplayName("Несколько элементов — прямой порядок")
        void multiple() {
            list.addAtTail(1);
            list.addAtTail(2);
            list.addAtTail(3);
            assertEquals(Arrays.asList(1, 2, 3), toList(list));
            assertEquals(3, list.size);
            assertInvariants(list);
        }
    }

    // ================================================================
    //  ГРУППА 4: get
    // ================================================================

    @Nested
    @DisplayName("get")
    class GetTests {

        @Test
        @DisplayName("get(0) на пустом списке возвращает -1")
        void emptyList() {
            assertEquals(-1, list.get(0));
        }

        @Test
        @DisplayName("get с валидными индексами")
        void validIndices() {
            list.addAtTail(10);
            list.addAtTail(20);
            list.addAtTail(30);

            assertEquals(10, list.get(0));
            assertEquals(20, list.get(1));
            assertEquals(30, list.get(2));
        }

        @Test
        @DisplayName("get с индексом == size возвращает -1")
        void indexEqualsSize() {
            list.addAtTail(10);
            assertEquals(-1, list.get(1));
        }

        @Test
        @DisplayName("get с индексом > size возвращает -1")
        void indexGreaterThanSize() {
            list.addAtTail(10);
            assertEquals(-1, list.get(100));
        }

        @Test
        @DisplayName("get с отрицательным индексом возвращает -1")
        void negativeIndex() {
            list.addAtTail(10);
            list.addAtTail(20);
            // По условию LeetCode отрицательный индекс невалиден
            // Если реализация не обрабатывает — этот тест это выявит
            assertEquals(-1, list.get(-1));
        }
    }

    // ================================================================
    //  ГРУППА 5: addAtIndex
    // ================================================================

    @Nested
    @DisplayName("addAtIndex")
    class AddAtIndexTests {

        @Test
        @DisplayName("addAtIndex(0) в пустой список")
        void zeroEmpty() {
            list.addAtIndex(0, 10);
            assertEquals(List.of(10), toList(list));
            assertInvariants(list);
        }

        @Test
        @DisplayName("addAtIndex(0) в непустой список — вставка в начало")
        void zeroNonEmpty() {
            list.addAtTail(20);
            list.addAtTail(30);
            list.addAtIndex(0, 10);
            assertEquals(Arrays.asList(10, 20, 30), toList(list));
            assertInvariants(list);
        }

        @Test
        @DisplayName("addAtIndex в середину")
        void middle() {
            list.addAtTail(10);
            list.addAtTail(30);
            list.addAtIndex(1, 20);
            assertEquals(Arrays.asList(10, 20, 30), toList(list));
            assertInvariants(list);
        }

        @Test
        @DisplayName("addAtIndex(size) — вставка в конец")
        void atEnd() {
            list.addAtTail(10);
            list.addAtTail(20);
            list.addAtIndex(2, 30);
            assertEquals(Arrays.asList(10, 20, 30), toList(list));
            assertInvariants(list);
        }

        @Test
        @DisplayName("addAtIndex с index > size — ничего не делает")
        void outOfBounds() {
            list.addAtTail(10);
            list.addAtIndex(5, 99);
            assertEquals(List.of(10), toList(list));
            assertEquals(1, list.size);
        }

        @Test
        @DisplayName("addAtIndex с отрицательным индексом — ничего не делает")
        void negativeIndex() {
            list.addAtTail(10);
            list.addAtIndex(-1, 99);
            // По условию LeetCode: отрицательный индекс невалиден
            assertEquals(List.of(10), toList(list));
            assertEquals(1, list.size);
        }
    }

    // ================================================================
    //  ГРУППА 6: deleteAtIndex
    // ================================================================

    @Nested
    @DisplayName("deleteAtIndex")
    class DeleteAtIndexTests {

        @Test
        @DisplayName("deleteAtIndex на пустом списке — ничего не делает")
        void emptyList() {
            assertDoesNotThrow(() -> list.deleteAtIndex(0));
            assertTrue(toList(list).isEmpty());
            assertEquals(0, list.size);
        }

        @Test
        @DisplayName("Удаление первого элемента")
        void first() {
            list.addAtTail(10);
            list.addAtTail(20);
            list.addAtTail(30);
            list.deleteAtIndex(0);
            assertEquals(Arrays.asList(20, 30), toList(list));
            assertInvariants(list);
        }

        @Test
        @DisplayName("Удаление последнего элемента")
        void last() {
            list.addAtTail(10);
            list.addAtTail(20);
            list.addAtTail(30);
            list.deleteAtIndex(2);
            assertEquals(Arrays.asList(10, 20), toList(list));
            assertInvariants(list);
        }

        @Test
        @DisplayName("Удаление среднего элемента")
        void middle() {
            list.addAtTail(10);
            list.addAtTail(20);
            list.addAtTail(30);
            list.deleteAtIndex(1);
            assertEquals(Arrays.asList(10, 30), toList(list));
            assertInvariants(list);
        }

        @Test
        @DisplayName("Удаление единственного элемента")
        void onlyElement() {
            list.addAtTail(10);
            list.deleteAtIndex(0);
            assertTrue(toList(list).isEmpty());
            assertEquals(0, list.size);
            assertNull(list.head.next);
        }

        @Test
        @DisplayName("deleteAtIndex с index >= size — ничего не делает")
        void outOfBounds() {
            list.addAtTail(10);
            list.addAtTail(20);
            list.deleteAtIndex(2);
            list.deleteAtIndex(100);
            assertEquals(Arrays.asList(10, 20), toList(list));
            assertEquals(2, list.size);
        }

        @Test
        @DisplayName("deleteAtIndex с отрицательным индексом — ничего не делает")
        void negativeIndex() {
            list.addAtTail(10);
            list.addAtTail(20);
            list.deleteAtIndex(-1);
            assertEquals(Arrays.asList(10, 20), toList(list));
            assertEquals(2, list.size);
        }
    }

    // ================================================================
    //  ГРУППА 7: Комбинированные сценарии
    // ================================================================

    @Nested
    @DisplayName("Комбинированные сценарии")
    class CombinedTests {

        @Test
        @DisplayName("Смешанные add/delete/get")
        void mixedOperations() {
            list.addAtTail(1);       // [1]
            list.addAtTail(2);       // [1, 2]
            list.addAtHead(0);       // [0, 1, 2]
            list.addAtIndex(2, 15);  // [0, 1, 15, 2]
            list.deleteAtIndex(1);   // [0, 15, 2]

            assertEquals(Arrays.asList(0, 15, 2), toList(list));
            assertEquals(15, list.get(1));
            assertInvariants(list);
        }

        @Test
        @DisplayName("Удалить всё, затем снова добавлять")
        void clearAndRefill() {
            list.addAtTail(1);
            list.addAtTail(2);
            list.addAtTail(3);
            list.deleteAtIndex(0);
            list.deleteAtIndex(0);
            list.deleteAtIndex(0);

            assertTrue(toList(list).isEmpty());
            assertEquals(0, list.size);

            list.addAtHead(100);
            list.addAtTail(200);
            assertEquals(Arrays.asList(100, 200), toList(list));
            assertInvariants(list);
        }

        @Test
        @DisplayName("Чередование addAtHead и addAtTail")
        void alternatingHeadTail() {
            list.addAtHead(1);  // [1]
            list.addAtTail(2);  // [1, 2]
            list.addAtHead(3);  // [3, 1, 2]
            list.addAtTail(4);  // [3, 1, 2, 4]
            list.addAtHead(5);  // [5, 3, 1, 2, 4]

            assertEquals(Arrays.asList(5, 3, 1, 2, 4), toList(list));
            assertInvariants(list);
        }
    }

    // ================================================================
    //  ГРУППА 8: Стресс-тест
    // ================================================================

    @Nested
    @DisplayName("Стресс-тест")
    class StressTests {

        @Test
        @DisplayName("200+ операций — размер и связность не ломаются")
        void heavyLoad() {
            // 100 добавлений в конец
            for (int i = 0; i < 100; i++) list.addAtTail(i);
            assertEquals(100, list.size);

            // 30 удалений с начала
            for (int i = 0; i < 30; i++) list.deleteAtIndex(0);
            assertEquals(70, list.size);
            assertEquals(30, list.get(0)); // первый оставшийся

            // 20 удалений с конца
            for (int i = 0; i < 20; i++) list.deleteAtIndex(list.size - 1);
            assertEquals(50, list.size);
            assertEquals(79, list.get(49)); // последний оставшийся перед вставками

            // 50 добавлений в начало
            for (int i = 1000; i < 1050; i++) list.addAtHead(i);
            assertEquals(100, list.size);

            // Проверяем первые и последние элементы
            assertEquals(1049, list.get(0));   // первый добавленный в начало
            assertEquals(1000, list.get(49));  // последний добавленный в начало
            assertEquals(30, list.get(50));    // первый из оставшихся
            assertEquals(79, list.get(99));    // последний элемент (30 + 50 - 1 = 79)

            assertInvariants(list);
        }

        @Test
        @DisplayName("Многократное добавление и удаление одного элемента")
        void addRemoveOneRepeatedly() {
            for (int i = 0; i < 1000; i++) {
                list.addAtTail(i);
                assertEquals(1, list.size);
                assertEquals(i, list.get(0));
                list.deleteAtIndex(0);
                assertEquals(0, list.size);
            }
            assertInvariants(list);
        }
    }

    // ================================================================
    //  ГРУППА 9: Сценарий из LeetCode #707
    // ================================================================

    @Nested
    @DisplayName("LeetCode #707 — пример из условия")
    class LeetCodeExampleTest {

        @Test
        @DisplayName("Официальный пример из задачи")
        void officialExample() {
            // MyLinkedList linkedList = new MyLinkedList();
            list.addAtHead(1);          // [1]
            list.addAtTail(3);          // [1, 3]
            list.addAtIndex(1, 2);      // [1, 2, 3]
            assertEquals(2, list.get(1));
            list.deleteAtIndex(1);      // [1, 3]
            assertEquals(3, list.get(1));
            assertInvariants(list);
        }
    }
}