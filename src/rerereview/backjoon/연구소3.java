package rerereview.backjoon;

/**
 * https://www.acmicpc.net/problem/17142
 * 17142. 연구소3
 * 2025/09/05
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 연구소3 {
    static int N;
    static int M;
    static int[][] lab;
    static List<int[]> viruses = new ArrayList<>();
    static int totalVirusCnt;
    static int totalEmptyCnt = 0;
    static int minSpreadTime = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        lab = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                lab[i][j] = Integer.parseInt(st.nextToken());
                if (lab[i][j] == 2) {
                    viruses.add(new int[]{i, j});
                }
                else if (lab[i][j] == 0) {
                    totalEmptyCnt++;
                }
            }
        }
        totalVirusCnt = viruses.size();

        backtracking(0, new ArrayList<>());

        int ans = (minSpreadTime == Integer.MAX_VALUE) ? -1 : minSpreadTime;
        System.out.println(ans);
        return;
    }

    static int[] DR = {1, 0, -1, 0};
    static int[] DC = {0, 1, 0, -1};

    static void backtracking(int startVirusIdx, List<Integer> activeViruses) {
        if (activeViruses.size() == M) {
            //M개의 바이러스를 활성 상태로 변화시킨 연구소 만들기
            int[][] activeLab = new int[N][N];
            for (int i = 0; i < N; i++) {
                activeLab[i] = Arrays.copyOf(lab[i], N);
            }
            Deque<int[]> queue = new ArrayDeque<>();
            for (int avIdx : activeViruses) {
                int[] av = viruses.get(avIdx);
                int r = av[0], c = av[1];
                activeLab[r][c] = 3;
                queue.add(new int[]{r, c, 0});
            }

            //바이러스 전파시키기
            int lastSpreadTime = 0;
            int totalSpreadCnt = 0;
            while (!queue.isEmpty()) {
                int[] curr = queue.remove();
                int r = curr[0], c = curr[1];
                int spreadTime = curr[2];

                for (int d = 0; d < 4; d++) {
                    int nextR = r + DR[d], nextC = c + DC[d];
                    if (nextR < 0 || nextR >= N || nextC < 0 || nextC >= N) continue;

                    if (activeLab[nextR][nextC] == 0 || activeLab[nextR][nextC] == 2) {
                        if (activeLab[nextR][nextC] == 0) {
                            totalSpreadCnt++;
                            lastSpreadTime = spreadTime + 1;
                        }

                        activeLab[nextR][nextC] = 3;
                        queue.add(new int[]{nextR, nextC, spreadTime + 1});
                    }
                }
            }
            if (totalSpreadCnt == totalEmptyCnt) {
                minSpreadTime = Math.min(lastSpreadTime, minSpreadTime);
            }
            return;
        }

        for (int i = startVirusIdx; i < totalVirusCnt; i++) {
            activeViruses.add(i);
            backtracking(i + 1, activeViruses);
            activeViruses.remove(activeViruses.size() - 1);
        }
    }
}
