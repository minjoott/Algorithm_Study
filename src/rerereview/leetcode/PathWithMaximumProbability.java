package rerereview.leetcode;

/**
 * https://leetcode.com/problems/path-with-maximum-probability/description/
 * 1514. Path with Maximum Probability
 * 2025/09/08
 */

import java.util.*;

public class PathWithMaximumProbability {
    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        Map<Integer, List<Edge>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) {
            graph.put(i, new ArrayList<>());
        }
        for (int i = 0; i < edges.length; i++) {
            int a = edges[i][0], b = edges[i][1];
            double prob = succProb[i];
            graph.get(a).add(new Edge(b, prob));
            graph.get(b).add(new Edge(a, prob));
        }

        Queue<Edge> pq = new PriorityQueue<>((a, b) -> Double.compare(b.prob, a.prob));
        double[] maxProb = new double[n];

        maxProb[start] = 1;
        pq.add(new Edge(start, 1));
        while (!pq.isEmpty()) {
            Edge curr = pq.remove();
            if (curr.prob < maxProb[curr.node]) continue;

            if (curr.node == end) return curr.prob;

            for (Edge next : graph.get(curr.node)) {
                next.prob = curr.prob * next.prob;
                if (next.prob > maxProb[next.node]) {
                    maxProb[next.node] = next.prob;
                    pq.add(new Edge(next.node, next.prob));
                }
            }
        }
        return maxProb[end];
    }

    static class Edge {
        int node;
        double prob;
        Edge (int node, double prob) {
            this.node = node;
            this.prob = prob;
        }
    }
}
