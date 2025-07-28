package review.leetcode;

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
        pq.offer(new Edge(start, 1));

        while (!pq.isEmpty()) {
            Edge curr = pq.poll();

            if (curr.prob < maxProb[curr.node]) continue;

            for (Edge next : graph.get(curr.node)) {
                double nextProb = curr.prob * next.prob;
                if (nextProb > maxProb[next.node]) {
                    maxProb[next.node] = nextProb;
                    pq.offer(new Edge(next.node, nextProb));
                }
            }
        }

        return maxProb[end];
    }

    class Edge {
        int node;
        double prob;

        Edge (int node, double prob) {
            this.node = node;
            this.prob = prob;
        }
    }
}
