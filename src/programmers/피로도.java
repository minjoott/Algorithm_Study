package programmers;

public class 피로도 {
    static int ans = 0;

    public int solution(int k, int[][] dungeons) {
        backtracking(k, 0, dungeons, new boolean[dungeons.length]);
        return ans;
    }

    boolean backtracking(int k, int cnt, int[][] dungeons, boolean[] visited) {
        if (cnt > ans) ans = cnt;
        else if (cnt == dungeons.length) return true;

        for (int i = 0; i < dungeons.length; i++) {
            if (!visited[i] && k >= dungeons[i][0]) {
                visited[i] = true;
                if (backtracking(k - dungeons[i][1], cnt + 1, dungeons, visited))
                    return true;
                visited[i] = false;
            }
        }

        return false;
    }
}
