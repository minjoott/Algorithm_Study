package programmers;

import java.util.ArrayDeque;
import java.util.Deque;

public class 뒤에있는큰수찾기 {
    public int[] solution(int[] numbers) {
        int N = numbers.length;

        Deque<int[]> stack = new ArrayDeque<>();
        int[] answer = new int[N];
        for (int i = 0; i < N; i++) {
            int num = numbers[i];
            while (!stack.isEmpty() && stack.peek()[0] < num) {
                int[] pre = stack.pop();
                int preIdx = pre[1];
                answer[preIdx] = num;
            }
            stack.push(new int[]{num, i});
        }

        while (!stack.isEmpty()) {
            int restIdx = stack.pop()[1];
            answer[restIdx] = -1;
        }

        return answer;
    }
}
