package problems.leetcode;

/**
 * https://leetcode.com/problems/daily-temperatures
 * [LeetCode] 739. Daily Temperatures
 * solved at: 251106
 */

import java.util.ArrayDeque;
import java.util.Deque;

public class DailyTemperatures {
    class Solution {
        public int[] dailyTemperatures(int[] temperatures) {
            int[] answer = new int[temperatures.length];
            Deque<int[]> stack = new ArrayDeque<>();
            for (int i = 0; i < temperatures.length; i++) {
                while (!stack.isEmpty() && stack.peek()[0] < temperatures[i]) {
                    int[] cooler = stack.pop();
                    answer[cooler[1]] = i - cooler[1];
                }
                stack.push(new int[]{temperatures[i], i});
            }
            return answer;
        }
    }
}
