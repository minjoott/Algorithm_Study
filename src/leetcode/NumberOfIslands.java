package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

class NumberOfIslands_bfs {
    public int numIslands(char[][] grid) {
        int ans = 0;
        int m = grid.length, n = grid[0].length;
        boolean[][] visited = new boolean[m][n];

        int[] dy = {1, 0, -1, 0};
        int[] dx = {0, 1, 0, -1};
        Deque<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1' && !visited[i][j]) {
                    queue.add(new int[]{i, j});
                    visited[i][j] = true;
                    ans++;

                    while (!queue.isEmpty()) {
                        int[] curr = queue.poll();

                        for (int d = 0; d < 4; d++) {
                            int nextY = curr[0] + dy[d];
                            int nextX = curr[1] + dx[d];

                            if (nextY >= m || nextY < 0 || nextX >= n || nextX < 0 || visited[nextY][nextX]) continue;

                            if (grid[nextY][nextX] == '1') {
                                queue.add(new int[]{nextY, nextX});
                                visited[nextY][nextX] = true;
                            }
                        }
                    }
                }
            }
        }

        return ans;
    }
}

class NumberOfIslands_dfs {
    int ans = 0;
    int m, n;
    boolean[][] visited;

    public int numIslands(char[][] grid) {
        m = grid.length;
        n = grid[0].length;
        visited = new boolean[m][n];

        Deque<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1' && !visited[i][j]) {
                    visited[i][j] = true;
                    dfs(i, j, grid);
                    ans++;
                }
            }
        }

        return ans;
    }

    int[] dy = { 1, 0, -1, 0 };
    int[] dx = { 0, 1, 0, -1 };

    void dfs(int y, int x, char[][] grid) {
        for (int d = 0; d < 4; d++) {
            int nextY = y + dy[d];
            int nextX = x + dx[d];

            if (nextY >= m || nextY < 0 || nextX >= n || nextX < 0 || visited[nextY][nextX])
                continue;

            if (grid[nextY][nextX] == '1') {
                visited[nextY][nextX] = true;
                dfs(nextY, nextX, grid);
            }
        }
    }
}