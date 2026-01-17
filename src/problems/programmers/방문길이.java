package problems.programmers;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/49994
 * [Programmers] 49994. 방문 길이
 * solved at: 260117
 */

public class 방문길이 {
    class Solution1 {
        public int solution(String dirs) {
            int answer = 0;
            boolean[][][][] visited = new boolean[11][11][11][11];
            int r = 0;
            int c = 0;
            for (char d : dirs.toCharArray()) {
                int nr = r;
                int nc = c;
                if (d == 'U') nr++;
                else if (d == 'D') nr--;
                else if (d == 'R') nc++;
                else nc--;  //'L'

                if (nr < -5 || nr > 5 || nc < -5 || nc > 5) continue;

                int nrIdx = (nr < 0) ? (nr - 5) * -1 : nr;
                int ncIdx = (nc < 0) ? (nc - 5) * -1 : nc;

                int rIdx = (r < 0) ? (r - 5) * -1 : r;
                int cIdx = (c < 0) ? (c - 5) * -1 : c;

                if (!visited[rIdx][cIdx][nrIdx][ncIdx]) {
                    answer++;
                    visited[rIdx][cIdx][nrIdx][ncIdx] = true;
                    visited[nrIdx][ncIdx][rIdx][cIdx] = true;
                }

                r = nr;
                c = nc;
            }
            return answer;
        }
    }
}
