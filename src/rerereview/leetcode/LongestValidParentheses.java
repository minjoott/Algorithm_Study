package rerereview.leetcode;

/**
 * https://leetcode.com/problems/longest-valid-parentheses/description/
 * 32. Longest Valid Parentheses
 * 2025/08/30
 */

import java.util.ArrayDeque;
import java.util.Deque;

public class LongestValidParentheses {
    public int longestValidParentheses(String s) {
        int ans = 0;
        Deque<Entry> stack = new ArrayDeque<>();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ')' && !stack.isEmpty() && stack.peek().c == '(') {
                stack.pop();
                int length = (stack.isEmpty()) ? i + 1 : i - stack.peek().idx;
                ans = Math.max(length, ans);
            }
            else {
                stack.push(new Entry(s.charAt(i), i));
            }
        }
        return ans;
    }

    class Entry {
        char c;
        int idx;

        Entry (char c, int idx) {
            this.c = c;
            this.idx = idx;
        }
    }
}
