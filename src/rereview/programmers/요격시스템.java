package rereview.programmers;

import java.util.Arrays;

public class 요격시스템 {
    public int solution(int[][] targets) {
        Arrays.sort(targets, (a, b) -> a[1] - b[1]);

        int answer = 0;
        int ptr = 0;
        while (ptr < targets.length) {
            int[] curr = targets[ptr++];
            ++answer;

            while (ptr < targets.length && targets[ptr][0] < curr[1]) {
                ++ptr;
            }
        }

        return answer;
    }
}
