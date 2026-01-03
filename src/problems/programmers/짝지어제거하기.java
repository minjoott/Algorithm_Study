package problems.programmers;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/12973
 * [Programmers] 12973. 짝지어 제거하기
 * solved at: 260103
 */

import java.util.ArrayDeque;
import java.util.Deque;

public class 짝지어제거하기 {
    class Solution1 {
        public int solution(String s) {

            if (s.length() % 2 != 0) return 0;

            Deque<Character> stack = new ArrayDeque<>();
            for (char c : s.toCharArray()) {
                if (!stack.isEmpty() && stack.peek() == c)
                    stack.pop();
                else
                    stack.push(c);
            }

            if (stack.isEmpty()) return 1;
            else return 0;
        }
    }
}
