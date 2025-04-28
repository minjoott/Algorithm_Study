package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

class IsGraphBipartite_bfs {
    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        boolean[] visited = new boolean[n];
        boolean[] set = new boolean[n];

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                Deque<Integer> queue = new ArrayDeque<>();

                queue.add(i);
                set[i] = true;
                visited[i] = true;

                while (!queue.isEmpty()) {
                    int cur = queue.poll();
                    for (int next : graph[cur]) {
                        if (!visited[next]) {
                            queue.add(next);
                            set[next] = !set[cur];
                            visited[next] = true;
                        }
                        else {  // next가 이미 방문한 곳이면
                            if (set[cur] == set[next])
                                return false;
                        }
                    }
                }
            }
        }

        return true;
    }
}

class IsGraphBipartite_dfs {
    int n;
    boolean[] visited;
    boolean[] set;

    public boolean isBipartite(int[][] graph) {
        n = graph.length;
        visited = new boolean[n];
        set = new boolean[n];

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                set[i] = true;
                visited[i] = true;
                if (!dfs(i, graph))
                    return false;
            }
        }

        return true;
    }

    boolean dfs(int cur, int[][] graph) {
        for (int next : graph[cur]) {
            if (!visited[next]) {
                set[next] = !set[cur];
                visited[next] = true;
                if (!dfs(next, graph))
                    return false;
            }
            else {
                if (set[cur] == set[next])
                    return false;
            }
        }

        return true;
    }
}
