package programmers;

import java.util.*;

public class 귤고르기 {
    public int solution(int k, int[] tangerines) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int tangerine : tangerines) {
            map.put(tangerine, map.getOrDefault(tangerine, 0) + 1);
        }

        List<int[]> list = new ArrayList<>();
        for (int size : map.keySet()) {
            list.add(new int[]{size, map.get(size)});
        }
        Collections.sort(list, (a, b) -> b[1] - a[1]);

        int cnt = 0;  //answer
        for (int[] tangerine : list) {
            cnt++;
            k -= tangerine[1];
            if (k <= 0) break;
        }

        return cnt;
    }
}
