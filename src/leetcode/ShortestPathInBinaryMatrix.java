package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

public class ShortestPathInBinaryMatrix {
    public int shortestPathBinaryMatrix(int[][] grid) {
        int ans = 0;

        int n = grid.length;
        boolean[][] visited = new boolean[n][n];
        Deque<int[]> queue = new ArrayDeque<>();

        int[] dy = {1, 1, 0, -1, -1, -1, 0, 1};
        int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};

        if (grid[0][0] == 0) {
            queue.add(new int[]{0, 0, 1});
            visited[0][0] = true;
        }

        outerLoop:
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            if (curr[0] == n - 1 && curr[1] == n - 1) {
                ans = curr[2];
                break;
            }

            for (int d = 0; d < 8; d++) {
                int nextY = curr[0] + dy[d];
                int nextX = curr[1] + dx[d];

                if (nextY >= n || nextY < 0 || nextX >= n || nextX < 0 || visited[nextY][nextX]) continue;

                if (grid[nextY][nextX] == 0) {
                    queue.add(new int[]{nextY, nextX, curr[2] + 1});
                    visited[nextY][nextX] = true;
                }
            }
        }

        if (visited[0][0] && visited[n - 1][n - 1])
            return ans;
        else
            return -1;
    }
}
