package problems.programmers;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/68645
 * [Programmers] 68645. 삼각 달팽이
 * solved at: 260119
 */

public class 삼각달팽이 {
    class Solution1 {
        public int[] solution(int n) {
            int[][] array = new int[n][];
            for (int i = 0; i < n; i++) array[i] = new int[i + 1];

            int cnt = n * (n + 1) / 2;
            int value = 0;
            int length = n;
            int x = 0;
            int y = -1;
            while (length > 0) {
                for (int i = 0; i < length; i++) array[++y][x] = ++value;
                if (array[y][x] == cnt) break;

                for (int i = 0; i < length - 1; i++) array[y][++x] = ++value;
                if (array[y][x] == cnt) break;

                for (int i = 0; i < length - 2; i++) array[--y][--x] = ++value;
                if (array[y][x] == cnt) break;

                length -= 3;
            }

            int[] answer = new int[cnt];
            int ptr = 0;
            for (int i = 0; i < n; i++) {
                int[] row = array[i];
                for (int j = 0; j < row.length; j++)
                    answer[ptr++] = row[j];
            }
            return answer;
        }
    }
}
