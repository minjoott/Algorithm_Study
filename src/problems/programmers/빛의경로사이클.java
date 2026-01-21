package problems.programmers;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/86052
 * [Programmers] 86052. 빛의 경로 사이클
 * solved at: 260121
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class 빛의경로사이클 {
    class Solution1 {
        int[] DR = {-1, 0, 1, 0};
        int[] DC = {0, 1, 0, -1};

        public int[] solution(String[] grid) {
            int R = grid.length;  //행의 개수
            int C = grid[0].length();  //열의 개수

            List<Integer> cycles = new ArrayList<>();
            boolean[][][] visited = new boolean[R][C][4];
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    for (int d = 0; d < 4; d++) {
                        if (visited[i][j][d]) continue;

                        int len = 0;
                        int r = i, c = j, dir = d;
                        while (!visited[r][c][dir]) {
                            visited[r][c][dir] = true;
                            len++;

                            r = (r + DR[dir] + R) % R;
                            c = (c + DC[dir] + C) % C;

                            char ch = grid[r].charAt(c);
                            if (ch == 'L') dir = (dir + 1) % 4;
                            else if (ch == 'R') dir = (dir + 3) % 4;
                        }
                        cycles.add(len);
                    }
                }
            }

            Collections.sort(cycles);
            int[] answer = new int[cycles.size()];
            for (int i = 0; i < cycles.size(); i++) answer[i] = cycles.get(i);
            return answer;
        }
    }
}
