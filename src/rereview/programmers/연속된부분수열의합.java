package rereview.programmers;

import java.util.PriorityQueue;
import java.util.Queue;

public class 연속된부분수열의합 {
    public int[] solution(int[] sequence, int k) {
        Queue<int[]> pq = new PriorityQueue<>((a, b) -> {
            if (a[2] - b[2] != 0) return a[2] - b[2];
            return a[0] - b[0];
        });

        int start = 0, end = 0;
        int sum = sequence[0];
        while (true) {
            if (sum < k) {
                if (end == sequence.length - 1) break;
                sum += sequence[++end];
            }
            else if (sum > k) {
                sum -= sequence[start++];
            }
            else {
                pq.offer(new int[]{start, end, end - start + 1});
                if (end == sequence.length - 1) break;
                sum -= sequence[start++];
            }
        }

        int[] answer = pq.poll();
        return new int[]{answer[0], answer[1]};
    }
}
