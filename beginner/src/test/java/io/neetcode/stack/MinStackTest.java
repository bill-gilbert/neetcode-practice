package io.neetcode.stack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

class MinStackTest {

    private MinStack minStack;

    @BeforeEach
    void setUp() {
        minStack = new MinStack();
    }

    // ==========================================
    // 1. Базовый сценарий из условия задачи
    // ==========================================
    @Test
    @DisplayName("Базовый сценарий из примера LeetCode")
    void testExampleFromDescription() {
        minStack.push(1);
        minStack.push(2);
        minStack.push(0);
        
        assertEquals(0, minStack.getMin(), "Минимум должен быть 0");
        
        minStack.pop();
        assertEquals(2, minStack.top(), "После pop() сверху должен быть 2");
        assertEquals(1, minStack.getMin(), "Минимум должен восстановиться до 1");
    }

    // ==========================================
    // 2. Граничные значения int (самое важное!)
    // ==========================================
    @Test
    @DisplayName("Граничные значения: MAX_VALUE и MIN_VALUE")
    void testIntegerBoundaries() {
        minStack.push(Integer.MAX_VALUE);
        minStack.push(Integer.MIN_VALUE);
        minStack.push(0);
        
        assertEquals(Integer.MIN_VALUE, minStack.getMin());
        assertEquals(0, minStack.top());
        
        minStack.pop();
        assertEquals(Integer.MIN_VALUE, minStack.getMin());
        assertEquals(Integer.MIN_VALUE, minStack.top());
        
        minStack.pop();
        assertEquals(Integer.MAX_VALUE, minStack.getMin());
    }

    @Test
    @DisplayName("Переполнение при вычислении разности: MAX - MIN")
    void testOverflowOnDifference() {
        // Это тот самый случай, который ломает решение с int!
        // Разность MAX_VALUE - MIN_VALUE не влезает в int.
        minStack.push(Integer.MIN_VALUE);
        minStack.push(Integer.MAX_VALUE);
        
        assertEquals(Integer.MIN_VALUE, minStack.getMin());
        assertEquals(Integer.MAX_VALUE, minStack.top());
        
        minStack.pop();
        assertEquals(Integer.MIN_VALUE, minStack.getMin());
        assertEquals(Integer.MIN_VALUE, minStack.top());
    }

    // ==========================================
    // 3. Повторяющиеся минимумы
    // ==========================================
    @Test
    @DisplayName("Несколько одинаковых минимумов")
    void testDuplicateMinimums() {
        minStack.push(2);
        minStack.push(2);
        minStack.push(2);
        
        assertEquals(2, minStack.getMin());
        
        minStack.pop();
        assertEquals(2, minStack.getMin(), "Минимум не должен измениться");
        
        minStack.pop();
        assertEquals(2, minStack.getMin(), "Минимум всё ещё 2");
        
        minStack.pop();
        // Стек пуст, getMin/top не должны вызываться по ТЗ
    }

    // ==========================================
    // 4. Убывающая последовательность
    // ==========================================
    @Test
    @DisplayName("Каждый push обновляет минимум")
    void testDecreasingSequence() {
        minStack.push(5);
        assertEquals(5, minStack.getMin());
        
        minStack.push(3);
        assertEquals(3, minStack.getMin());
        
        minStack.push(1);
        assertEquals(1, minStack.getMin());
        
        minStack.push(-10);
        assertEquals(-10, minStack.getMin());
        
        minStack.pop();
        assertEquals(1, minStack.getMin(), "Восстановление до 1");
        
        minStack.pop();
        assertEquals(3, minStack.getMin(), "Восстановление до 3");
    }

    // ==========================================
    // 5. Возрастающая последовательность
    // ==========================================
    @Test
    @DisplayName("Минимум не меняется при возрастании")
    void testIncreasingSequence() {
        minStack.push(1);
        minStack.push(2);
        minStack.push(3);
        minStack.push(100);
        
        assertEquals(1, minStack.getMin());
        assertEquals(100, minStack.top());
        
        minStack.pop();
        assertEquals(1, minStack.getMin(), "Минимум не изменился");
    }

    // ==========================================
    // 6. Отрицательные числа
    // ==========================================
    @Test
    @DisplayName("Работа с отрицательными числами")
    void testNegativeNumbers() {
        minStack.push(-5);
        minStack.push(-1);
        minStack.push(-10);
        
        assertEquals(-10, minStack.getMin());
        assertEquals(-10, minStack.top());
        
        minStack.pop();
        assertEquals(-5, minStack.getMin());
        assertEquals(-1, minStack.top());
    }

    // ==========================================
    // 7. Смешанные операции
    // ==========================================
    @Test
    @DisplayName("Смешанные push/pop с разными значениями")
    void testMixedOperations() {
        minStack.push(2147483646); // MAX_VALUE - 1
        minStack.push(2147483646);
        minStack.push(2147483647); // MAX_VALUE
        
        assertEquals(2147483646, minStack.getMin());
        assertEquals(2147483647, minStack.top());
        
        minStack.pop();
        assertEquals(2147483646, minStack.getMin());
        assertEquals(2147483646, minStack.top());
    }

    // ==========================================
    // 8. Полное опустошение и повторное заполнение
    // ==========================================
    @Test
    @DisplayName("Опустошение стека и работа с новым состоянием")
    void testEmptyAndRefill() {
        minStack.push(10);
        minStack.push(5);
        minStack.push(20);
        
        minStack.pop();
        minStack.pop();
        minStack.pop();
        
        // Стек снова пуст — добавляем новые значения
        minStack.push(100);
        minStack.push(-50);
        
        assertEquals(-50, minStack.getMin());
        assertEquals(-50, minStack.top());
    }

    // ==========================================
    // 9. Один элемент
    // ==========================================
    @Test
    @DisplayName("Стек с одним элементом")
    void testSingleElement() {
        minStack.push(42);
        
        assertEquals(42, minStack.top());
        assertEquals(42, minStack.getMin());
        
        minStack.pop();
        // assertTrue(minStack.isEmpty(), "Стек должен быть пуст");
    }

    // ==========================================
    // 10. Стресс-тест: много операций
    // ==========================================
    @Test
    @DisplayName("Большое количество операций")
    void testManyOperations() {
        int n = 10000;
        for (int i = 0; i < n; i++) {
            minStack.push(i);
        }
        assertEquals(0, minStack.getMin());
        assertEquals(n - 1, minStack.top());
        
        for (int i = 0; i < n / 2; i++) {
            minStack.pop();
        }
        assertEquals(0, minStack.getMin());
        assertEquals(n / 2 - 1, minStack.top());
    }
}