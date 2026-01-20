package problems.programmers;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/76502
 * [Programmers] 76502. 괄호 회전하기
 * solved at: 260120
 */

import java.util.ArrayDeque;
import java.util.Deque;

public class 괄호회전하기 {
    class Solution1 {
        public int solution(String s) {
            Deque<Character> queue = new ArrayDeque<>();
            for (char c : s.toCharArray()) queue.add(c);

            int answer = 0;
            for (int i = 0; i < s.length(); i++) {
                if (isCorrect(queue)) answer++;
                char c = queue.remove();
                queue.add(c);
            }
            return answer;
        }

        boolean isCorrect(Deque<Character> queue) {
            Deque<Character> stack = new ArrayDeque<>();
            for (char c : queue) {
                if (!stack.isEmpty() && stack.peek() == pair(c)) stack.pop();
                else stack.push(c);
            }
            return (stack.isEmpty()) ? true : false;
        }

        char pair(char a) {
            if (a == ')') return '(';
            else if (a == ']') return '[';
            else if (a == '}') return '{';
            else return 'X';
        }
    }
}
