package io.neetcode.list;

import java.util.PriorityQueue;

public class MergeKSortedLinkedLists {
    /**
     * Метод объединяет k отсортированных связанных списков в один отсортированный связанный список.
     * 
     * @param lists массив связанных списков
     * @return отсортированный связанный список, объединяющий все данные из входных списков
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;

        // Создание приоритетной очереди для хранения узлов, отсортированных по значениям узлов
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>((a, b) -> a.val - b.val);

        // Добавляем начало каждого списка в приоритетную очередь.
        for (ListNode list : lists) {
            if (list != null) {
                minHeap.add(list);
            }
        }

        // Фиктивная голова для помощи в построении результирующего связанного списка
        ListNode dummyHead = new ListNode(0);
        ListNode current = dummyHead;

        // Пока есть элементы в приоритетной очереди
        while (!minHeap.isEmpty()) {
            // Извлекаем самый маленький элемент из очереди
            ListNode smallestNode = minHeap.poll();
            // Перемещаем указатель текущего узла
            current.next = smallestNode;
            current = current.next;

            // Если есть следующий узел, добавляем его в очередь
            if (smallestNode.next != null) {
                minHeap.add(smallestNode.next);
            }
        }

        // Возвращаем следующее за фиктивной головой, так как это начало объединённого списка
        return dummyHead.next;
    }
}