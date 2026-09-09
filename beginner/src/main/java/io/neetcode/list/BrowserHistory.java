package io.neetcode.list;

import java.util.ArrayList;
import java.util.List;

class BrowserHistory {
    private List<String> history;
    private int current;

    public BrowserHistory(String homepage) {
        history = new ArrayList<>();
        history.add(homepage);
        current = 0;
    }

    public void visit(String url) {
        current++;
        if (current < history.size()) {
            history.set(current, url);
            // Truncate forward history efficiently
            history.subList(current + 1, history.size()).clear();
        } else {
            history.add(url);
        }
    }

    public String back(int steps) {
        current = Math.max(0, current - steps);
        return history.get(current);
    }

    public String forward(int steps) {
        current = Math.min(history.size() - 1, current + steps);
        return history.get(current);
    }
}
