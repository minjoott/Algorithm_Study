package review.backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class 구슬탈출 {
    static final int[] DR = {1, 0, -1, 0};
    static final int[] DC = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        final int N = Integer.parseInt(st.nextToken());
        final int M = Integer.parseInt(st.nextToken());

        char[][] board = new char[N][M];
        Position red = null, blue = null;
        for (int i = 0; i < N; i++) {
            String row = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = row.charAt(j);
                if (board[i][j] == 'R') red = new Position(i, j);
                else if (board[i][j] == 'B') blue = new Position(i, j);
            }
        }

        boolean[][][][] visited = new boolean[N][M][N][M];  // 좌표 (redR, redC, blueR, blueC)의 방문 여부 관리
        Deque<Entry> queue = new ArrayDeque<>();

        visited[red.r][red.c][blue.r][blue.c] = true;
        queue.offer(new Entry(red, blue, 0));

        while (!queue.isEmpty()) {
            Entry curr = queue.poll();

            if (curr.count == 10) break;

            red = curr.red; blue = curr.blue;
            for (int d = 0; d < 4; d++) {
                // 빨간 구슬
                Position nextRed = new Position(red.r, red.c);
                boolean redHole = false;
                while (board[nextRed.r][nextRed.c] != '#') {  // 벽에 부딪힐 때까지 기울이기
                    nextRed.r += DR[d]; nextRed.c += DC[d];
                    if (board[nextRed.r][nextRed.c] == 'O') {
                        redHole = true;
                        break;
                    }
                }
                nextRed.r -= DR[d]; nextRed.c -= DC[d];

                // 파란 구슬
                Position nextBlue = new Position(blue.r, blue.c);
                boolean blueHole = false;
                while (board[nextBlue.r][nextBlue.c] != '#') {  // 벽에 부딪힐 때까지 기울이기
                    nextBlue.r += DR[d]; nextBlue.c += DC[d];
                    if (board[nextBlue.r][nextBlue.c] == 'O') {
                        blueHole = true;
                        break;
                    }
                }
                nextBlue.r -= DR[d]; nextBlue.c -= DC[d];

                // 파란 구슬이 구멍에 빠졌으면, 해당 방향으로 기울이면 X
                if (blueHole) continue;

                // 빨간 구슬만 구멍에 빠졌으면, 게임 성공
                if (redHole) {
                    System.out.println(1);
                    return;
                }

                // 두 구슬이 같은 칸에 존재하지 않도록 별도 처리
                if (nextRed.equals(nextBlue)) {
                    int redDistance, blueDistance;
                    if (d == 0 || d == 2) {  // 세로로 움직인 경우
                        redDistance = Math.abs(nextRed.r - curr.red.r);
                        blueDistance = Math.abs(nextBlue.r - curr.blue.r);
                        if (redDistance > blueDistance) nextRed.r -= DR[d];
                        else nextBlue.r -= DR[d];
                    } else {  // 가로로 움직인 경우
                        redDistance = Math.abs(nextRed.c - curr.red.c);
                        blueDistance = Math.abs(nextBlue.c - curr.blue.c);
                        if (redDistance > blueDistance) nextRed.c -= DC[d];
                        else nextBlue.c -= DC[d];
                    }
                }

                if (visited[nextRed.r][nextRed.c][nextBlue.r][nextBlue.c]) continue;

                // enqueue
                visited[nextRed.r][nextRed.c][nextBlue.r][nextBlue.c] = true;
                queue.offer(new Entry(nextRed, nextBlue, curr.count + 1));

            }
        }

        // 10번 이하로 움직여서 빨간 구슬을 빼낼 수 있는 방법이 존재하지 않음. 게임 종료
        System.out.println(0);
        return;
    }

    static class Position {
        int r;
        int c;

        Position(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public boolean equals(Object o) {
            Position other = (Position) o;
            return (this.r == other.r && this.c == other.c);
        }
    }

    static class Entry {
        Position red;
        Position blue;
        int count;

        Entry(Position red, Position blue, int count) {
            this.red = red;
            this.blue = blue;
            this.count = count;
        }
    }
}
