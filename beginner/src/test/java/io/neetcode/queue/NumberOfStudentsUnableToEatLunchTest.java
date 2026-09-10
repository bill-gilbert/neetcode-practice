package io.neetcode.queue;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class NumberOfStudentsUnableToEatLunchTest {

    private final NumberOfStudentsUnableToEatLunch solution = new NumberOfStudentsUnableToEatLunch();

    @Test
    void testExample1_AllStudentsCanEat() {
        int[] students = {1, 1, 0, 0};
        int[] sandwiches = {0, 1, 0, 1};
        assertEquals(0, solution.countStudents(students, sandwiches));
    }

    @Test
    void testExample2_SomeStudentsCannotEat() {
        int[] students = {1, 1, 1, 0, 0, 1};
        int[] sandwiches = {1, 0, 0, 0, 1, 1};
        assertEquals(3, solution.countStudents(students, sandwiches));
    }

    @Test
    void testNoOneWantsTheFirstSandwich() {
        // Все студенты хотят 0, а первый сэндвич 1. Процесс остановится сразу.
        int[] students = {0, 0, 0, 0};
        int[] sandwiches = {1, 1, 1, 1};
        assertEquals(4, solution.countStudents(students, sandwiches));
    }

    @Test
    void testAllStudentsWantSameAndSandwichesMatch() {
        int[] students = {1, 1, 1, 1};
        int[] sandwiches = {1, 1, 1, 1};
        assertEquals(0, solution.countStudents(students, sandwiches));
    }

    @Test
    void testSingleStudentMatches() {
        int[] students = {0};
        int[] sandwiches = {0};
        assertEquals(0, solution.countStudents(students, sandwiches));
    }

    @Test
    void testSingleStudentMismatches() {
        int[] students = {1};
        int[] sandwiches = {0};
        assertEquals(1, solution.countStudents(students, sandwiches));
    }

    @Test
    void testStopsExactlyWhenPreferenceRunsOut() {
        // Студенты: три 0, одна 1. Сэндвичи: 0, 0, 1, 0. 
        // После раздачи 0, 0, 1 останется один студент с 0, но следующий сэндвич 0, он его заберет. 
        // Давайте сделаем так: студенты [0, 0, 1], сэндвичи [1, 0, 0]. 
        // 1 заберет 1, останутся два 0 и сэндвичи [0, 0]. Все поедят.
        // А если студенты [0, 0, 1], сэндвичи [1, 1, 0]:
        // 1 заберет 1. Останутся [0, 0], а следующий сэндвич 1. Никто не хочет. Ответ: 2.
        int[] students = {0, 0, 1};
        int[] sandwiches = {1, 1, 0};
        assertEquals(2, solution.countStudents(students, sandwiches));
    }
    
    @Test
    void testLargeAlternatingQueue() {
        // Проверка на чуть большем массиве
        int[] students = {0, 1, 0, 1, 0, 1, 0, 1};
        int[] sandwiches = {0, 0, 0, 0, 1, 1, 1, 1};
        // 4 нуля заберут 4 нуля. Останется 4 студента с 1, а следующий сэндвич 1. 
        // Все поедят, ответ 0.
        assertEquals(0, solution.countStudents(students, sandwiches));
    }
}