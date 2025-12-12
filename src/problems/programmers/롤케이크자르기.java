package problems.programmers;

import java.util.HashSet;
import java.util.Set;

public class 롤케이크자르기 {
    class Solution {
        public int solution(int[] topping) {
            Set<Integer> rightSet = new HashSet<>();
            int[] rightCnt = new int[10001];
            for (int t : topping) {
                rightSet.add(t);
                rightCnt[t]++;
            }

            int answer = 0;
            Set<Integer> leftSet = new HashSet<>();
            for (int t : topping) {
                leftSet.add(t);

                rightCnt[t]--;
                if (rightCnt[t] == 0) rightSet.remove(t);

                if (leftSet.size() == rightSet.size()) answer++;
            }

            return answer;
        }
    }
}
