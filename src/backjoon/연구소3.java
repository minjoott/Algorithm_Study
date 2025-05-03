package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 연구소3 {
    static int N;
    static int M;
    static int minSpreadTime = Integer.MAX_VALUE;
    static int emptyNum = 0;
    static int[] dy = {1, 0, -1, 0};
    static int[] dx = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][N];
        List<int[]> virus = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] == 0)
                    emptyNum++;
                else if (map[i][j] == 2)
                    virus.add(new int[]{i, j});
            }
        }

        backtracking(0, new ArrayList<>(), virus, map);

        if (minSpreadTime == Integer.MAX_VALUE)
            System.out.println(-1);
        else
            System.out.println(minSpreadTime);

        return;
    }

    static void backtracking(int start, List<int[]> active, List<int[]> virus, int[][] map) {
        if (active.size() == M) {
            boolean[][] visited = new boolean[N][N];
            Deque<int[]> queue = new ArrayDeque<>();
            for (int[] a : active) {
                queue.add(new int[]{a[0], a[1], 0});
                visited[a[0]][a[1]] = true;
            }

            int[] curr = null;
            int emptyCnt = 0;
            int currSpreadTime = 0;
            while (!queue.isEmpty()) {
                curr = queue.poll();

                for (int d = 0; d < 4; d++) {
                    int nextY = curr[0] + dy[d];
                    int nextX = curr[1] + dx[d];

                    if (nextY >= N || nextY < 0 || nextX >= N || nextX < 0 || visited[nextY][nextX]) continue;

                    if (map[nextY][nextX] == 0 || map[nextY][nextX] == 2) {
                        if (map[nextY][nextX] == 0) {
                            emptyCnt++;
                            currSpreadTime = curr[2] + 1;
                        }

                        queue.add(new int[]{nextY, nextX, curr[2] + 1});
                        visited[nextY][nextX] = true;
                    }
                }
            }

            if (emptyCnt == emptyNum) {
                if (currSpreadTime < minSpreadTime) {
                    minSpreadTime = currSpreadTime;
                }
            }

            return;
        }

        for (int i = start; i < virus.size(); i++) {
            active.add(virus.get(i));
            backtracking(i + 1, active, virus, map);
            active.remove(active.size() - 1);
        }
    }
}
