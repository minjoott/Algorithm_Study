package leetcode;

import java.util.*;

public class NetworkDelayTime {
    public int networkDelayTime(int[][] times, int n, int k) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            graph.put(i, new ArrayList<>());
        }
        for (int i = 0; i < times.length; i++) {
            int from = times[i][0];
            int to = times[i][1];
            int time = times[i][2];
            graph.get(from).add(new int[]{to, time});
        }

        int[] minTime = new int[n + 1];
        Arrays.fill(minTime, Integer.MAX_VALUE);

        PriorityQueue<int[]> pq = new PriorityQueue<>((p1, p2) -> p1[1] - p2[1]);
        pq.add(new int[]{k, 0});
        minTime[k] = 0;

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int curNode = curr[0];
            int curTime = curr[1];

            if (curTime > minTime[curNode]) continue;

            for (int[] next : graph.get(curNode)) {
                int nextNode = next[0];
                int nextTime = next[1];

                int totalTime = curTime + nextTime;
                if (totalTime < minTime[nextNode]) {
                    minTime[nextNode] = totalTime;
                    pq.add(new int[]{nextNode, totalTime});
                }
            }
        }

        int answer = -1;
        for (int i = 1; i <= n; i++) {
            if (minTime[i] == Integer.MAX_VALUE)
                return -1;
            answer = Math.max(answer, minTime[i]);
        }
        return answer;
    }
}
