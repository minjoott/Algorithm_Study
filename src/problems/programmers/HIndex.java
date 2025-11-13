package problems.programmers;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/42747
 * [Programmers] 42747. H-Index
 * solved at: 251112
 */

import java.util.Arrays;

public class HIndex {
    class Solution {
        public int solution(int[] citations) {
            int n = citations.length;
            Arrays.sort(citations);

            int answer = 0;
            for (int i = 0; i < n; i++) {
                int h = n - i;
                if (citations[i] >= h) {
                    answer = h;
                    break;
                }
            }
            return answer;
        }
    }
}
