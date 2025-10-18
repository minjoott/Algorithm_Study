package problems.backjoon;

/**
 * https://www.acmicpc.net/problem/20055
 * [Backjoon] 20055. 컨베이터 벨트 위의 로봇
 * solved at: 251017
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 컨테이너벨트위의로봇 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int M = 2 * N;

        int[] power = new int[2 * N];
        boolean[] isRobot = new boolean[2 * N];
        int up = 0;  //올리는 칸
        int down = N - 1;  //내리는 칸
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 2 * N; i++) {
            power[i] = Integer.parseInt(st.nextToken());
        }

        int zeroCnt = 0;
        int answer = 0;
        while (true) {
            answer++;

            //한 칸 회전
            up = (up - 1 + M) % M;
            down = (down - 1 + M) % M;

            if (isRobot[down]) isRobot[down] = false;
            //로봇 이동
            for (int curr = (down - 1 + M) % M; curr != (up - 1 + M) % M; curr = (curr - 1 + M) % M) {
                if (isRobot[curr]) {
                    int next = (curr + 1) % M;
                    if (!isRobot[next] && power[next] > 0) {
                        isRobot[next] = true;
                        isRobot[curr] = false;
                        if (--power[next] == 0) zeroCnt++;
                    }
                }
            }
            if (isRobot[down]) isRobot[down] = false;

            //로봇 올리기
            if (power[up] > 0) {
                isRobot[up] = true;
                if (--power[up] == 0) zeroCnt++;
            }

            //종료 조건
            if (zeroCnt >= K) {
                System.out.println(answer);
                return;
            }
        }
    }
}
