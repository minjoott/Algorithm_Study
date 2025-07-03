package review.backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 연구소3 {
    static final int[] DR = {1, 0, -1, 0};
    static final int[] DC = {0, 1, 0, -1};
    static int minActiveTime = Integer.MAX_VALUE;
    static int emptyCnt = 0;
    static int N;
    static int M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][N];
        List<int[]> virusList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 0) {
                    emptyCnt++;
                } else if (map[i][j] == 2) {
                    virusList.add(new int[]{i, j});
                }
            }
        }

        if (emptyCnt == 0) {
            System.out.println(0);
            return;
        }

        backtracking(0, new ArrayList<>(), virusList, map);

        int answer = (minActiveTime != Integer.MAX_VALUE) ? minActiveTime : -1;
        System.out.println(answer);
        return;
    }

    private static void backtracking(int start, List<int[]> activeVirusList, List<int[]> virusList, int[][] map) {
        if (activeVirusList.size() == M) {
            int[][] activeMap = new int[N][N];
            for (int i = 0; i < N; i++) {
                activeMap[i] = map[i].clone();
            }

            Deque<int[]> queue = new ArrayDeque<>();
            for (int[] av : activeVirusList) {
                int r = av[0], c = av[1];
                activeMap[r][c] = 3;
                queue.offer(new int[]{r, c, 0});
            }

            int emptyToActiveCnt = 0;
            int[] curr = null;
            while (!queue.isEmpty()) {
                curr = queue.poll();
                int r = curr[0], c = curr[1], cnt = curr[2];

                for (int d = 0; d < 4; d++) {
                    int nextR = r + DR[d], nextC = c + DC[d];
                    if (nextR < 0 || nextR >= N || nextC < 0 || nextC >= N ||
                            activeMap[nextR][nextC] == 3 || activeMap[nextR][nextC] == 1) continue;

                    if (activeMap[nextR][nextC] == 0) {
                        if (++emptyToActiveCnt == emptyCnt) {
                            minActiveTime = Math.min(cnt + 1, minActiveTime);
                            return;
                        }
                    }

                    activeMap[nextR][nextC] = 3;
                    queue.offer(new int[]{nextR, nextC, cnt + 1});
                }
            }
            return;
        }

        for (int i = start; i < virusList.size(); i++) {
            activeVirusList.add(virusList.get(i));
            backtracking(i + 1, activeVirusList, virusList, map);
            activeVirusList.remove(activeVirusList.size() - 1);
        }
    }
}
