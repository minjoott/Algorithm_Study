package softeer;

import java.io.*;
import java.util.*;

public class 순서대로진행하기 {
    static int answer = 0;  // 차량이 m개의 지점을 순서대로 방문할 수 있는 서로 다른 방법의 수
    static int n;
    static int m;
    static int[][] map;
    static boolean[][] visited;
    static List<Point> destinations;  // 꼭 방문해야 할 경유지

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 입력값 처리
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        destinations = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken()) - 1;  // 행
            int x = Integer.parseInt(st.nextToken()) - 1;  // 열
            destinations.add(new Point(y, x));  // (행, 열)
        }

        // 출발지부터 DFS + 백트래킹 수행
        visited = new boolean[n][n];
        int startY = destinations.get(0).y;
        int startX = destinations.get(0).x;
        visited[startY][startX] = true;
        dfs(destinations.get(0), 1);

        System.out.println(answer);
        return;
    }

    static void dfs(Point curr, int nextDestinationIdx) {
        if (nextDestinationIdx == m) {  // 현재 지점(curr)이 마지막 경유지라면,
            answer++;
            return;
        }

        int[] dy = {1, 0, -1, 0};
        int[] dx = {0, 1, 0, -1};
        for (int d = 0; d < 4; d++) {
            int nextY = curr.y + dy[d];
            int nextX = curr.x + dx[d];
            if (nextY < 0 || nextY >= n || nextX < 0 || nextX >= n || visited[nextY][nextX] || map[nextY][nextX] == 1) continue;

            Point next = new Point(nextY, nextX);
            int updatedDestinationIdx = nextDestinationIdx;
            if (next.equals(destinations.get(nextDestinationIdx))) {  // 다음에 방문할 지점이 이제 방문해야 할 경유지라면,
                updatedDestinationIdx++;  // 경유지 포인터++
            }
            else if (destinations.contains(next)) {  // 나중에 방문해야 할 경유지라면,
                continue;
            }

            // 서로 다른 경로를 탐색해 나가야 하므로, 백트래킹
            visited[nextY][nextX] = true;
            dfs(next, updatedDestinationIdx);
            visited[nextY][nextX] = false;
        }
    }

    static class Point {
        int y;
        int x;
        Point(int y, int x) {
            this.y = y;
            this.x = x;
        }

        @Override
        public boolean equals(Object obj) {
            Point other = (Point) obj;
            return this.y == other.y && this.x == other.x;
        }
    }
}
