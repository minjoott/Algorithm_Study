package rerereview.backjoon;

/**
 * https://www.acmicpc.net/problem/14502
 * 14502. 연구소
 * 2025/09/04
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 연구소 {
    static int N;
    static int M;
    static List<int[]> empties = new ArrayList<>();
    static int totalEmptyCnt = 0;
    static List<int[]> viruses = new ArrayList<>();
    static int maxSafeSize = 0;  //답

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 0) {
                    empties.add(new int[]{i, j});
                }
                else if (map[i][j] == 2) {
                    viruses.add(new int[]{i, j});
                }
            }
        }
        totalEmptyCnt = empties.size();

        backtracking(0, new ArrayList<>(), map);
        System.out.println(maxSafeSize);

        return;
    }

    static int[] DR = {1, 0, -1, 0};
    static int[] DC = {0, 1, 0, -1};

    static void backtracking(int startEmptyIdx, List<int[]> selectedEmpties, int[][] oldMap) {
        if (selectedEmpties.size() == 3) {
            //이번 조합으로 선택된 3개의 벽을 세운 지도 만들기
            int[][] map = new int[N][M];
            for (int i = 0; i < N; i++) {
                map[i] = Arrays.copyOf(oldMap[i], M);
            }
            for (int[] wall : selectedEmpties) {
                map[wall[0]][wall[1]] = 1;
            }

            //바이러스를 전파시키고 안전영역 구하기
            Deque<int[]> queue = new ArrayDeque<>();
            boolean[][] visited = new boolean[N][M];
            for (int[] virus : viruses) {
                visited[virus[0]][virus[1]] = true;
                queue.offer(virus);
            }

            int noSafeCnt = 3;
            while (!queue.isEmpty()) {
                int[] curr = queue.poll();
                for (int d = 0; d < 4; d++) {
                    int nextR = curr[0] + DR[d];
                    int nextC = curr[1] + DC[d];
                    if (nextR < 0 || nextR >= N || nextC < 0 || nextC >= M || visited[nextR][nextC] ||
                        map[nextR][nextC] != 0) continue;

                    visited[nextR][nextC] = true;
                    queue.offer(new int[]{nextR, nextC});
                    noSafeCnt++;
                }
            }
            int safeSize = totalEmptyCnt - noSafeCnt;
            maxSafeSize = Math.max(safeSize, maxSafeSize);

            return;
        }

        // 백트래킹으로 조합 구현
        for (int i = startEmptyIdx; i < totalEmptyCnt; i++) {
            selectedEmpties.add(empties.get(i));
            backtracking(i + 1, selectedEmpties, oldMap);
            selectedEmpties.remove(selectedEmpties.size() - 1);
        }
    }
}
