package problems.programmers;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/42840
 * [Programmers] 42840. 모의고사
 * solved at: 251113
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class 모의고사 {
    class Solution {
        public int[] solution(int[] answers) {
            int n = answers.length;

            int[][] roles = {
                    {1, 2, 3, 4, 5},  //5
                    {2, 1, 2, 3, 2, 4, 2, 5},  //8
                    {3, 3, 1, 1, 2, 2, 4, 4, 5, 5}  //10
            };

            int[] successCnt = new int[3];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < 3; j++) {  //수포자 j의 i번 문제 채점
                    int divideNum = roles[j].length;
                    if (roles[j][i % divideNum] == answers[i]) successCnt[j]++;
                }
            }

            int maxSuccessCnt = 0;
            for (int cnt : successCnt) maxSuccessCnt = Math.max(cnt, maxSuccessCnt);
            List<Integer> answerList = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                if (successCnt[i] == maxSuccessCnt) answerList.add(i);
            }
            Collections.sort(answerList);
            int[] answerArr = new int[answerList.size()];
            for (int i = 0; i < answerList.size(); i++) answerArr[i] = answerList.get(i) + 1;

            return answerArr;
        }
    }
}
