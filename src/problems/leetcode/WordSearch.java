package problems.leetcode;

/**
 * https://leetcode.com/problems/word-search/description/
 * [LeetCode] 79. Word Search
 * solved at: 251104
 */

public class WordSearch {
    class Solution {
        int m;
        int n;

        public boolean exist(char[][] board, String word) {
            m = board.length;
            n = board[0].length;

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (board[i][j] == word.charAt(0)) {
                        boolean[][] visited = new boolean[m][n];
                        visited[i][j] = true;
                        if (backtracking(1, i, j, visited, board, word)) return true;
                    }
                }
            }
            return false;
        }

        final int[] DR = {1, 0, -1, 0};
        final int[] DC = {0, 1, 0, -1};

        boolean backtracking(int checkIdx, int r, int c, boolean[][] visited, char[][] board, String word) {
            if (checkIdx == word.length()) {
                return true;
            }

            for (int d = 0; d < 4; d++) {
                int nextR = r + DR[d];
                int nextC = c + DC[d];

                if (nextR < 0 || nextR >= m || nextC < 0 || nextC >= n) continue;
                if (visited[nextR][nextC]) continue;
                if (board[nextR][nextC] != word.charAt(checkIdx)) continue;

                visited[nextR][nextC] = true;
                if (backtracking(checkIdx + 1, nextR, nextC, visited, board, word)) return true;
                visited[nextR][nextC] = false;
            }
            return false;
        }
    }
}
