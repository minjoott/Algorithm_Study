package problems.baekjoon;

/**
 * https://www.acmicpc.net/problem/1976
 * [Backjoon] 1976. 여행 가자
 * solved at: 251207
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 여행가자 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        List<Set<Integer>> set = new ArrayList<>();
        set.add(new HashSet<>());
        for (int i = 1; i <= N; i++) {
            set.add(new HashSet<>());
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                if (Integer.parseInt(st.nextToken()) == 1) {
                    set.get(i).add(j);
                }
            }
        }

        int[] plan = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            plan[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < M - 1; i++) {
            int a = plan[i];  //출발지
            int b = plan[i + 1];  //목적지

            boolean possible = false;
            Deque<Integer> queue = new ArrayDeque<>();
            boolean[] visited = new boolean[N + 1];

            queue.offer(a);
            visited[a] = true;

            while (!queue.isEmpty()) {
                int curr = queue.poll();
                if (curr == b) {
                    possible = true;
                    break;
                }

                for (int next : set.get(curr)) {
                    if (!visited[next]) {
                        visited[next] = true;
                        queue.offer(next);
                    }
                }
            }

            if (!possible) {
                System.out.println("NO");
                return;
            }
        }

        System.out.println("YES");
        return;
    }
}
