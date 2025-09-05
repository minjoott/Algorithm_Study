package rerereview.leetcode;

/**
 * https://leetcode.com/problems/network-delay-time/description/
 * 743. Netword Delay Time
 * 2025/09/04
 */

import java.util.*;

public class NetworkDelayTime {

    public int networkDelayTime(int[][] times, int n, int k) {
        //1. 방향그래프 graph 만들기
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            graph.put(i, new ArrayList<>());
        }
        for (int[] edge : times) {
            int u = edge[0], v = edge[1], w = edge[2];
            graph.get(u).add(new int[]{v, w});
        }

        //2. 다익스트라 알고리즘 수행
        Queue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        int[] minTimes = new int[n + 1];
        Arrays.fill(minTimes, Integer.MAX_VALUE);

        minTimes[k] = 0;
        pq.add(new int[]{k, 0});
        while (!pq.isEmpty()) {
            int[] curr = pq.remove();
            int currNode = curr[0], currTime = curr[1];
            if (currTime > minTimes[currNode]) continue;

            for (int[] next : graph.get(currNode)) {
                int nextNode = next[0], nextTime = currTime + next[1];
                if (nextTime < minTimes[nextNode]) {
                    minTimes[nextNode] = nextTime;
                    pq.add(new int[]{nextNode, nextTime});
                }
            }
        }

        //3. 답 추출
        int ans = -1;
        for (int i = 1; i <= n; i++) {
            if (minTimes[i] == Integer.MAX_VALUE) return -1;

            ans = Math.max(minTimes[i], ans);
        }
        return ans;
    }
}
