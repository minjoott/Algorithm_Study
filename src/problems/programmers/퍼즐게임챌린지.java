package problems.programmers;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/340212
 * [Programmers] 340212. 퍼즐 게임 챌린지
 * solved at: 251121
 */

public class 퍼즐게임챌린지 {
    class Solution {
        public int solution(int[] diffs, int[] times, long limit) {
            int n = diffs.length;

            int l = 1;
            int r = 100_000;

            int answer = -1;
            while (l <= r) {
                int level = l + (r - l) / 2;
                long totalTime = times[0];
                for (int i = 1; i < n; i++) {
                    if (totalTime > limit) break;

                    if (diffs[i] <= level) totalTime += times[i];
                    else totalTime += (long) ((diffs[i] - level) * (times[i] + times[i - 1])) + times[i];
                }
                if (totalTime <= limit) {
                    r = level - 1;
                    answer = level;
                } else {
                    l = level + 1;
                }
            }
            return l;
        }
    }
}
