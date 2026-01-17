package problems.programmers;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/12980
 * [Programmers] 12980. 점프와 순간 이동
 * solved at: 260117
 */

public class 점프와순간이동 {
    public class Solution1 {
        public int solution(int n) {
            int answer = 0;
            while (n > 0) {
                if (n % 2 == 0) {  //짝수
                    n /= 2;
                }
                else {  //홀수
                    n--;
                    answer++;
                }
            }
            return answer;
        }
    }
}
