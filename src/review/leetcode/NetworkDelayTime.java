package review.leetcode;

import java.util.*;

public class NetworkDelayTime {
    public int networkDelayTime(int[][] times, int n, int k) {
        Map<Integer, List<Edge>> graph = new HashMap<>();
        for (int i = 1; i <= n ;i++) {
            graph.put(i, new ArrayList<>());
        }
        for (int[] t : times) {
            int u = t[0], v = t[1], w = t[2];
            graph.get(u).add(new Edge(v, w));
        }

        Queue<Edge> pq = new PriorityQueue<>((a, b) -> a.time - b.time);
        int[] minTime = new int[n + 1];
        Arrays.fill(minTime, Integer.MAX_VALUE);

        minTime[k] = 0;
        pq.add(new Edge(k, 0));

        while (!pq.isEmpty()) {
            Edge curr = pq.remove();

            if (curr.time > minTime[curr.node]) continue;

            for (Edge edge : graph.get(curr.node)) {
                int nextTime = curr.time + edge.time;
                if (nextTime < minTime[edge.node]) {
                    minTime[edge.node] = nextTime;
                    pq.add(new Edge(edge.node, nextTime));
                }
            }
        }

        int maxTime = 0;  //answer
        for (int i = 1; i <= n; i++) {
            if (minTime[i] == Integer.MAX_VALUE) return -1;
            maxTime = Math.max(minTime[i], maxTime);
        }
        return maxTime;
    }

    class Edge {
        int node;
        int time;

        Edge (int node, int time) {
            this.node = node;
            this.time = time;
        }
    }
}
