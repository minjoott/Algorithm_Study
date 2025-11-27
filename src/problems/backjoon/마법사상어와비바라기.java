package problems.backjoon;

/**
 * https://www.acmicpc.net/problem/21610
 * [Backjoon] 21620. 마법사 상어와 비바라기
 * solved at: 251127
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 마법사상어와비바라기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] waters = new int[N + 1][N + 1];  //바구니에 들어있는 물의 양
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                waters[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] moveInfo = new int[M][2];  //이동 정보 (방향, 거리)
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            moveInfo[i] = new int[]{d, s};
        }

        List<int[]> clouds = new ArrayList<>();  //비구름의 위치
        clouds.add(new int[]{N, 1}); clouds.add(new int[]{N, 2}); clouds.add(new int[]{N - 1, 1}); clouds.add(new int[]{N - 1, 2});

        int[] DR = {0, 0, -1, -1, -1, 0, 1, 1, 1};  //8방향 (첫번째 더미 값 추가)
        int[] DC = {0, -1, -1, 0, 1, 1, 1, 0, -1};

        for (int m = 0; m < M; m++) {
            //Step1. 모든 구름이 d 방향으로 s칸 이동한다.
            int d = moveInfo[m][0];
            int s = moveInfo[m][1];
            List<int[]> moved = new ArrayList<>();
            for (int[] curr : clouds) {
                int r = curr[0], c = curr[1];

                int nextR = r + DR[d] * s, nextC = c + DC[d] * s;
                while (nextR < 1) nextR += N;
                while (nextR > N) nextR -= N;
                while (nextC < 1) nextC += N;
                while (nextC > N) nextC -= N;

                moved.add(new int[]{nextR, nextC});
            }
            clouds = moved;

            //Step2. 각 구름에서 비가 내려 구름이 있는 칸의 바구니에 들어있는 물의 양이 1 증가한다.
            for (int[] cloud : clouds) {
                int r = cloud[0], c = cloud[1];
                waters[r][c]++;
            }

            //Step4. 물이 증가한 해당 칸의 대각선 방향으로 거리가 1인 칸에 물이 있는 바구니의 수만큼 (r, c)에 있는 바구니의 물의 양이 증가한다.
            for (int[] cloud : clouds) {
                int r = cloud[0], c = cloud[1];
                int cnt = 0;

                for (int diagonal = 2; diagonal <= 8; diagonal += 2) {
                    int diagonalR = r + DR[diagonal];
                    int diagonalC = c + DC[diagonal];

                    if (diagonalR < 1 || diagonalR > N || diagonalC < 1 || diagonalC > N) continue;
                    if (waters[diagonalR][diagonalC] > 0) cnt++;
                }

                waters[r][c] += cnt;
            }

            //Step3. 구름이 모두 사라진다.
            Set<String> removedClouds = new HashSet<>();
            for (int[] cloud : clouds) {
                int r = cloud[0], c = cloud[1];
                removedClouds.add(r + "," + c);
            }
            clouds.clear();

            //Step5. 바구니에 들어있는 물의 양이 2 이상인 모든 칸에 구름이 생기고, 물의 양이 2 줄어든다.
            //       이때 구름이 생기는 칸은 3에서 구름이 사라진 칸이 아니어야 한다.
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (waters[i][j] >= 2) {
                        if (!removedClouds.contains(i + "," + j)) {
                            clouds.add(new int[]{i, j});
                            waters[i][j] -= 2;
                        }
                    }
                }
            }
        }

        int waterSum = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                waterSum += waters[i][j];
            }
        }

        System.out.println(waterSum);
        return;
    }
}
