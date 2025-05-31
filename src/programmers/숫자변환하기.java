package programmers;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

class 숫자변환하기_bfs {
    public int solution(int x, int y, int n) {
        Deque<int[]> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[y + 1];

        visited[x] = true;
        queue.offer(new int[]{x, 0});

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int num = curr[0];
            int cnt = curr[1];

            if (num == y)
                return cnt;

            int[] nextCase = new int[]{num + n, num * 2, num * 3};
            for (int next : nextCase) {
                if (next <= y && !visited[next]) {
                    visited[next] = true;
                    queue.offer(new int[]{next, cnt + 1});
                }
            }
        }

        return -1;
    }
}

class 숫자변환하기_dp {
    public int solution(int x, int y, int n) {
        int[] dp = new int[y + 1];
        Arrays.fill(dp, -1);

        if (x == y)
            return 0;

        dp[x] = 0;

        for (int i = x; i <= y; i++) {
            if (dp[i] == -1) continue;

            int currCnt = dp[i];
            int[] nextArr = {i + n, i * 2, i * 3};

            for (int next : nextArr) {
                if (next <= y && (dp[next] == -1 || currCnt + 1 < dp[next])) {
                    dp[next] = currCnt + 1;

                    if (next == y) {
                        return dp[next];
                    }
                }
            }
        }

        return -1;
    }
}