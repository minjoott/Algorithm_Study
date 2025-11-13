package problems.programmers;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/42746
 * [Programmers] 42746. 가장 큰 수
 * solved at: 251112
 */

import java.util.Arrays;

public class 가장큰수 {
    class Solution {
        public String solution(int[] numbers) {
            String[] arr = new String[numbers.length];
            for (int i = 0; i < numbers.length; i++) {
                arr[i] = String.valueOf(numbers[i]);
            }

            Arrays.sort(arr, (a, b) -> (b + a).compareTo(a + b));

            if (Integer.parseInt(arr[0]) == 0) return "" + 0;

            StringBuilder sb = new StringBuilder();
            for (String n : arr) sb.append(n);
            return sb.toString();
        }
    }
}
