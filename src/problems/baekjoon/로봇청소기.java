package problems.baekjoon;

/**
 * https://www.acmicpc.net/problem/14503
 * [Baekjoon] 14503. 로봇 청소기
 * solved at: 260131
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 로봇청소기 {
    static final int[] DR = {-1, 0, 1, 0};
    static final int[] DC = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int cr = Integer.parseInt(st.nextToken());
        int cc = Integer.parseInt(st.nextToken());
        int cd = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];  // 0: 아직 청소 안 된 빈 칸, -1: 청소된 빈 칸, 1: 벽
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int count = 0;  //청소하는 칸의 개수 (답)
        while (true) {
            //step1
            if (map[cr][cc] == 0) {
                map[cr][cc] = -1;
                count++;
            }

            //step2: 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸 유무 체크
            boolean exit = false;
            for (int d = 0; d < 4; d++) {
                int nr = cr + DR[d], nc = cc + DC[d];
                if (map[nr][nc] == 0) {
                    exit = true;
                    break;
                }
            }

            //step3
            if (!exit) {  //주변 4칸 중 청소되지 않은 빈 칸이 없는 경우
                int back = (cd + 2) % 4;
                cr += DR[back];
                cc += DC[back];
                if (map[cr][cc] == 1) break;
            }
            else {  //주변 4칸 중 청소되지 않은 빈 칸이 있는 경우
                cd = (cd + 3) % 4;
                int fr = cr + DR[cd];
                int fc = cc + DC[cd];
                if (map[fr][fc] == 0) {
                    cr = fr;
                    cc = fc;
                }
            }
        }
        System.out.println(count);
        return;
    }
}
