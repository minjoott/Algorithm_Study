package rerereview.leetcode;

/**
 * https://leetcode.com/problems/daily-temperatures/
 * 739. Daily Temperatures
 * 2025/08/30
 */

import java.util.ArrayDeque;
import java.util.Deque;

public class DailyTemperatures {
    public int[] dailyTemperatures(int[] temperatures) {
        int[] answer = new int[temperatures.length];
        Deque<int[]> stack = new ArrayDeque<>();

        for (int i = 0; i < temperatures.length; i++) {
            while (!stack.isEmpty() && temperatures[i] > stack.peek()[0]) {
                int prevDay = stack.pop()[1];
                answer[prevDay] = i - prevDay;
            }
            stack.push(new int[]{temperatures[i], i});
        }

        return answer;
    }
}
