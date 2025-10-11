package problems.backjoon;

/**
 * https://www.acmicpc.net/problem/19238
 * [Backjoon] 19238. 스타트택시
 * solved at: 251011
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 스타트택시 {
    static final int[] DR = {-1, 0, 0, 1};
    static final int[] DC = {0, -1, 1, 0};

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

        Position[] destinations = new Position[M + 1];
        int[][] departureMap = new int[N][N];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            Position departure = new Position(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);
            Position destination = new Position(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);
            destinations[i + 1] = destination;

            departureMap[departure.r][departure.c] = i + 1;  //해당 위치에 i+1번 승객이 서 있음을 표시 => O(1) time에 파악 가능
        }

        int completedCnt = 0;
        for (int i = 0; i < M; i++) {
            // step1: 승객을 태우기
            int minDist = Integer.MAX_VALUE;
            List<Position> candidates = new ArrayList<>();
            Queue<Entry> q1 = new ArrayDeque<>();
            boolean[][] visited1 = new boolean[N][N];

            q1.add(new Entry(taxi, 0));  //택시 출발
            visited1[taxi.r][taxi.c] = true;

            while (!q1.isEmpty()) {
                Entry curr = q1.remove();

                if (!candidates.isEmpty() && curr.cnt > minDist) break;  //동일한 거리의 승객으로 candidates 구성 완료

                if (departureMap[curr.position.r][curr.position.c] != 0) {  //현위치에 승객이 서 있으면 candidates애 추가
                    candidates.add(new Position(curr.position.r, curr.position.c));
                    minDist = curr.cnt;
                }

                for (int d = 0; d < 4; d++) {
                    int nextR = curr.position.r + DR[d];
                    int nextC = curr.position.c + DC[d];

                    if (nextR < 0 || nextR >= N || nextC < 0 || nextC >= N) continue;
                    if (visited1[nextR][nextC]) continue;
                    if (map[nextR][nextC] == 1) continue;
                    if (curr.cnt + 1> fuel) continue;  //연료 부족

                    q1.add(new Entry(new Position(nextR, nextC), curr.cnt + 1));
                    visited1[nextR][nextC] = true;
                }
            }

            if (candidates.isEmpty()) {  //연료 부족 or 가능한 경로 X 때문에 승객을 태우지 못하면, 운행 중단
                System.out.println(-1);
                return;
            }

            fuel -= minDist;  //승객을 태우러 가는데 이동한 거리만큼 연료 소모

            candidates.sort((a, b) -> {  //행, 열 번호가 작은 순으로 정렬
               if (a.r != b.r) return a.r - b.r;
               return a.c - b.c;
            });

            Position departure = candidates.get(0);  //우선순위가 가장 높은 승객 먼저 태우기
            int passengerNo = departureMap[departure.r][departure.c];
            Position destination = destinations[passengerNo];

            // step2: 태운 승객을 목적지로 이동시키기
            Queue<Entry> q2 = new ArrayDeque<>();
            boolean[][] visited2 = new boolean[N][N];

            q2.add(new Entry(departure, 0));
            visited2[departure.r][departure.c] = true;

            Entry curr = null;
            while (!q2.isEmpty()) {
                curr = q2.remove();

                if (curr.position.r == destination.r && curr.position.c == destination.c) {  //승객의 목적지 도착
                    completedCnt++;
                    break;
                }

                for (int d = 0; d < 4; d++) {
                    int nextR = curr.position.r + DR[d];
                    int nextC = curr.position.c + DC[d];

                    if (nextR < 0 || nextR >= N || nextC < 0 || nextC >= N) continue;
                    if (visited2[nextR][nextC]) continue;
                    if (map[nextR][nextC] == 1) continue;
                    if (curr.cnt + 1> fuel) continue;  //연료 부족

                    q2.add(new Entry(new Position(nextR, nextC), curr.cnt + 1));
                    visited2[nextR][nextC] = true;
                }
            }

            if (i + 1 == completedCnt) {  //승객 passengerNo을 목적지까지 성공적으로 이동시켰으면
                fuel += curr.cnt;  //연료 충전
                departureMap[departure.r][departure.c] = 0;
                taxi = destination;
            }
            else {
                System.out.println(-1);
                return;
            }
        }
        System.out.println(fuel);
        return;
    }

    static class Position {
        int r;
        int c;

        Position(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static class Entry {
        Position position;
        int cnt;

        Entry(Position position, int cnt) {
            this.position = position;
            this.cnt = cnt;
        }
    }
}
