package softeer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 나무공격 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];
        int attackerNum = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] == 1) attackerNum++;
            }
        }

        int[][] shield = new int[2][2];
        for (int i = 0; i < 2; i++) {
            st = new StringTokenizer(br.readLine());
            shield[i][0] = Integer.parseInt(st.nextToken()) - 1;  // L
            shield[i][1] = Integer.parseInt(st.nextToken()) - 1;  // R
        }

        for (int[] s : shield) {
            int L = s[0], R = s[1];
            for (int i = L; i <= R; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] == 1) {
                        map[i][j] = 0;
                        attackerNum--;
                        break;
                    }
                }
            }
        }

        System.out.println(attackerNum);
        return;
    }
}
