package problems.leetcode;

/**
 * https://leetcode.com/problems/is-graph-bipartite/
 * [LeetCode] 785. Is Graph Bipartite?
 * solved at: 251009
 */

public class IsGraphBipartite {

    class Solution1_dfs {
        int n;
        boolean[] visited;

        public boolean isBipartite(int[][] graph) {
            n = graph.length;
            visited = new boolean[n];

            for (int i = 0; i < n; i++) {
                if (!visited[i]) {
                    if (!dfs(i, new boolean[n], graph)) return false;
                }
            }
            return true;
        }

        boolean dfs(int curr, boolean[] set, int[][] graph) {
            visited[curr] = true;

            for (int next : graph[curr]) {
                if (!visited[next]) {
                    set[next] = !set[curr];
                    if (!dfs(next, set, graph)) return false;;
                }
                if (set[next] == set[curr]) return false;
            }
            return true;
        }
    }
}
