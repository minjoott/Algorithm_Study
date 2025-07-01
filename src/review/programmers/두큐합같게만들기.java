package review.programmers;

public class 두큐합같게만들기 {
    public int solution(int[] q1, int[] q2) {
        final int n = q1.length;

        long sum1 = 0, sum2 = 0;
        for (int x : q1) sum1 += x;
        for (int x : q2) sum2 += x;

        long total = sum1 + sum2;
        if (total % 2 != 0) return -1;
        long target = total / 2;

        int[] arr = new int[2 * n];
        for (int i = 0; i < n; i++) {
            arr[i] = q1[i];
            arr[n + i] = q2[i];
        }

        int l = 0, r = n, moves = 0;
        while (moves < 3 * n) {
            if (sum1 == target) {
                return moves;
            } else if (sum1 < target) {
                if (r >= 2 * n) break;
                sum1 += arr[r++];
            } else {
                if (l >= 2 * n) break;
                sum1 -= arr[l++];
            }
            moves++;
        }
        return -1;
    }
}
