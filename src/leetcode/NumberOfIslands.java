package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

public class NumberOfIslands {
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
