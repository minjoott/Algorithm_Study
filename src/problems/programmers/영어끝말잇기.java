package problems.programmers;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/12981
 * [Programmers] 12981. 영어 끝말잇기
 * solved at: 260114
 */

import java.util.HashSet;
import java.util.Set;

public class 영어끝말잇기 {
    class Solution1 {
        public int[] solution(int n, String[] words) {
            int answer[] = new int[]{0, 0};

            Set<String> set = new HashSet<>();
            char prevChar = words[0].charAt(0);
            for (int i = 0; i < words.length; i++) {
                String curr = words[i];
                if (prevChar != curr.charAt(0) || set.contains(curr)) {
                    answer[0] = i % n + 1;
                    answer[1] = i / n + 1;
                    break;
                }
                prevChar = curr.charAt(curr.length() - 1);
                set.add(curr);
            }
            return answer;
        }
    }
}
