package leetcode;

import java.util.*;

public class PathWithMaximumProbability {
    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        Map<Integer, List<double[]>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) {
            graph.put(i, new ArrayList<>());
        }
        for (int i = 0; i < edges.length; i++) {
            graph.get(edges[i][0]).add(new double[]{edges[i][1], succProb[i]});
            graph.get(edges[i][1]).add(new double[]{edges[i][0], succProb[i]});
        }

        double[] maxProb = new double[n];
        Arrays.fill(maxProb, 0);

        Queue<double[]> pq = new PriorityQueue<>((a, b) -> Double.compare(b[1], a[1]));
        pq.add(new double[]{start, 1});
        while (!pq.isEmpty()) {
            double[] curr = pq.poll();
            int curNode = (int) curr[0];
            double curProb = curr[1];
            if (curProb < maxProb[curNode]) continue;

            for (double[] next : graph.get(curNode)) {
                int nextNode = (int) next[0];
                double nextProb = curProb * next[1];

                if (nextProb > maxProb[nextNode]) {
                    pq.add(new double[]{nextNode, nextProb});
                    maxProb[nextNode] = nextProb;
                }
            }
        }

        return maxProb[end];
    }
}
