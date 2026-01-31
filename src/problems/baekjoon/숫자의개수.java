package problems.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * https://www.acmicpc.net/problem/2577
 * [Baekjoon] 2577. 숫자의 개수
 * solved at: 260131
 */

public class 숫자의개수 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int A = Integer.parseInt(br.readLine());
        int B = Integer.parseInt(br.readLine());
        int C = Integer.parseInt(br.readLine());

        String result = (long) A * B * C + "";
        int[] count = new int[10];
        for (char ch : result.toCharArray()) {
            int num = Integer.parseInt(ch + "");
            count[num]++;
        }

        for (int i = 0; i < 10; i++) {
            System.out.println(count[i]);
        }
        return;
    }
}
