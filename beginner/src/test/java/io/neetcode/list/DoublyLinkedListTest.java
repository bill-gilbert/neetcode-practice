package io.neetcode.list;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("DoublyLinkedList")
class DoublyLinkedListTest {

    private DoublyLinkedList list;

    @BeforeEach
    void setUp() {
        // Перед каждым тестом создаём свежий список
        list = new DoublyLinkedList();
    }

    // ===== Вспомогательные методы =====

    /** Превращаем наш список в обычный List<Integer> для удобного сравнения */
    private List<Integer> toList(DoublyLinkedList dll) {
        List<Integer> result = new ArrayList<>();
        DoublyLinkedListNode curr = dll.head.next;
        while (curr != dll.tail) {
            result.add(curr.val);
            curr = curr.next;
        }
        return result;
    }

    /** Проверяем, что ссылки prev/next у всех элементов согласованы */
    private void assertLinksConsistent(DoublyLinkedList dll) {
        DoublyLinkedListNode curr = dll.head;
        while (curr != dll.tail) {
            assertNotNull(curr.next, "next не должен быть null до tail");
            assertEquals(curr, curr.next.prev,
                    "Ссылки prev/next рассинхронизированы на узле со значением " + curr.val);
            curr = curr.next;
        }
        assertEquals(dll.tail, curr, "Должны дойти ровно до tail");
        assertNull(curr.next, "У tail.next должно быть null");
    }

    // ===== ГРУППА ТЕСТОВ: Инициализация =====

    @Nested
    @DisplayName("Инициализация")
    class InitializationTests {

        @Test
        @DisplayName("Новый список пустой")
        void newListIsEmpty() {
            assertTrue(toList(list).isEmpty());
        }

        @Test
        @DisplayName("Head и tail связаны друг с другом")
        void headAndTailAreLinked() {
            assertEquals(list.tail, list.head.next);
            assertEquals(list.head, list.tail.prev);
        }
    }

    // ===== ГРУППА ТЕСТОВ: Вставка =====

    @Nested
    @DisplayName("Вставка")
    class InsertTests {

        @Test
        @DisplayName("insertFront добавляет один элемент в начало")
        void insertFrontSingle() {
            list.insertFront(10);
            assertEquals(List.of(10), toList(list));
            assertLinksConsistent(list);
        }

        @Test
        @DisplayName("insertEnd добавляет один элемент в конец")
        void insertEndSingle() {
            list.insertEnd(20);
            assertEquals(List.of(20), toList(list));
            assertLinksConsistent(list);
        }

        @Test
        @DisplayName("Несколько insertFront выстраивают элементы в обратном порядке")
        void insertFrontMultiple() {
            list.insertFront(1);
            list.insertFront(2);
            list.insertFront(3);
            assertEquals(Arrays.asList(3, 2, 1), toList(list));
            assertLinksConsistent(list);
        }

        @Test
        @DisplayName("Несколько insertEnd выстраивают элементы в прямом порядке")
        void insertEndMultiple() {
            list.insertEnd(1);
            list.insertEnd(2);
            list.insertEnd(3);
            assertEquals(Arrays.asList(1, 2, 3), toList(list));
            assertLinksConsistent(list);
        }

        @Test
        @DisplayName("Смешанные вставки Front и End")
        void mixedInserts() {
            list.insertEnd(1);
            list.insertFront(2);
            list.insertEnd(3);
            list.insertFront(4);
            assertEquals(Arrays.asList(4, 2, 1, 3), toList(list));
            assertLinksConsistent(list);
        }
    }

    // ===== ГРУППА ТЕСТОВ: Удаление =====

    @Nested
    @DisplayName("Удаление")
    class RemoveTests {

        @Test
        @DisplayName("removeFront удаляет единственный элемент")
        void removeFrontSingle() {
            list.insertFront(10);
            list.removeFront();
            assertTrue(toList(list).isEmpty());
            assertLinksConsistent(list);
        }

        @Test
        @DisplayName("removeEnd удаляет единственный элемент")
        void removeEndSingle() {
            list.insertEnd(20);
            list.removeEnd();
            assertTrue(toList(list).isEmpty());
            assertLinksConsistent(list);
        }

        @Test
        @DisplayName("removeFront удаляет первый из нескольких")
        void removeFrontFromMultiple() {
            list.insertEnd(1);
            list.insertEnd(2);
            list.insertEnd(3);
            list.removeFront();
            assertEquals(Arrays.asList(2, 3), toList(list));
            assertLinksConsistent(list);
        }

        @Test
        @DisplayName("removeEnd удаляет последний из нескольких")
        void removeEndFromMultiple() {
            list.insertEnd(1);
            list.insertEnd(2);
            list.insertEnd(3);
            list.removeEnd();
            assertEquals(Arrays.asList(1, 2), toList(list));
            assertLinksConsistent(list);
        }

        @Test
        @DisplayName("Чередование removeFront и removeEnd")
        void alternatingRemoves() {
            for (int i = 1; i <= 5; i++) list.insertEnd(i);
            list.removeFront(); // [2,3,4,5]
            list.removeEnd();   // [2,3,4]
            list.removeFront(); // [3,4]
            assertEquals(Arrays.asList(3, 4), toList(list));
            assertLinksConsistent(list);
        }

        @Test
        @DisplayName("Удаление всех элементов по одному")
        void removeAllElements() {
            list.insertEnd(1);
            list.insertEnd(2);
            list.insertEnd(3);
            list.removeFront();
            list.removeFront();
            list.removeFront();
            assertTrue(toList(list).isEmpty());
            assertLinksConsistent(list);
        }
    }

    // ===== ГРУППА ТЕСТОВ: Граничные случаи =====

    @Nested
    @DisplayName("Граничные случаи")
    class EdgeCaseTests {

        @Test
        @DisplayName("Вставка после полного удаления работает")
        void reinsertAfterEmpty() {
            list.insertEnd(1);
            list.insertEnd(2);
            list.removeFront();
            list.removeEnd();

            list.insertFront(3);
            list.insertEnd(4);

            assertEquals(Arrays.asList(3, 4), toList(list));
            assertLinksConsistent(list);
        }

        @Test
        @DisplayName("removeFront на пустом списке не падает с NPE")
        void removeFrontOnEmptyDoesNotThrow() {
            assertDoesNotThrow(() -> list.removeFront());
        }

        @Test
        @DisplayName("removeEnd на пустом списке не падает с NPE")
        void removeEndOnEmptyDoesNotThrow() {
            assertDoesNotThrow(() -> list.removeEnd());
        }

        @Test
        @DisplayName("Многократные операции не ломают связность")
        void stressTestLinksConsistency() {
            for (int i = 0; i < 100; i++) list.insertEnd(i);
            for (int i = 0; i < 50; i++) list.removeFront();
            for (int i = 0; i < 25; i++) list.removeEnd();
            for (int i = 1000; i < 1050; i++) list.insertFront(i);

            assertEquals(75, toList(list).size());
            assertLinksConsistent(list);
        }
    }
}