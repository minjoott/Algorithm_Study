package review.leetcode;

public class IsGraphBipartite {
    private int n;
    private boolean[] visited;

    public boolean isBipartite(int[][] graph) {
        n = graph.length;
        visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                if (!dfs(i, new boolean[n], graph)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean dfs(int curr, boolean[] set, int[][] graph) {
        for (int next : graph[curr]) {
            if (!visited[next]) {
                visited[next] = true;
                set[next] = !set[curr];
                if (!dfs(next, set, graph)) {
                    return false;
                }
            } else {
                if (set[next] == set[curr]) {
                    return false;
                }
            }
        }
        return true;
    }
}
