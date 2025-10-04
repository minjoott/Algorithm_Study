package problems.leetcode;

/**
 * https://leetcode.com/problems/word-search/description
 * [LeetCode] 79. Word Search
 * solved at: 251004
 */

public class WordSearch {
    class Solution1_backtracking {
        int m, n;

        public boolean exist(char[][] board, String word) {
            m = board.length;
            n = board[0].length;

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (board[i][j] == word.charAt(0)) {
                        boolean[][] visited = new boolean[m][n];
                        visited[i][j] = true;
                        if (backtracking(i, j, 1, visited, board, word)) return true;
                    }
                }
            }
            return false;
        }

        int[] DR = {1, 0, -1, 0};
        int[] DC = {0, 1, 0, -1};

        boolean backtracking(int r, int c, int checkIdx, boolean[][] visited, char[][] board, String word) {
            if (checkIdx == word.length()) return true;

            for (int d = 0; d < 4; d++) {
                int nextR = r + DR[d];
                int nextC = c + DC[d];

                if (nextR < 0 || nextR >= m || nextC < 0 || nextC >= n || visited[nextR][nextC]) continue;

                if (board[nextR][nextC] == word.charAt(checkIdx)) {
                    visited[nextR][nextC] = true;
                    if (backtracking(nextR, nextC, checkIdx + 1, visited, board, word)) return true;
                    visited[nextR][nextC] = false;
                }
            }
            return false;
        }
    }


    class Solution2_backtracking {
        int m, n;

        public boolean exist(char[][] board, String word) {
            m = board.length;
            n = board[0].length;

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (backtracking(i, j, 0, new boolean[m][n], board, word)) return true;
                }
            }
            return false;
        }

        int[] DR = {1, 0, -1, 0};
        int[] DC = {0, 1, 0, -1};

        boolean backtracking(int r, int c, int checkIdx, boolean[][] visited, char[][] board, String word) {
            if (checkIdx == word.length()) return true;

            if (r < 0 || r >= m || c < 0 || c >= n) return false;
            if (visited[r][c]) return false;
            if (board[r][c] != word.charAt(checkIdx)) return false;

            visited[r][c] = true;
            for (int d = 0; d < 4; d++) {
                if (backtracking(r + DR[d], c + DC[d], checkIdx + 1, visited, board, word)) return true;
            }
            visited[r][c] = false;

            return false;
        }
    }
}
