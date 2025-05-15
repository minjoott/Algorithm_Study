package programmers;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class 호텔대실 {
    public int solution(String[][] book_time) {
        int N = book_time.length;

        // 대실 시작 시간이 빠른 순으로 정렬
        int[][] times = new int[N][2];
        for (int i = 0; i < N; i++) {
            String[] time = book_time[i];
            String[] startStr = time[0].split(":");
            int start = Integer.parseInt(startStr[0]) * 60 +  Integer.parseInt(startStr[1]);
            String[] endStr = time[1].split(":");
            int end = Integer.parseInt(endStr[0]) * 60 + Integer.parseInt(endStr[1]);

            times[i] = new int[]{start, end};
        }
        Arrays.sort(times, (a, b) -> a[0] - b[0]);  // 오름차순 정렬

        int maxRoomCnt = 0;  // 우선순위큐에 가장 많은 방이 저장되어 있을 때의 값 (answer)
        Queue<Integer> pq = new PriorityQueue<>();  // 대실 종료 시각이 빠른 순으로 오름차순 정렬
        for (int[] time : times) {
            int start = time[0], end = time[1];
            while (!pq.isEmpty() && start >= pq.peek() + 10) {
                pq.poll();
            }
            pq.offer(end);
            maxRoomCnt = Math.max(pq.size(), maxRoomCnt);
        }

        return maxRoomCnt;
    }
}
