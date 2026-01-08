package problems.programmers;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/12985
 * [Programmers] 12985. 예상 대진표
 * solved at: 250106
 */

public class 예상대진표 {
    class Solution1 {
        public int solution(int n, int a, int b) {
            int round = 0;
            while (a != b) {
                a = (a + 1) / 2;
                b = (b + 1) / 2;
                round++;
            }
            return round;
        }
    }
}
