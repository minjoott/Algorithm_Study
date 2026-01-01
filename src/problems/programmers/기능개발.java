package problems.programmers;

import java.util.ArrayList;
import java.util.List;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/42586
 * [Programmers] 42586. 기능 개발
 * solved at: 260101
 */

public class 기능개발 {

    class Solution1 {
        public int[] solution(int[] progresses, int[] speeds) {
            int n = progresses.length;

            List<Integer> result = new ArrayList<>();
            int prevFuncDepDay = -1;
            int needDepFuncCnt = 0;
            for (int i = 0; i < n; i++) {
                int remainDevPercent = 100 - progresses[i];
                int currDevEndDay = remainDevPercent / speeds[i];
                if (remainDevPercent % speeds[i] != 0) currDevEndDay++;

                if (needDepFuncCnt != 0 && prevFuncDepDay < currDevEndDay) {
                    result.add(needDepFuncCnt);
                    needDepFuncCnt = 0;
                }

                if (prevFuncDepDay < currDevEndDay) prevFuncDepDay = currDevEndDay;
                needDepFuncCnt++;
            }

            if (needDepFuncCnt > 0) result.add(needDepFuncCnt);

            int[] answer = new int[result.size()];
            for (int i = 0; i < result.size(); i++) answer[i] = result.get(i);
            return answer;
        }
    }
}
