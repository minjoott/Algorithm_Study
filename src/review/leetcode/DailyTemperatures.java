package review.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

public class DailyTemperatures {
    public int[] dailyTemperatures(int[] temperatures) {
        final int N = temperatures.length;
        int[] answer = new int[N];
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < N; i++) {
            while (!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]) {
                int prev = stack.pop();
                answer[prev] = i - prev;
            }
            stack.push(i);
        }

        return answer;
    }
}
