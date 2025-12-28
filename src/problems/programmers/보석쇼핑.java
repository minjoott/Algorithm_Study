package problems.programmers;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/67258
 * [Programmers] 67258. 보석 쇼핑 (2020 카카오 인턴십)
 * solved at : 251228
 */

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class 보석쇼핑 {
    class Solution1 {
        public int[] solution(String[] gems) {
            Set<String> category = new HashSet<>();
            for (String gem : gems) category.add(gem);
            int k = category.size();  //모든 종류의 개수

            int ans_length = 100001;
            int ans_start = -1;
            int ans_end = -1;
            Map<String, Integer> curr = new HashMap<>();
            int start = 0;
            for (int end = 0; end < gems.length; end++) {
                curr.put(gems[end], curr.getOrDefault(gems[end], 0) + 1);

                if (curr.size() == k) {
                    while (curr.get(gems[start]) > 1 && start < end) {
                        curr.put(gems[start], curr.get(gems[start]) - 1);
                        start++;
                    }

                    int curr_length = end - start + 1;
                    if (curr_length < ans_length) {
                        ans_start = start;
                        ans_end = end;
                        ans_length = curr_length;
                    }

                    curr.remove(gems[start]);
                    start++;
                }
            }

            return new int[]{ans_start + 1, ans_end + 1};
        }
    }
}
