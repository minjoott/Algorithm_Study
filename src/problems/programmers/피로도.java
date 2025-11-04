package problems.programmers;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/87946
 * [Programmers] 87946. 피로도
 * solved at: 251104
 */

public class 피로도 {
    class Solution {
        int N;
        int maxCnt = 0;  //answer

        public int solution(int k, int[][] dungeons) {
            N = dungeons.length;
            backtracking(0, new boolean[N], k, dungeons);
            return maxCnt;
        }

        void backtracking(int cnt, boolean[] visited, int k, int[][] dungeons) {
            maxCnt = Math.max(cnt, maxCnt);

            for (int i = 0; i < N; i++) {
                if (!visited[i] && dungeons[i][0] <= k) {
                    visited[i] = true;
                    backtracking(cnt + 1, visited, k - dungeons[i][1], dungeons);
                    visited[i] = false;
                }
            }
        }
    }
}