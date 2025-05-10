package programmers;

import java.util.Arrays;

public class 요격시스템 {
    public int solution(int[][] targets) {
        Arrays.sort(targets, (a, b) -> a[1] - b[1]);

        int shieldEnd = -1;
        int shieldCnt = 0;  // answer
        for (int[] target : targets) {
            if (target[0] < shieldEnd) continue;

            shieldCnt++;
            shieldEnd = target[1];
        }

        return shieldCnt;
    }
}
