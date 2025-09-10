package rerereview.backjoon;

/**
 * https://www.acmicpc.net/problem/13459
 * 13459. 구슬 탈출
 * 2025/09/08
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class 구슬탈출 {
    static final int[] DR = {1, 0, -1, 0};
    static final int[] DC = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String strNM = br.readLine();
        String[] arrNM = strNM.split(" ");
        final int N = Integer.parseInt(arrNM[0]);
        final int M = Integer.parseInt(arrNM[1]);

        char[][] board = new char[N][M];
        int[] r = null, b = null;
        for (int i = 0; i < N; i++) {
            String row = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = row.charAt(j);
                if (board[i][j] == 'R') r = new int[]{i, j};
                else if (board[i][j] == 'B') b = new int[]{i, j};
            }
        }

        boolean[][][][] visited = new boolean[N][M][N][M];
        Deque<Entry> queue = new ArrayDeque<>();
        visited[r[0]][r[1]][b[0]][b[1]] = true;
        queue.add(new Entry(r, b, 0));

        while (!queue.isEmpty()) {
            Entry curr = queue.remove();
            if (curr.moveCnt >= 10) {
                System.out.println(0);
                return;
            }

            for (int d = 0; d < 4; d++) {
                int newR_r = curr.r[0], newR_c = curr.r[1];
                boolean isHoleR = false;
                while (board[newR_r][newR_c] != '#') {
                    newR_r += DR[d]; newR_c += DC[d];
                    if (board[newR_r][newR_c] == 'O') {
                        isHoleR = true;
                        break;
                    }
                }
                newR_r -= DR[d]; newR_c -= DC[d];

                int newB_r = curr.b[0], newB_c = curr.b[1];
                boolean isHoleB = false;
                while (board[newB_r][newB_c] != '#') {
                    newB_r += DR[d]; newB_c += DC[d];
                    if (board[newB_r][newB_c] == 'O') {
                        isHoleB = true;
                        break;
                    }
                }
                newB_r -= DR[d]; newB_c -= DC[d];

                if (isHoleB) continue;
                if (isHoleR) {
                    System.out.println(1);
                    return;
                }

                if (newR_r == newB_r && newR_c == newB_c) {
                    if (d % 2 == 0) {  //상하
                        int moveCntR = Math.abs(curr.r[0] - newR_r);
                        int moveCntB = Math.abs(curr.b[0] - newB_r);
                        if (moveCntR > moveCntB) newR_r -= DR[d];
                        else if (moveCntR < moveCntB) newB_r -= DR[d];
                    }
                    else {  //좌우
                        int moveCntR = Math.abs(curr.r[1] - newR_c);
                        int moveCntB = Math.abs(curr.b[1] - newB_c);
                        if (moveCntR > moveCntB) newR_c -= DC[d];
                        else if (moveCntR < moveCntB) newB_c -= DC[d];
                    }
                }

                int[] newR, newB;
                if (!visited[newR_r][newR_c][newB_r][newB_c]) {
                    visited[newR_r][newR_c][newB_r][newB_c] = true;
                    newR = new int[]{newR_r, newR_c};
                    newB = new int[]{newB_r, newB_c};
                    queue.add(new Entry(newR, newB, curr.moveCnt + 1));
                }
            }
        }
        System.out.println(0);
        return;
    }

    static class Entry {
        int[] r, b;
        int moveCnt;
        Entry (int[] r, int[] b, int moveCnt) {
            this.r = r; this.b = b;
            this.moveCnt = moveCnt;
        }
    }
}
