package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class 구슬탈출 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] NM = br.readLine().split(" ");
        int N = Integer.parseInt(NM[0]);
        int M = Integer.parseInt(NM[1]);

        char[][] map = new char[N][M];
        int[] startRB = new int[5];
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j);

                if (map[i][j] == 'R') {
                    startRB[0] = i;
                    startRB[1] = j;
                }
                else if (map[i][j] == 'B') {
                    startRB[2] = i;
                    startRB[3] = j;
                }
            }
        }

        Deque<int[]> queue = new ArrayDeque<>();
        queue.add(startRB);

        int[] dy = {1, 0, -1, 0};
        int[] dx = {0, 1, 0, -1};
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            if (curr[4] >= 10) {
                System.out.println(0);
                return;
            }

            for (int d = 0; d < 4; d++) {
                int[] R = new int[]{curr[0], curr[1]};
                int[] B = new int[]{curr[2], curr[3]};

                boolean R_isHole = false;
                int R_cnt = 0;
                do {
                    R[0] += dy[d];
                    R[1] += dx[d];
                    R_cnt++;

                    if (map[R[0]][R[1]] == 'O') {
                        R_isHole = true;
                        break;
                    }
                } while (map[R[0]][R[1]] != '#');
                R[0] -= dy[d];
                R[1] -= dx[d];

                boolean B_isHole = false;
                int B_cnt = 0;
                do {
                    B[0] += dy[d];
                    B[1] += dx[d];
                    B_cnt++;

                    if (map[B[0]][B[1]] == 'O') {
                        B_isHole = true;
                        break;
                    }
                } while (map[B[0]][B[1]] != '#');
                B[0] -= dy[d];
                B[1] -= dx[d];

                if (B_isHole)
                    continue;
                if (R_isHole) {
                    System.out.println(1);
                    return;
                }

                if (R[0] == B[0] && R[1] == B[1]) {
                    if (R_cnt > B_cnt) {
                        R[0] -= dy[d];
                        R[1] -= dx[d];
                    }
                    else {
                        B[0] -= dy[d];
                        B[1] -= dx[d];
                    }
                }

                queue.add(new int[]{R[0], R[1], B[0], B[1], curr[4] + 1});
            }
        }

        System.out.println(0);
        return;
    }
}
