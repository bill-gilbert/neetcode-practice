package io.neetcode.stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class MinStack {
    private Deque<Integer> stack;
    private Deque<Integer> minStack;

    public MinStack() {
        stack = new ArrayDeque<>();
        minStack = new ArrayDeque<>();
    }

    public void push(int val) {
        stack.push(val);
        // Если minStack пуст или текущее значение <= текущему минимуму,
        // добавляем его в стек минимумов. Т.е. наверху всегда самый минимум.
        // Для подстраховки минимальный добавляем несколько раз. На случай если его бахнут из основного
        if (minStack.isEmpty() || val <= minStack.peek()) {
            minStack.push(val);
        }
    }

    public void pop() {
        // Удаляем элемент из основного стека
        int popped = stack.pop();
        // Если удаленный элемент был текущим минимумом, удаляем его и из minStack
        // Для этого мы минимальный несколько раз и добавляли
        if (popped == minStack.peek()) {
            minStack.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}
