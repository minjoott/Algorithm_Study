package review.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

public class ShortestPathInBinaryMatrix {
    public int shortestPathBinaryMatrix(int[][] grid) {
        final int n = grid.length;
        if (grid[0][0] == 1 || grid[n - 1][n - 1] == 1) return -1;

        boolean[][] visited = new boolean[n][n];
        Deque<int[]> queue = new ArrayDeque<>();
        visited[0][0] = true;
        queue.add(new int[]{0, 0, 1});

        final int[] DR = new int[]{1, 1, 0, -1, -1, -1, 0, 1};
        final int[] DC = new int[]{0, 1, 1, 1, 0, -1, -1, -1};
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0], c = curr[1], len = curr[2];
            if (r == n - 1 && c == n - 1) return len;

            for (int d = 0; d < 8; d++) {
                int nextR = r + DR[d];
                int nextC = c + DC[d];

                if (nextR < 0 || nextR >= n || nextC < 0 || nextC >= n ||
                        visited[nextR][nextC] ||
                        grid[nextR][nextC] == 1)
                    continue;

                visited[nextR][nextC] = true;
                queue.offer(new int[]{nextR, nextC, len + 1});
            }
        }
        return -1;
    }
}
