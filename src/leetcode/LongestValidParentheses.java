package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

public class LongestValidParentheses {
    public int longestValidParentheses(String s) {
        int ans = 0;
        Deque<Entry> stack = new ArrayDeque<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(new Entry(c, i));
            }
            else {  // ')'
                if (!stack.isEmpty() && stack.peek().c == '(') {
                    Entry pop = stack.pop();
                    int cur;
                    if (!stack.isEmpty()) {
                        cur = i - stack.peek().idx;
                    }
                    else {
                        cur = i + 1;
                    }
                    if (cur > ans)
                        ans = cur;
                }
                else {
                    stack.push(new Entry(')', i));
                }
            }
        }

        return ans;
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
