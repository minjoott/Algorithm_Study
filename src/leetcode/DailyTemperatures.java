package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

public class DailyTemperatures {
    public int[] dailyTemperatures(int[] temperatures) {
        int[] ans = new int[temperatures.length];

        Deque<int[]> stack = new ArrayDeque<>();
        for (int i = 0; i < temperatures.length; i++) {
            if (!stack.isEmpty()) {
                while (!stack.isEmpty() && stack.peek()[0] < temperatures[i]) {
                    int[] pop = stack.pop();
                    ans[pop[1]] = i - pop[1];
                }
            }
            stack.push(new int[]{temperatures[i], i});
        }

        return ans;
    }
}
