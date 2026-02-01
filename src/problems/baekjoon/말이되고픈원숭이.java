package problems.baekjoon;

/**
 * https://www.acmicpc.net/probl
 * [Baekjoon] 1600. 말이 되고픈 원숭이
 * solved at: 260201
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/** # 문제
 * - 원숭이는 인접한 칸으로만 움직일 수 있고, 단 K번만 장애물을 뛰어넘어 원숭이처럼 움직일 수 있다.
 * - 원숭이는 격자판의 맨 왼쪽 위에서 맨 오른쪽 아래까지 가야 한다.
 * - 입력: 0은 평지, 1은 장애물
 * - 출력: 원숭이의 동작수의 ""최솟값"", 도착점 불가면 -1
 */
public class 말이되고픈원숭이 {
    static int[] DR = {1, 0, -1, 0};
    static int[] DC = {0, 1, 0, -1};
    static int[] HDR = {2, 1, -1, -2, -2, -1, 1, 2};
    static int[] HDC = {1, 2, 2, 1, -1, -2, -2, -1};

    static int H;
    static int W;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int K = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        int[][] map = new int[H][W];
        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < W; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Deque<Entry> queue = new ArrayDeque<>();
        boolean[][][] visited = new boolean[H][W][K + 1];
        queue.add(new Entry(0, 0, K, 0));
        visited[0][0][K] = true;

        int answer = -1;
        while (!queue.isEmpty()) {
            Entry curr = queue.remove();

            if (curr.r == H - 1 && curr.c == W - 1) {
                answer = curr.cnt;
                break;
            }

            for (int d = 0; d < 4; d++) {
                int nr = curr.r + DR[d], nc = curr.c + DC[d];

                if (nr < 0 || nr >= H || nc < 0 || nc >= W) continue;
                if (visited[nr][nc][curr.k]) continue;
                if (map[nr][nc] == 1) continue;

                queue.add(new Entry(nr, nc, curr.k, curr.cnt + 1));
                visited[nr][nc][curr.k] = true;
            }

            if (curr.k <=  0) continue;

            for (int d = 0; d < 8; d++) {
                int nr = curr.r + HDR[d], nc = curr.c + HDC[d];

                if (nr < 0 || nr >= H || nc < 0 || nc >= W) continue;
                if (visited[nr][nc][curr.k - 1]) continue;
                if (map[nr][nc] == 1) continue;

                queue.add(new Entry(nr, nc, curr.k - 1, curr.cnt + 1));
                visited[nr][nc][curr.k - 1] = true;
            }
        }

        System.out.println(answer);
    }

    static class Entry {
        int r, c;
        int k;
        int cnt;

        Entry (int r, int c, int k, int cnt) {
            this.r = r; this.c = c;
            this.k = k;
            this.cnt = cnt;
        }
    }

}
