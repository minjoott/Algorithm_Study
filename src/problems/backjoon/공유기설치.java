package problems.backjoon;

/**
 * https://www.acmicpc.net/problem/2110
 * [Backjoon] 2110. 공유기 설치
 * solved at: 251130
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 공유기설치 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        int[] house = new int[N];
        for (int i = 0; i < N; i++) house[i] = Integer.parseInt(br.readLine());
        Arrays.sort(house);

        int left = 1;
        int right = house[N - 1] - house[0];  //집 간에 가장 먼 거리
        while (left <= right) {
            int mid = (left + right) / 2;  //가장 인접한 두 공유기 사이의 거리
            int count = 0;  //최소 mid 만큼의 거리로 설치할 수 있는 공유기의 수
            int prevWifi = house[0];  //일단 첫번째 집에 공유기 설치
            count++;
            for (int i = 1; i < N; i++) {
                if (count >= C) break;
                if (house[i] - prevWifi >= mid) {  //mid 거리와 같거나 더 멀다면 공유기 설치
                    prevWifi = house[i];
                    count++;
                }
            }
            if (count >= C) left = mid + 1;
            else right = mid - 1;
        }

        System.out.println(right);
        return;
    }
}
