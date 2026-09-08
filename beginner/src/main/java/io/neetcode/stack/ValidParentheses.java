package io.neetcode.stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

public class ValidParentheses {
    public boolean isValidOld(String s) {
        while (s.contains("()") || s.contains("{}") || s.contains("[]")) {
            s = s.replace("()", "");
            s = s.replace("{}", "");
            s = s.replace("[]", "");
        }
        return s.isEmpty();
    }

    public boolean isValid(String s) {
        int n = s.length();
        if ((n & 1) == 1) return false;
        if (n == 0) return true;

        byte[] pair = new byte[128];
        pair['}'] = '{';
        pair[')'] = '(';
        pair[']'] = '[';

        char[] arr = s.toCharArray();
        char[] stack = new char[n];
        int top = -1;

        for (char c : arr) {
            byte expected = pair[c];
            if (expected != 0) {
                if (top < 0 || stack[top] != expected) return false;
                top--;
            } else {
                stack[++top] = c;
            }
        }
        return top == -1;
    }
    public boolean isValidV3(String s) {
        // Кэшируем Map, чтобы не создавать при каждом вызове
        Map<Character, Character> closeToOpen = Map.of(
                ')', '(',
                ']', '[',
                '}', '{'
        );

        Deque<Character> stack = new ArrayDeque<>();

        for (char c : s.toCharArray()) {
            Character expected = closeToOpen.get(c);
            if (expected != null) {
                if (stack.isEmpty() || !stack.peek().equals(expected)) {
                    return false;
                }
                stack.pop();
            } else {
                stack.push(c);
            }
        }
        return stack.isEmpty();
    }
}
