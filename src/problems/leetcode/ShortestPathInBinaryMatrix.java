package problems.leetcode;

/**
 * https://leetcode.com/problems/shortest-path-in-binary-matrix/
 * [LeetCode] 1091. Shortest Path in Binary Matrix
 * solved at: 251111
 */

import java.util.ArrayDeque;
import java.util.Deque;

public class ShortestPathInBinaryMatrix {
    class Solution {
        final int[] DR = {1, 1, 0, -1, -1, -1, 0, 1};
        final int[] DC = {0, 1, 1, 1, 0, -1, -1, -1};

        public int shortestPathBinaryMatrix(int[][] grid) {
            int n = grid.length;

            if (grid[0][0] == 1) return -1;

            boolean[][] visited = new boolean[n][n];
            Deque<int[]> queue = new ArrayDeque<>();
            visited[0][0] = true;
            queue.add(new int[]{0, 0, 1});
            while (!queue.isEmpty()) {
                int[] curr = queue.remove();
                int r = curr[0], c = curr[1], length = curr[2];

                if (r == n - 1 && c == n - 1) return length;

                for (int d = 0; d < 8; d++) {
                    int nextR = r + DR[d], nextC = c + DC[d];
                    if (nextR < 0 || nextR >= n || nextC < 0 || nextC >= n) continue;
                    if (visited[nextR][nextC]) continue;
                    if (grid[nextR][nextC] == 1) continue;

                    visited[nextR][nextC] = true;
                    queue.add(new int[]{nextR, nextC, length + 1});
                }
            }
            return -1;
        }
    }
}
