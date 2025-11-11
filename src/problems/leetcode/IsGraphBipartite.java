package problems.leetcode;

/**
 * https://leetcode.com/problems/is-graph-bipartite/
 * [LeetCode] 785. Is Graph Bipartite?
 * solved at: 251110
 */

import java.util.ArrayDeque;
import java.util.Deque;

public class IsGraphBipartite {
    class Solution {
        public boolean isBipartite(int[][] graph) {
            int n = graph.length;

            boolean[] visited = new boolean[n];
            boolean[] set = new boolean[n];
            Deque<Integer> queue = new ArrayDeque<>();
            for (int i = 0; i < n; i++) {
                if (!visited[i] && graph[i].length != 0) {
                    visited[i] = true;
                    set[i] = true;
                    queue.add(i);
                    while (!queue.isEmpty()) {
                        int curr = queue.remove();
                        for (int next : graph[curr]) {
                            if (visited[next] && set[curr] != set[next])continue;
                            else if (visited[next] && set[curr] == set[next])return false;
                            else {
                                visited[next] = true;
                                set[next] = !set[curr];
                                queue.add(next);
                            }
                        }
                    }
                }
            }
            return true;
        }
    }
}
