package problems.baekjoon;

/**
 * https://www.acmicpc.net/problem/13975
 * [Baekjoon] 13975. 파일 합치기 3
 * solved at: 251208
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class 파일합치기3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            int K = Integer.parseInt(br.readLine());
            Queue<Long> pq = new PriorityQueue<>();
            st = new StringTokenizer(br.readLine());
            for (int k = 0; k < K; k++) {
                long size = Long.parseLong(st.nextToken());
                pq.offer(size);
            }

            long cost = 0;
            while (pq.size() > 1) {
                long sum = pq.poll() + pq.poll();
                cost += sum;
                pq.offer(sum);
            }
            sb.append(cost).append('\n');
        }
        System.out.println(sb.toString());
        return;
    }
}
