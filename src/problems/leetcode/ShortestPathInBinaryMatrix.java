package problems.leetcode;

/**
 * https://leetcode.com/problems/shortest-path-in-binary-matrix/
 * [LeetCode] 1091. Shortest Path in Binary Matrix
 * solved at: 251009
 */

import java.util.ArrayDeque;
import java.util.Queue;

public class ShortestPathInBinaryMatrix {

    class Solution1_bfs {
        final int[] DR = {1, 1, 0, -1, -1, -1, 0, 1};
        final int[] DC = {0, 1, 1, 1, 0, -1, -1, -1};
        int n;

        public int shortestPathBinaryMatrix(int[][] grid) {
            n = grid.length;

            if (grid[0][0] == 1) return -1;

            Queue<int[]> queue = new ArrayDeque<>();
            boolean[][] visited = new boolean[n][n];

            queue.add(new int[]{0, 0, 1});
            visited[0][0] = true;

            while (!queue.isEmpty()) {
                int[] curr = queue.remove();
                if (curr[0] == n - 1 && curr[1] == n - 1) return curr[2];

                for (int d = 0; d < 8; d++) {
                    int nextR = curr[0] + DR[d];
                    int nextC = curr[1] + DC[d];

                    if (nextR < 0 || nextR >= n || nextC < 0 || nextC >= n || visited[nextR][nextC]) continue;
                    if (grid[nextR][nextC] == 1) continue;

                    queue.add(new int[]{nextR, nextC, curr[2] + 1});
                    visited[nextR][nextC] = true;
                }
            }

            return -1;
        }
    }
}
