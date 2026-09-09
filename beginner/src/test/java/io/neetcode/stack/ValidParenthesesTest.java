package io.neetcode.stack;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ValidParenthesesTest {

    private final ValidParentheses solution = new ValidParentheses();

    // --- Параметризованный тест: все 10 случаев из предыдущего ответа ---
    @ParameterizedTest(name = "Строка \"{0}\" → {1}")
    @CsvSource({
        "'()',             true",
        "'()[]{}',         true",
        "'(]',             false",
        "'([)]',           false",
        "'(((',            false",
        "')))',            false",
        "'{[]}',           true",
        "'(((((())))))',   true",
        "'((())',          false",
        "'{[()()]}',       true"
    })
    @DisplayName("Параметризованный тест: базовые и типовые случаи")
    void testCommonCases(String input, boolean expected) {
        assertEquals(expected, solution.isValid(input),
                () -> "Ожидалось " + expected + " для строки: " + input);
    }

    // --- Граничные случаи ---
    @Test
    @DisplayName("Строка из одного символа всегда невалидна")
    void testSingleCharacter() {
        assertFalse(solution.isValid("("));
        assertFalse(solution.isValid(")"));
        assertFalse(solution.isValid("["));
    }

    @Test
    @DisplayName("Пустая строка считается валидной (стек пуст)")
    void testEmptyString() {
        assertTrue(solution.isValid(""));
    }

    @Test
    @DisplayName("Глубокая вложенность скобок")
    void testDeepNesting() {
        String deep = "((((((((((()))))))))))";
        assertTrue(solution.isValid(deep));
    }

    @Test
    @DisplayName("Много разных типов скобок подряд")
    void testManyPairs() {
        assertTrue(solution.isValid("()()()()(){}{}{}{}{}[][][][][]"));
    }

    @Test
    @DisplayName("Закрывающая скобка без открывающей в начале")
    void testClosingWithoutOpening() {
        assertFalse(solution.isValid(")()"));
        assertFalse(solution.isValid("}{}"));
    }

    @Test
    @DisplayName("Открывающая скобка без закрывающей в конце")
    void testOpeningWithoutClosing() {
        assertFalse(solution.isValid("()("));
        assertFalse(solution.isValid("(()"));
    }

    @Test
    @DisplayName("Максимальная длина строки (1000 символов)")
    void testMaxLength() {
        // 500 пар скобок = 1000 символов
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 500; i++) sb.append("()");
        assertTrue(solution.isValid(sb.toString()));
    }

    @Test
    @DisplayName("Перемешанные валидные пары")
    void testMixedValidPairs() {
        assertTrue(solution.isValid("{[()]}{[()]}{[()]}"));
    }

    @Test
    @DisplayName("Невалидная строка с мусором в конце")
    void testInvalidTrailing() {
        assertFalse(solution.isValid("()[]{}("));
    }
}