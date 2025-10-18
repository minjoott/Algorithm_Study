package problems.backjoon;

/**
 * https://www.acmicpc.net/problem/20437
 * [Backjoon] 20437. 문자열 게임 2
 * solved at: 251018
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class 문자열게임2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        //T번의 게임 진행
        for (int t = 0; t < N; t++) {
            st = new StringTokenizer(br.readLine());
            String W = st.nextToken();
            st = new StringTokenizer(br.readLine());
            int K = Integer.parseInt(st.nextToken());

            //step1. W가 포함하고 있는 알파벳 소문자 종류 파악하기
            char[] strArr = W.toCharArray();
            Map<Character, Integer> kinds = new HashMap<>();
            for (char c : strArr) {
                kinds.put(c, kinds.getOrDefault(c, 0) + 1);
            }

            int minLen = 100001;
            int maxLen = 0;
            //step2. K개 이상 포함된 문자마다 K개를 포함하고 조건을 만족하는 연속 문자열의 minLen과 maxLen 찾기
            for (char c : kinds.keySet()) {
                if (kinds.get(c) >= K) {
                    int l = 0;
                    int r = 0;
                    int cnt = 0;
                    while (r < W.length()) {
                        if (W.charAt(r++) == c) {  //c를 만날 때까지 r++ (만난 이후 오른쪽으로 한 칸 더 이동)
                            if (++cnt == K) {
                                while (W.charAt(l++) != c) ;  //c를 만날 때까지 l++, 만난 이후 오른쪽으로 한 칸 더 이동
                                int len = (r - 1) - (l - 1) + 1;
                                minLen = Math.min(len, minLen);
                                maxLen = Math.max(len, maxLen);
                                cnt--;
                            }
                        }
                    }
                }
            }

            //step3. 정답 출력
            if (minLen == 100001 && maxLen == 0) System.out.println(-1);
            else System.out.println(minLen + " " + maxLen);
        }
    }
}
