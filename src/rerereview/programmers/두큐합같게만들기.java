package rerereview.programmers;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/118667
 *
 */

import java.util.ArrayDeque;
import java.util.Deque;

public class 두큐합같게만들기 {

    public int solution(int[] queue1, int[] queue2) {
        int n = queue1.length;

        Deque<Integer> q1 = new ArrayDeque<>();
        Deque<Integer> q2 = new ArrayDeque<>();
        long sum1 = 0;
        long sum2 = 0;
        for (int i = 0; i < n; i++) {
            int v1 = queue1[i];
            q1.add(v1);
            sum1 += v1;
            int v2 = queue2[i];
            q2.add(v2);
            sum2 += v2;
        }

        int ans = 0;
        for (int i = 0; i < 3 * n; i++) {
            if (sum1 == sum2) {
                return ans;
            } else if (sum1 < sum2) {
                int v = q2.remove();
                q1.add(v);
                sum2 -= v;
                sum1 += v;
                ++ans;
            } else {
                int v = q1.remove();
                q2.add(v);
                sum1 -= v;
                sum2 += v;
                ++ans;
            }
        }
        return -1;
    }
}
