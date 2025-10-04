package study.backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 과제 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        final int N = Integer.parseInt(st.nextToken());

        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            list.add(new int[]{d, w});
        }
        list.sort((a, b) -> a[0] - b[0]);

        Queue<Integer> pq = new PriorityQueue<>();
        int maxSum = 0;
        for (int[] task : list) {
            int d = task[0];
            int w = task[1];
            pq.add(w);
            maxSum += w;

            if (pq.size() > d) {
                maxSum -= pq.remove();
            }
        }
        System.out.println(maxSum);
        return;
    }
}
