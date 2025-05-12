package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 파티 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        final int N = Integer.parseInt(st.nextToken());
        final int M = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int i = 1; i <= N; i++) {
            graph.put(i, new ArrayList<>());
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());
            graph.get(from).add(new int[]{to, time});
        }

        int[] timeToParty = new int[N + 1];
        Queue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        for (int i = 1; i <= N; i++) {
            if (i == X) continue;

            int[] minTimeToParty = new int[N + 1];
            Arrays.fill(minTimeToParty, Integer.MAX_VALUE);
            pq.add(new int[]{i, 0});

            while (!pq.isEmpty()) {
                int[] curr = pq.poll();
                int curNode = curr[0], curTime = curr[1];
                if (curTime > minTimeToParty[curNode]) continue;

                for (int[] next : graph.get(curNode)) {
                    int nextNode = next[0], nextTime = curTime + next[1];
                    if (nextTime < minTimeToParty[nextNode]) {
                        minTimeToParty[nextNode] = nextTime;
                        if (nextNode != X) pq.add(new int[]{nextNode, nextTime});
                    }
                }
            }

            int[] minTimeFromParty = new int[N + 1];
            Arrays.fill(minTimeFromParty, Integer.MAX_VALUE);
             pq.add(new int[]{X, 0});

            while (!pq.isEmpty()) {
                int[] curr = pq.poll();
                int curNode = curr[0], curTime = curr[1];
                if (curTime > minTimeFromParty[curNode]) continue;

                for (int[] next : graph.get(curNode)) {
                    int nextNode = next[0], nextTime = curTime + next[1];
                    if (nextTime < minTimeFromParty[nextNode]) {
                        minTimeFromParty[nextNode] = nextTime;
                        if (nextNode != i) pq.add(new int[]{nextNode, nextTime});
                    }
                }
            }

            timeToParty[i] = minTimeToParty[X] + minTimeFromParty[i];
        }

        int longest = -1;
        for (int i = 1; i <= N; i++) {
            longest = Math.max(timeToParty[i], longest);
        }
        System.out.println(longest);
        return;
    }
}
