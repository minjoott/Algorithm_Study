package problems.baekjoon;

/**
 * https://www.acmicpc.net/problem/16935
 * [Baekjoon] 16935. 배열 돌리기
 * solved at: 251208
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 배열돌리기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        int[][] arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int r = 0; r < R; r++) {
            int jobNo = Integer.parseInt(st.nextToken());
            N = arr.length;
            M = arr[0].length;
            switch (jobNo) {
                case 1 -> {  //상하 반전
                    for (int i = 0; i < N / 2; i++) {
                        swap1(i, arr);
                    }
                }
                case 2 -> {  //좌우 반전
                    for (int j = 0; j < M / 2; j++) {
                        for (int i = 0; i < N; i++) {
                            swap2(i, j, arr);
                        }
                    }
                }
                case 3 -> {  //오른쪽으로 90도 회전
                    int[][] newArr = new int[M][N];
                    for (int i = 0; i < N; i++) {
                        int[] row = arr[i];
                        for (int j = 0; j < M; j++) {
                            newArr[j][N - 1 - i] = row[j];
                        }
                    }
                    arr = newArr;
                }
                case 4 -> {  //왼쪽으로 90도 회전
                    int[][] newArr = new int[M][N];
                    for (int i = 0; i < N; i++) {
                        int[] row = arr[i];
                        for (int j = 0; j < M; j++) {
                            newArr[M - 1 - j][i] = row[j];
                        }
                    }
                    arr = newArr;
                }
                case 5 -> {  //그룹 이동 1
                    int[][] newArr = new int[N][M];
                    for (int i = 0; i < N / 2; i++) {
                        for (int j = 0; j < M / 2; j++) {
                            newArr[i][M / 2 + j] = arr[i][j];
                        }
                    }
                    for (int i = 0; i < N / 2; i++) {
                        for (int j = M / 2; j < M; j++) {
                            newArr[N / 2 + i][j] = arr[i][j];
                        }
                    }
                    for (int i = N / 2; i < N; i++) {
                        for (int j = M / 2; j < M; j++) {
                            newArr[i][j - M / 2] = arr[i][j];
                        }
                    }
                    for (int i = N / 2; i < N; i++) {
                        for (int j = 0; j < M / 2; j++) {
                            newArr[i - N / 2][j] = arr[i][j];
                        }
                    }
                    arr = newArr;
                }
                case 6 -> {  //그룹 이동 2
                    int[][] newArr = new int[N][M];
                    for (int i = 0; i < N / 2; i++) {
                        for (int j = 0; j < M / 2; j++) {
                            newArr[N / 2 + i][j] = arr[i][j];
                        }
                    }
                    for (int i = N / 2; i < N; i++) {
                        for (int j = 0; j < M / 2; j++) {
                            newArr[i][M / 2 + j] = arr[i][j];
                        }
                    }
                    for (int i = N / 2; i < N; i++) {
                        for (int j = M / 2; j < M; j++) {
                            newArr[i - N / 2][j] = arr[i][j];
                        }
                    }
                    for (int i = 0; i < N / 2; i++) {
                        for (int j = M / 2; j < M; j++) {
                            newArr[i][j - M / 2] = arr[i][j];
                        }
                    }
                    arr = newArr;
                }
            }
        }

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

    static void swap1(int r, int[][] arr) {
        int N = arr.length;

        int[] temp = arr[r];
        arr[r] = arr[N - 1 - r];
        arr[N - 1 - r] = temp;
    }

    static void swap2(int r, int c, int[][] arr) {
        int M = arr[0].length;

        int temp = arr[r][c];
        arr[r][c] = arr[r][M - 1 - c];
        arr[r][M - 1 - c] = temp;
    }
}
