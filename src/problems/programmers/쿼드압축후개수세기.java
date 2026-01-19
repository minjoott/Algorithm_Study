package problems.programmers;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/68936
 * [Programmers] 68936. 쿼드압축 후 개수 세기
 * solved at: 260119
 */

public class 쿼드압축후개수세기 {
    class Solution1 {
        int zeroCnt = 0;
        int oneCnt = 0;

        public int[] solution(int[][] arr) {
            compress(0, 0, arr.length, arr);
            return new int[]{zeroCnt, oneCnt};
        }

        void compress(int baseR, int baseC, int length, int[][] arr) {
            boolean result = true;
            int baseNum = arr[baseR][baseC];
            for (int i = 0; i < length; i++) {
                for (int j = 0; j < length; j++) {
                    if (arr[baseR + i][baseC + j] != baseNum) {
                        result = false;
                        break;
                    }
                }
                if (!result) break;
            }
            if (!result) {
                compress(baseR, baseC, length / 2, arr);
                compress(baseR, baseC + length / 2, length / 2, arr);
                compress(baseR + length / 2, baseC, length / 2, arr);
                compress(baseR + length / 2, baseC + length / 2, length / 2, arr);
            }
            else {
                if (arr[baseR][baseC] == 0) zeroCnt++;
                else oneCnt++;
            }
            return;
        }
    }
}
