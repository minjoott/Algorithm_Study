package review.programmers;

import java.util.HashSet;
import java.util.Set;

public class 피로도 {
    private int ans = 0;

    public int solution(int k, int[][] dungeons) {
        return backtracking(k, 0, new HashSet<>(), dungeons);
    }

    private int backtracking(int k, int max, Set<Integer> visited, int[][] dungeons) {
        ans = Math.max(max, ans);
        if (ans == dungeons.length) {
            return ans;
        }

        for (int d = 0; d < dungeons.length; d++) {
            if (!visited.contains(d) && dungeons[d][0] <= k) {
                visited.add(d);
                backtracking(k - dungeons[d][1], max + 1, visited, dungeons);
                visited.remove(d);
            }
        }

        return ans;
    }
}
