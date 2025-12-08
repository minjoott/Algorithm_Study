package problems.baekjoon;

/**
 * https://www.acmicpc.net/problem/2133
 * [Backjoon] 2133. 타일 채우기
 * solved at: 251207
 */

import java.util.Scanner;

public class 타일채우기 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        if (N % 2 != 0) {
            System.out.println(0);
            return;
        }

        int[] dp = new int[N + 1];
        dp[0] = 1;
        dp[2] = 3;
        for (int i = 4; i <= N; i += 2) {
            dp[i] = dp[i - 2] * 3;
            for (int j = 0; j < i - 2; j += 2) {
                dp[i] += dp[j] * 2;
            }
        }
        System.out.println(dp[N]);
        return;
    }
}
