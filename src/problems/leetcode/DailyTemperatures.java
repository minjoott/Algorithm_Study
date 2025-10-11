package problems.leetcode;

/**
 * https://leetcode.com/problems/daily-temperatures/description/
 * [LeetCode] 739. Daily Temperatures
 * solved at: 251009
 */

import java.util.Stack;

public class DailyTemperatures {

    class Solution1_stack {
        public int[] dailyTemperatures(int[] temperatures) {
            int[] answer = new int[temperatures.length];
            Stack<int[]> stack = new Stack<>();
            for (int i = 0; i < temperatures.length; i++) {
                while (!stack.isEmpty() && stack.peek()[0] < temperatures[i]) {
                    int[] pick = stack.pop();
                    answer[pick[1]] = i - pick[1];
                }
                stack.push(new int[]{temperatures[i], i});
            }
            return answer;
        }
    }
}
