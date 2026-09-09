package io.neetcode.list;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BrowserHistoryTest {

    private BrowserHistory browserHistory;

    @BeforeEach
    void setUp() {
        // Базовая инициализация перед каждым тестом, если нужна
        browserHistory = new BrowserHistory("neetcode.com");
    }

    @Test
    void testLeetCodeExample() {
        // Это основной сценарий из условия задачи
        browserHistory.visit("google.com");
        browserHistory.visit("facebook.com");
        browserHistory.visit("youtube.com");

        assertEquals("facebook.com", browserHistory.back(1));
        assertEquals("google.com", browserHistory.back(1));
        assertEquals("facebook.com", browserHistory.forward(1));

        browserHistory.visit("linkedin.com");

        assertEquals("linkedin.com", browserHistory.forward(2)); // Будущее стерто, стоим на месте
        assertEquals("google.com", browserHistory.back(2));
        assertEquals("neetcode.com", browserHistory.back(7));    // Упираемся в самый начало
    }

    @Test
    void testVisitAfterBackErasesFuture() {
        // Самый важный тест! Проверяет "стирание будущего"
        BrowserHistory bh = new BrowserHistory("A.com");
        bh.visit("B.com");
        bh.visit("C.com");

        assertEquals("B.com", bh.back(1)); // Вернулись на B

        // Переходим на новую страницу. "C.com" должен быть безжалостно удален
        bh.visit("D.com");

        // Пытаемся пойти вперед. Мы должны остаться на "D.com", а не попасть на "C.com"
        assertEquals("D.com", bh.forward(10));
        assertEquals("B.com", bh.back(1));
        assertEquals("A.com", bh.back(1));
    }

    @Test
    void testExtremeStepsBoundaries() {
        // Проверяем, что код не упадет с IndexOutOfBoundsException при огромных шагах
        browserHistory.visit("page1.com");
        browserHistory.visit("page2.com");

        // Пытаемся уйти назад дальше, чем позволяет история (всего 2 шага назад доступно)
        assertEquals("neetcode.com", browserHistory.back(100));

        // Пытаемся уйти вперед дальше, чем есть в истории (всего 2 шага вперед доступно)
        assertEquals("page2.com", browserHistory.forward(100));
    }

    @Test
    void testMultipleVisitsWithoutBack() {
        // Проверяем, что обычный рост истории работает корректно без стираний
        browserHistory.visit("1.com"); // индекс 1
        browserHistory.visit("2.com"); // индекс 2
        browserHistory.visit("3.com"); // индекс 3

        // С индекса 3 уходим на 3 шага назад -> попадаем в индекс 0
        assertEquals("neetcode.com", browserHistory.back(3));

        // С индекса 0 уходим на 2 шага вперед -> попадаем в индекс 2
        assertEquals("2.com", browserHistory.forward(2));
    }
}
