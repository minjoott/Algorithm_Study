package problems.baekjoon;

/**
 * https://www.acmicpc.net/problem/3190
 * [Backjoon] 3190. 뱀
 * solved at: 251120
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class 뱀 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        boolean[][] snake = new boolean[N + 1][N + 1];  //뱀의 위치 저장

        boolean[][] apples = new boolean[N + 1][N + 1];  //사과 위치 저장
        int K = Integer.parseInt(br.readLine());
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            apples[r][c] = true;
        }

        int L = Integer.parseInt(br.readLine());
        int[] changeTime = new int[L + 1];  //방향 변환 시점
        char[] changeDirection = new char[L + 1];  //변환할 방향 (L/D)
        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            int X = Integer.parseInt(st.nextToken());
            char C = st.nextToken().charAt(0);
            changeTime[i] = X;
            changeDirection[i] = C;
        }
        changeTime[L] = 10000;
        changeDirection[L] = 'A';

        int[] DR = {-1, 0, 1, 0};
        int[] DC = {0, 1, 0, -1};

        List<int[]> snakeList = new LinkedList<>();  //뱀의 몸 (index 0이 꼬리)
        snakeList.add(new int[]{1, 1});
        snake[1][1] = true;
        int r = 1, c = 1;  //뱀의 위치
        int d = 1;  //향하는 방향  *참고: 북0 동1 남2 서3
        int count = 0;  //게임 시작 시간으로부터 지난 시간

        for (int i = 0; i <= L; i++) {
            while (count < changeTime[i]) {  //Step1. 현재 방향으로 i번 변환점까지 가서
                count++;

                int nextR = r + DR[d], nextC = c + DC[d];

                if (nextR < 1 || nextR > N || nextC < 1 || nextC > N || snake[nextR][nextC]) {  //뱀이 벽이나 자기자신의 몸과 부딪히면, 게임 끝
                    System.out.println(count);
                    return;
                }

                r = nextR;
                c = nextC;

                if (apples[r][c]) apples[r][c] = false;  //사과가 있으면, 먹고
                else {  //사과가 없으면, 꼬리 자르기
                    int[] tail = snakeList.remove(0);
                    snake[tail[0]][tail[1]] = false;
                }
                snakeList.add(new int[]{r, c});  //머리 늘리기
                snake[r][c] = true;
            }

            //Step2. 방향(L/D) 변환
            if (changeDirection[i] == 'L') d = (d + 3) % 4;  //왼쪽으로 90도 회전
            else d = (d + 1) % 4;  //오른쪽으로 90도 회전
        }
    }
}
