package problems.programmers;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/70129
 * [Programmers] 70129. 이진 변환 반복하기
 * solved at: 250120
 */

public class 이진변환반복하기 {
    class Solution1 {
        int removedZeroCnt = 0;
        int convertCnt = 0;

        public int[] solution(String s) {
            makeOne(s);
            return new int[]{convertCnt, removedZeroCnt};
        }

        void makeOne(String s) {
            if (s.equals("1")) return;

            int zeroCnt = 0;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '0') zeroCnt++;
            }
            removedZeroCnt += zeroCnt;

            int oneCnt = s.length() - zeroCnt;
            StringBuilder sb = new StringBuilder();
            while (oneCnt > 0) {
                sb.append(oneCnt % 2);
                oneCnt /= 2;
            }

            String newS = sb.reverse().toString();
            convertCnt++;
            makeOne(newS);
        }
    }
}
