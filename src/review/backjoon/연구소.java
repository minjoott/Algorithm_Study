package review.backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 연구소 {
    private static int answer = 0;
    private static int N;
    private static int M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        final int[][] map = new int[N][M];
        List<Position> emptyList = new ArrayList<>();
        List<Position> virusList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 0) emptyList.add(new Position(i, j));
                else if (map[i][j] == 2) virusList.add(new Position(i, j));
            }
        }

        backtracking(0, new ArrayList<>(), emptyList, virusList, map);
        System.out.println(answer);
        return;
    }

    static final int[] DR = {1, 0, -1, 0};
    static final int[] DC = {0, 1, 0, -1};
    private static void backtracking(int start, List<Position> wallList, List<Position> emptyList, List<Position> virusList, int[][] map) {
        if (wallList.size() == 3) {
            for (Position w : wallList) {
                map[w.r][w.c] = 1;
            }
            int safeCnt = emptyList.size() - 3;

            Deque<Position> queue = new ArrayDeque<>(virusList);
            boolean[][] infected = new boolean[N][M];
            while (!queue.isEmpty()) {
                Position curr = queue.poll();

                for (int d = 0; d < 4; d++) {
                    int nextR = curr.r + DR[d], nextC = curr.c + DC[d];
                    Position next = new Position(nextR, nextC);

                    if (nextR < 0 || nextR >= N || nextC < 0 || nextC >= M || map[nextR][nextC] != 0 || infected[nextR][nextC]) continue;

                    infected[nextR][nextC] = true;
                    queue.offer(next);
                    safeCnt--;
                }
            }
            answer = Math.max(safeCnt, answer);

            for (Position w : wallList) {
                map[w.r][w.c] = 0;
            }

            return;
        }

        for (int i = start; i < emptyList.size(); i++) {
            wallList.add(emptyList.get(i));
            backtracking(i + 1, wallList, emptyList, virusList, map);
            wallList.remove(emptyList.get(i));
        }
    }

    static class Position {
        int r;
        int c;

        Position(int r, int c) {
            this.r = r;
            this.c = c;
        }

        public boolean equals(Object o) {
            Position position = (Position) o;
            return r == position.r && c == position.c;
        }
    }
}
