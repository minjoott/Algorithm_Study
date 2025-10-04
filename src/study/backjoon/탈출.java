package study.backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class 탈출 {
    static final int[] DR = {1, 0, -1, 0};
    static final int[] DC = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] rc = br.readLine().split(" ");
        final int R = Integer.parseInt(rc[0]);
        final int C = Integer.parseInt(rc[1]);

        char[][] map = new char[R][C];
        Entry S = null;
        Deque<Entry> waterQueue = new ArrayDeque<>();
        for (int i = 0; i < R; i++) {
            String row = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = row.charAt(j);
                if (map[i][j] == 'S') S = new Entry(i, j, 0);
                else if (map[i][j] == '*') waterQueue.add(new Entry(i, j, 0));
            }
        }

        int[][] waterMoveTime = new int[R][C];
        while (!waterQueue.isEmpty()) {
            Entry curr = waterQueue.remove();
            for (int d = 0; d < 4; d++) {
                int nextR = curr.r + DR[d], nextC = curr.c + DC[d];
                if (nextR < 0 || nextR >= R || nextC < 0 || nextC >= C ||
                        map[nextR][nextC] != '.') continue;

                waterQueue.add(new Entry(nextR, nextC, curr.time + 1));
                waterMoveTime[nextR][nextC] = curr.time + 1;
                map[nextR][nextC] = '*';
            }
        }

        Deque<Entry> manQueue = new ArrayDeque<>();
        boolean[][] manVisited = new boolean[R][C];
        manQueue.add(S);
        manVisited[S.r][S.c] = true;
        while (!manQueue.isEmpty()) {
            Entry curr = manQueue.remove();
            for (int d = 0; d < 4; d++) {
                int nextR = curr.r + DR[d], nextC = curr.c + DC[d];
                if (nextR < 0 || nextR >= R || nextC < 0 || nextC >= C ||
                        manVisited[nextR][nextC] || map[nextR][nextC] == 'X') continue;

                if (map[nextR][nextC] == 'D') {
                    System.out.println(curr.time + 1);
                    return;
                }

                if (map[nextR][nextC] == '.' || curr.time + 1 < waterMoveTime[nextR][nextC]) {
                    manQueue.add(new Entry(nextR, nextC, curr.time + 1));
                    manVisited[nextR][nextC] = true;
                }
            }
        }
        System.out.println("KAKTUS");
        return;
    }

    static class Entry {
        int r, c;
        int time;

        Entry(int r, int c, int time) {
            this.r = r;
            this.c = c;
            this.time = time;
        }
    }
}
