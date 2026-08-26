package io.neetcode.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Baseball Game
 *
 */
public class BaseballGame {

    public int calPoints(String[] ops) {
        int res = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        for (String op : ops) {
            if (op.equals("+")) {
                int top = stack.pop();
                int newTop = top + stack.peek();
                stack.push(top);
                stack.push(newTop);
                res += newTop;
            } else if (op.equals("D")) {
                int val = 2 * stack.peek();
                stack.push(val);
                res += val;
            } else if (op.equals("C")) {
                res -= stack.pop();
            } else {
                stack.push(Integer.parseInt(op));
                res += stack.peek();
            }
        }
        return res;
    }

    public static void main(String[] args) {
        BaseballGame baseballGame = new BaseballGame();
        System.out.println(baseballGame.calPoints(new String[]{"1", "2", "+", "C", "5", "D"}));
        System.out.println(baseballGame.calPoints(new String[]{"5","D","+","C"}));
    }
}
