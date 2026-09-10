package io.neetcode.queue;

class NumberOfStudentsUnableToEatLunch {
    public int countStudents(int[] students, int[] sandwiches) {
        // Считаем, сколько кто любит
        int[] count = new int[2];
        for (int s : students) {
            count[s]++;
        }
        
        // Раздаем сэндвичи по порядку
        for (int sandwich : sandwiches) {
            if (count[sandwich] > 0) {
                count[sandwich]--;
            } else {
                // Никто не хочет этот сэндвич — процесс остановился
                break;
            }
        }
        
        // Возвращаем тех, кто остался голодным
        return count[0] + count[1];
    }
}