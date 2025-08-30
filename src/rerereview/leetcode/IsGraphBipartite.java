package rerereview.leetcode;

/**
 * https://leetcode.com/problems/is-graph-bipartite/description/
 * 785. Is Graph Bipartite?
 * 2025/08/30
 */

public class IsGraphBipartite {
    boolean[] visited;

    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                boolean[] set = new boolean[n];
                set[i] = true;
                if (!dfs(i, set, graph)) {
                    return false;
                }
            }
        }
        return true;
    }

    boolean dfs(int curr, boolean[] set, int[][] graph) {
        for (int next : graph[curr]) {
            if (visited[next]) {
                if (set[curr] == set[next]) {
                    return false;
                }
            }
            else {
                visited[next] = true;
                set[next] = !set[curr];
                if (!dfs(next, set, graph)) {
                    return false;
                }
            }
        }
        return true;
    }
}
