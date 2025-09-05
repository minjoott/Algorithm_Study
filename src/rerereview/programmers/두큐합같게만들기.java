package rerereview.programmers;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/118667
 * 118667. 두 큐 합 같게 만들기
 * 2025/08/31, 2025/09/04
 */

import java.util.ArrayDeque;
import java.util.Deque;

class 두큐합같게만들기_queue {

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

class 두큐합같게만들기_slidingWindow {
    public int solution(int[] queue1, int[] queue2) {
        int n = queue1.length;

        int[] arr = new int[2 * n];
        long sum1 = 0, sum2 = 0;
        for (int i = 0; i < n; i++) {
            int v1 = queue1[i], v2 = queue2[i];
            arr[i] = v1; arr[n + i] = v2;
            sum1 += v1; sum2 += v2;
        }
        if ((sum1 + sum2) % 2 != 0) return -1;

        int ptr1 = 0, ptr2 = n;
        int cnt = 0;
        while (cnt < 3 * n) {
            if (sum1 == sum2) {
                return cnt;
            }
            else if (sum1 > sum2) {
                int v = arr[ptr1];
                sum1 -= v; sum2 += v;
                ptr1 = (ptr1 + 1) % arr.length;
                ++cnt;
            }
            else {
                int v = arr[ptr2];
                sum2 -= v; sum1 += v;
                ptr2 = (ptr2 + 1) % arr.length;
                ++cnt;
            }
        }
        return -1;
    }
}