package rerereview.programmers;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/87946
 * 87946. 피로도
 * 2025/08/28
 */

public class 피로도 {
    int ans = 0;

    public int solution(int k, int[][] dungeons) {
        backtracking(0, new boolean[dungeons.length], k, dungeons);
        return ans;
    }

    boolean backtracking(int cnt, boolean[] visited, int k, int[][] dungeons) {
        if (cnt == dungeons.length) return true;

        for (int i = 0; i < dungeons.length; i++) {
            if (visited[i]) continue;

            if (k >= dungeons[i][0]) {
                ans = Math.max(cnt + 1, ans);

                visited[i] = true;
                if (backtracking(cnt + 1, visited, k - dungeons[i][1], dungeons)) {
                    return true;
                }
                visited[i] = false;
            }
        }
        return false;
    }
}
