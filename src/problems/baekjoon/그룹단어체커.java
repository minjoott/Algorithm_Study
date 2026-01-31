package problems.baekjoon;

/**
 * https://www.acmicpc.net/problem/1316
 * [Baekjoon] 1316. 그룹 단위 체커
 * solved at: 260131
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class 그룹단어체커 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int answer = 0;
        for (int i = 0; i < N; i++) {
            String word = br.readLine();
            Set<Character> group = new HashSet<>();
            char prev = word.charAt(0);
            boolean isGroup = true;
            for (char curr : word.toCharArray()) {
                if (curr != prev) {
                    if (group.contains(curr)) {
                        isGroup = false;
                        break;
                    }
                    group.add(prev);
                    prev = curr;
                }
            }
            if (isGroup) answer++;
        }

        System.out.println(answer);
        return;
    }
}