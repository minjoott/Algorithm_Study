package review.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

public class LongestValidParentheses {
    public int longestValidParentheses(String s) {
        int longest = 0;  // answer
        Deque<Entry> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ')' && !stack.isEmpty() && stack.peek().c == '(') {
                Entry prev = stack.pop();
                longest = !stack.isEmpty() ? Math.max(i - stack.peek().idx, longest) : i + 1;
            } else {
                stack.push(new Entry(s.charAt(i), i));
            }
        }

        return longest;
    }

    class Entry {
        char c;
        int idx;

        Entry(char c, int idx) {
            this.c = c;
            this.idx = idx;
        }
    }
}
