package problems.backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/**
 * https://www.acmicpc.net/problem/1715
 * [Backjoon] 1715. 카드 정렬하기
 * solved at: 251121(시간 초과 및 해결)
 */

public class 카드정렬하기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            int x = Integer.parseInt(br.readLine());
            pq.offer(x);
        }

        long answer = 0;
        while (pq.size() > 1) {
            int a = pq.poll();
            int b = pq.poll();
            int sum = a + b;

            answer += sum;
            pq.offer(sum);
        }

        System.out.println(answer);
        return;
    }
}
