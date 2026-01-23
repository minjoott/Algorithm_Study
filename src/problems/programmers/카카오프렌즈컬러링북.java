package problems.programmers;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/1829
 * [Programmers] 1829. 카카오프렌즈 컬러링북
 * solved at: 260123
 */

public class 카카오프렌즈컬러링북 {
    class Solution1 {
        public int[] solution(int m, int n, int[][] picture) {
            int totalCnt = 0;
            int maxSize = 0;

            boolean[][] visited = new boolean[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (picture[i][j] != 0 && !visited[i][j]) {
                        int size = extract(i, j, picture[i][j], visited, picture);
                        if (size > maxSize) maxSize = size;
                        totalCnt++;
                    }
                }
            }
            return new int[]{totalCnt, maxSize};
        }

        int[] DR = {1, 0, -1, 0};
        int[] DC = {0, 1, 0, -1};

        int extract(int r, int c, int color, boolean[][] visited, int[][] picture) {
            visited[r][c] = true;

            int size = 1;
            for (int d = 0; d < 4; d++) {
                int nr = r + DR[d], nc = c + DC[d];

                if (nr < 0 || nr >= picture.length || nc < 0 || nc >= picture[0].length
                        || visited[nr][nc] || picture[nr][nc] != color) continue;

                size += extract(nr, nc, color, visited, picture);
            }
            return size;
        }
    }
}
