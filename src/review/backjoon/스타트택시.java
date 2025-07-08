package review.backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 스타트택시 {
    static final int[] DR = {1, 0, -1, 0};
    static final int[] DC = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        final int N = Integer.parseInt(st.nextToken());
        final int M = Integer.parseInt(st.nextToken());
        int fuel = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        Position taxi = new Position(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);

        int[][] departures = new int[N][N];
        for (int[] row : departures) Arrays.fill(row, -1);  // departures[r][c] = pid
        int[][] destinations = new int[M][2];  // destinations[pid] = (r, c)
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int departureR = Integer.parseInt(st.nextToken()) - 1;
            int departureC = Integer.parseInt(st.nextToken()) - 1;
            int destinationR = Integer.parseInt(st.nextToken()) - 1;
            int destinationC = Integer.parseInt(st.nextToken()) - 1;
            departures[departureR][departureC] = i;
            destinations[i][0] = destinationR; destinations[i][1] = destinationC;
        }

        int completedCnt = 0;
        for(int i = 1; i <= M; i++) {
            /* 1. 승객을 택시에 태우기 */

            // special case: 현재 택시 위치에 승객이 서있는 경우, 즉시 탑승
            int target = (departures[taxi.r][taxi.c] != -1) ? departures[taxi.r][taxi.c] : -1;

            if (target == -1) {  // 최단 거리의 승객 후보를 찾으러 이동하기
                // 승객 후보 (거리가 모두 동일)
                Queue<Entry> candidates = new PriorityQueue<>((a, b)
                        -> a.position.r != b.position.r
                        ? a.position.r - b.position.r
                        : a.position.c - b.position.c
                );
                boolean[][] visited = new boolean[N][N];
                Deque<Entry> queue = new ArrayDeque<>();

                visited[taxi.r][taxi.c] = true;  // 출발
                queue.offer(new Entry(taxi, 0));

                while (!queue.isEmpty()) {
                    Entry curr = queue.poll();

                    // 이미 최소 거리 승객 후보가 있고 현재 거리가 그 후보의 거리보다 크면, 승객 후보 찾기 중단
                    if (!candidates.isEmpty() && curr.distance > candidates.peek().distance) break;

                    // 승객 후보 추가
                    if (departures[curr.position.r][curr.position.c] != -1) {
                        candidates.offer(curr);
                        continue;
                    }

                    if (curr.distance == fuel) continue;  // 연료가 모두 소모되면, 더이상 이동할 수 X

                    // 현재 위치를 중심으로 4방향 탐색
                    for (int d = 0; d < 4; d++) {
                        Entry next = new Entry(new Position(curr.position.r + DR[d], curr.position.c + DC[d]), curr.distance + 1);

                        if (next.position.r < 0 || next.position.r >= N || next.position.c < 0 || next.position.c >= N ||
                                visited[next.position.r][next.position.c] || map[next.position.r][next.position.c] == 1) continue;

                        visited[next.position.r][next.position.c] = true;
                        queue.offer(next);
                    }
                }// 승객 후보가 없으면, 운행 중단
                if (candidates.isEmpty()) {
                    System.out.println(-1);
                    return;
                }
                // 1st 승객 태우기 + 연료 소모
                Entry departure = candidates.poll();
                target = departures[departure.position.r][departure.position.c];
                taxi.r = departure.position.r; taxi.c = departure.position.c;
                fuel -= departure.distance;
            }

            /* 2. 승객을 목적지로 이동시키기 */
            Position destination = new Position(destinations[target][0], destinations[target][1]);

            boolean[][] visited = new boolean[N][N];
            Deque<Entry> queue = new ArrayDeque<>();

            // 목적지로 출발
            visited[taxi.r][taxi.c] = true;
            queue.offer(new Entry(taxi, 0));

            while (!queue.isEmpty()) {
                Entry curr = queue.poll();

                // 목적지에 도착했으면, 해당 승객 완료 처리
                if (curr.position.equals(destination)) {
                    departures[taxi.r][taxi.c] = -1;
                    taxi.r = destination.r; taxi.c = destination.c;
                    fuel += curr.distance;  // 연료 충전
                    completedCnt++;
                    break;
                }

                if (curr.distance == fuel) continue;  // 연료가 모두 소모되면, 더이상 이동할 수 X

                // 현재 위치를 중심으로 4방향 탐색
                for (int d = 0; d < 4; d++) {
                    Entry next = new Entry(new Position(curr.position.r + DR[d], curr.position.c + DC[d]), curr.distance + 1);

                    if (next.position.r < 0 || next.position.r >= N || next.position.c < 0 || next.position.c >= N ||
                            visited[next.position.r][next.position.c] || map[next.position.r][next.position.c] == 1) continue;

                    visited[next.position.r][next.position.c] = true;
                    queue.offer(next);
                }
            }
            // 태운 숭객을 목적지까지 이동시키지 못했다면, 즉시 운행 중단
            if (completedCnt != i) {
                System.out.println(-1);
                return;
            }
        }

        // completedCnt = M이면, 남은 연료를 출력하고 종료
        System.out.println(fuel);
        return;
    }

    static class Position {
        int r, c;

        Position(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public boolean equals(Object o) {
            Position other = (Position) o;
            return this.r == other.r && this.c == other.c;
        }
    }

    static class Entry {
        Position position;
        int distance;

        Entry(Position position, int distance) {
            this.position = position;
            this.distance = distance;
        }
    }
}
