package review.leetcode;

public class WordSearch {
    private int m;
    private int n;
    private boolean visited[][];
    public boolean exist(char[][] board, String word) {
        m = board.length;
        n = board[0].length;
        visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == word.charAt(0)) {
                    visited[i][j] = true;
                    if (backtracking(i, j, 1, board, word)) {
                        return true;
                    }
                    visited[i][j] = false;
                }
            }
        }

        return false;
    }

    private static final int[] DY = { 1, 0, -1, 0 };
    private static final int[] DX = { 0, 1, 0, -1 };

    private boolean backtracking(int currY, int currX, int currIdx, char[][] board, String word) {
        if (currIdx == word.length()) {
            return true;
        }

        for (int d = 0; d < 4; d++) {
            int nextY = currY + DY[d];
            int nextX = currX + DX[d];

            if (nextY < 0 || nextY >= m || nextX < 0 || nextX >= n || visited[nextY][nextX])
                continue;

            if (board[nextY][nextX] == word.charAt(currIdx)) {
                visited[nextY][nextX] = true;
                if (backtracking(nextY, nextX, currIdx + 1, board, word)) {
                    return true;
                }
                visited[nextY][nextX] = false;
            }
        }

        return false;
    }
}
