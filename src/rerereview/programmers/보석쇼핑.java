package rerereview.programmers;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/67258
 * 67258. [카카오 인턴] 보석 쇼핑
 * 2025/09/13
 */

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class 보석쇼핑 {
    public int[] solution(String[] gems) {
        final int N = gems.length;
        final int K = new HashSet<>(Arrays.asList(gems)).size();

        Map<String, Integer> freq = new HashMap<>();
        int shortestLength = 100000;
        int ansL = 0, ansR = 0;
        int l = 0;
        for (int r = 0; r < N; r++) {
            String gemL = gems[r];
            freq.put(gemL, freq.getOrDefault(gemL, 0) + 1);

            if (freq.size() == K) {
                while (freq.get(gems[l]) != 1) {
                    String gemR = gems[l];
                    freq.put(gemR, freq.get(gemR) - 1);
                    l++;
                }
                int length = r - l;
                if (length < shortestLength) {
                    shortestLength = length;
                    ansL = l; ansR = r;
                }
            }
        }
        return new int[]{ansL + 1, ansR + 1};
    }
}
