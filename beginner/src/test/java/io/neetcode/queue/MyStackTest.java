package io.neetcode.queue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ImplementStackUsingQueuesTest {

    private ImplementStackUsingQueues stack;

    @BeforeEach
    void setUp() {
        // Инициализация нового стека перед каждым тестом
        stack = new ImplementStackUsingQueues();
    }

    @Test
    void testInitiallyEmpty() {
        assertTrue(stack.empty(), "Новый стек должен быть пустым");
    }

    @Test
    void testPushAndTop() {
        stack.push(1);
        assertEquals(1, stack.top(), "Вершина стека должна быть 1");
        assertFalse(stack.empty(), "Стек не должен быть пустым после push");
        
        stack.push(2);
        assertEquals(2, stack.top(), "Вершина стека должна обновиться до 2");
    }

    @Test
    void testPushAndPopLIFO() {
        stack.push(1);
        stack.push(2);
        stack.push(3);

        assertEquals(3, stack.pop(), "Первый pop должен вернуть 3");
        assertEquals(2, stack.pop(), "Второй pop должен вернуть 2");
        assertEquals(1, stack.pop(), "Третий pop должен вернуть 1");
        assertTrue(stack.empty(), "Стек должен стать пустым после всех pop");
    }

    @Test
    void testInterleavedOperations() {
        stack.push(1);
        stack.push(2);
        assertEquals(2, stack.pop(), "Pop после двух push");
        
        stack.push(3);
        stack.push(4);
        assertEquals(4, stack.top(), "Top должен вернуть 4");
        
        assertEquals(4, stack.pop(), "Pop должен вернуть 4");
        assertEquals(3, stack.pop(), "Pop должен вернуть 3");
        assertEquals(2, stack.pop(), "Pop должен вернуть 2 (тот, что остался от первой партии)");
        assertEquals(1, stack.pop(), "Pop должен вернуть 1");
        
        assertTrue(stack.empty(), "Стек должен быть пуст в конце");
    }

    @Test
    void testDuplicateElements() {
        stack.push(5);
        stack.push(5);
        stack.push(5);

        assertEquals(5, stack.pop());
        assertEquals(5, stack.pop());
        assertEquals(5, stack.pop());
        assertTrue(stack.empty());
    }

    @Test
    void testEdgeIntegerValues() {
        stack.push(Integer.MAX_VALUE);
        stack.push(Integer.MIN_VALUE);
        stack.push(0);
        stack.push(-1);

        assertEquals(-1, stack.pop());
        assertEquals(0, stack.pop());
        assertEquals(Integer.MIN_VALUE, stack.pop());
        assertEquals(Integer.MAX_VALUE, stack.pop());
    }

    @Test
    void testLargeNumberOfElements() {
        int n = 1000;
        for (int i = 0; i < n; i++) {
            stack.push(i);
        }

        assertFalse(stack.empty());
        assertEquals(n - 1, stack.top());

        for (int i = n - 1; i >= 0; i--) {
            assertEquals(i, stack.pop(), "Ошибка на элементе " + i);
        }

        assertTrue(stack.empty());
    }
    
    // Примечание: этот тест демонстрирует поведение при некорректном использовании 
    // (вызов pop на пустом стеке). В задачах LeetCode это обычно не тестируется, 
    // но в реальном коде важно знать о таком поведении.
    @Test
    void testPopOnEmptyStackThrowsException() {
        // q.poll() возвращает null, а при авто-распаковке (unboxing) в int 
        // выбрасывается NullPointerException.
        assertThrows(NullPointerException.class, () -> {
            stack.pop();
        });
    }
}