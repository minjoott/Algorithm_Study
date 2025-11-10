package problems.leetcode;

/**
 * https://leetcode.com/problems/longest-valid-parentheses
 * [LeetCode] 32. Longest Valid Parentheses
 * solved at: 251106
 */

import java.util.ArrayDeque;
import java.util.Deque;

public class LongestValidParentheses {
    class Solution {
        public int longestValidParentheses(String s) {
            int longest = 0;
            Deque<Entry> stack = new ArrayDeque<>();
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == ')' && !stack.isEmpty() && stack.peek().c == '(') {
                    stack.pop();
                    int length = (stack.isEmpty()) ? i + 1 : i - stack.peek().idx;
                    longest = Math.max(length, longest);
                    continue;
                }
                stack.push(new Entry(s.charAt(i), i));
            }
            return longest;
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
}
