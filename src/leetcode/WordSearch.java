package leetcode;

public class WordSearch {
    public boolean exist(char[][] board, String word) {
        int m = board.length, n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == word.charAt(0)) {
                    boolean[][] visited = new boolean[m][n];
                    visited[i][j] = true;
                    if (backtracking(i, j, 1, board, word, visited))
                        return true;
                }
            }
        }

        return false;
    }

    int[] dy = {1, 0, -1, 0};
    int[] dx = {0, 1, 0, -1};

    boolean backtracking(int y, int x, int findIdx, char[][] board, String word, boolean[][] visited) {
        if (findIdx == word.length())
            return true;

        for (int d = 0; d < 4; d++) {
            int nextY = y + dy[d];
            int nextX = x + dx[d];

            int m = board.length, n = board[0].length;
            if (nextY >= m || nextY < 0 || nextX >= n || nextX < 0 || visited[nextY][nextX]) continue;

            if (board[nextY][nextX] == word.charAt(findIdx)) {
                visited[nextY][nextX] = true;
                if (backtracking(nextY, nextX, findIdx + 1, board, word, visited))
                    return true;
                visited[nextY][nextX] = false;
            }
        }

        return false;
    }
}
