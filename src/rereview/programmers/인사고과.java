package rereview.programmers;

import java.util.Arrays;

public class 인사고과 {
    public int solution(int[][] scores) {
        int ta = scores[0][0], tb = scores[0][1];
        int tsum = ta + tb;
        int rank = 1;

        Arrays.sort(scores, (a, b) -> {
            if (a[0] - b[0] != 0) return b[0] - a[0];
            return a[1] - b[1];
        });

        int maxB = -1;

        for (int[] s : scores) {
            int a = s[0], b = s[1];

            if (b < maxB) {
                if (a == ta && b == tb) return -1;
                continue;
            }

            maxB = Math.max(b, maxB);

            if (a + b > tsum) ++rank;
        }

        return rank;
    }
}
