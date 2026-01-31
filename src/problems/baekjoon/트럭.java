package problems.baekjoon;

/**
 * https://www.acmicpc.net/problem/13335
 * [Baekjoon] 13335. 트럭
 * solved at: 260131
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class 트럭 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        int[] cars = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) cars[i] = Integer.parseInt(st.nextToken());

        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < W; i++) queue.add(0);

        int i = 0;
        int weight = 0;
        int time = 0;

        while (i < N) {
            weight -= queue.remove();

            int entry = 0;
            if (weight + cars[i] <= L) {
                weight += cars[i];
                entry = cars[i];
                i++;
            }
            queue.add(entry);
            time++;
        }
        time += W;

        System.out.println(time);
        return;
    }
}
