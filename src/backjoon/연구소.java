package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 연구소 {
    static int maxSafeSize = 0;  // answer
    static int N, M;
    static int[] dy = {1, 0, -1, 0};
    static int[] dx = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];
        List<int[]> empties = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 0)
                    empties.add(new int[]{i, j});
            }
        }

        backtracking(0, new ArrayList<>(), empties, map);

        System.out.println(maxSafeSize);

        return;
    }

    static void backtracking(int start, List<int[]> walls, List<int[]> empties, int[][] map) {
        Deque<int[]> queue = new ArrayDeque<>();
        if (walls.size() == 3) {
            int[][] wallMap = new int[N][M];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    wallMap[i][j] = map[i][j];

                    if (wallMap[i][j] == 2) {
                        queue.add(new int[]{i, j});
                    }
                }
            }

            // 벽 세우기
            for (int[] wall : walls)
                wallMap[wall[0]][wall[1]] = 1;

            // 바이러스 확산시키기
            boolean[][] visited = new boolean[N][M];
            while (!queue.isEmpty()) {
                int[] curr = queue.poll();
                for (int d = 0; d < 4; d++) {
                    int nextY = curr[0] + dy[d];
                    int nextX = curr[1] + dx[d];

                    if (nextY >= N || nextY < 0 || nextX >= M || nextX < 0 || visited[nextY][nextX])
                        continue;

                    if (wallMap[nextY][nextX] == 0) {
                        queue.add(new int[]{nextY, nextX});
                        wallMap[nextY][nextX] = 2;
                        visited[nextY][nextX] = true;
                    }
                }
            }

            int safeSize = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (wallMap[i][j] == 0)
                        safeSize++;
                }
            }

            if (safeSize > maxSafeSize)
                maxSafeSize = safeSize;

            return;
        }

        for (int i = start; i < empties.size(); i++) {
            walls.add(empties.get(i));
            backtracking(i + 1, walls, empties, map);
            walls.remove(walls.size() - 1);
        }
    }
}
