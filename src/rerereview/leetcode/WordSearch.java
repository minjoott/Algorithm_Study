package rerereview.leetcode;

/**
 * https://leetcode.com/problems/word-search/description/
 * 79. Word Search
 * 2025/08/29
 */

public class WordSearch {
    int m, n;
    boolean[][] visited;

    public boolean exist(char[][] board, String word) {
        m = board.length;
        n = board[0].length;
        visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == word.charAt(0)) {
                    visited[i][j] = true;
                    if (backtracking(1, i, j, board, word)) return true;
                    visited[i][j] = false;
                }
            }
        }
        return false;
    }

    static final int[] DR = {1, 0, -1, 0};
    static final int[] DC = {0, 1, 0, -1};

    boolean backtracking(int checkIdx, int r, int c, char[][] board, String word) {
        if (checkIdx == word.length()) return true;

        for (int d = 0; d < 4; d++) {
            int nextR = r + DR[d], nextC = c + DC[d];

            if (nextR < 0 || nextR >= m || nextC < 0 || nextC >= n || visited[nextR][nextC]) continue;

            if (board[nextR][nextC] == word.charAt(checkIdx)) {
                visited[nextR][nextC] = true;
                if (backtracking(checkIdx + 1, nextR, nextC, board, word)) return true;
                visited[nextR][nextC] = false;
            }
        }
        return false;
    }
}
