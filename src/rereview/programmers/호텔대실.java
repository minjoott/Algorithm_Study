package rereview.programmers;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class 호텔대실 {
    public int solution(String[][] book_time) {
        int n = book_time.length;
        int[][] bookList = new int[n][2];
        for (int i = 0; i < n; i++) {
            String[] startStrs = book_time[i][0].split(":");
            int start = Integer.parseInt(startStrs[0]) * 60 +Integer.parseInt(startStrs[1]);
            String[] endStrs = book_time[i][1].split(":");
            int end = Integer.parseInt(endStrs[0]) * 60 + Integer.parseInt(endStrs[1]);
            bookList[i][0] = start;
            bookList[i][1] = end;
        }
        Arrays.sort(bookList, (a, b) -> (a[0] - b[0]));

        int answer = 0;
        Queue<Integer> pq = new PriorityQueue<>((a, b) -> a - b);
        for (int i = 0; i < n; i++) {
            if (pq.isEmpty() || bookList[i][0] < pq.peek() + 10) {
                ++answer;
            } else {
                pq.poll();
            }
            pq.offer(bookList[i][1]);
        }

        return answer;
    }
}
