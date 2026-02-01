package problems.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/** # 문제
 * - 입력: .은 빈 공간, *는 벽, $는 문서, 알파벳 대문자는 문, 알파벳 소문자는 열쇠(대문자의 문을 열 수 있음)
 * - 출력: 각 빌딩마다, 상근이가 훔칠 수 있는 문서의 최대 개수
 * - 상근이는 빌딩 가장자리의 벽이 아닌 곳을 통해 빌딩 들어갈 수 있음
 * - 입력받을 때, 빌딩 가장자리 중에서 *이 아닌 곳은 모두 entrances(list)에 저장 (r, c, ch)
 * - 입력받은 열쇠들을 모두 대문자로 변환해 keys(set)에 저장
 * - entrances의 모든 원소에 대해, ch가 .이면 just 출발! ch가 keys에 존재하면 출발!
 * - BFS로 열쇠 존재, 빈 공간들을 따라가면서 documentCnt get!
 */

public class 열쇠 {
    static int[] DR = {1, 0, -1, 0};
    static int[] DC = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        Set<Character> keys = new HashSet<>();

        for (int t = 0; t < T; t++) {  //빌딩
            String[] HW = br.readLine().split(" ");
            int H = Integer.parseInt(HW[0]);
            int W = Integer.parseInt(HW[1]);

            char[][] map = new char[H][W];
            List<Entry> entrances = new ArrayList<>();
            for (int i = 0; i < H; i++) {
                String row = br.readLine();
                for (int j = 0; j < W; j++) {
                    map[i][j] = row.charAt(j);

                    if (i == 0 || i == H - 1 || j == 0 || j == W - 1 && map[i][j] != '*') {
                        entrances.add(new Entry(i, j, map[i][j]));
                    }
                    if (map[i][j] >= 'a' && map[i][j] <= 'z') {
                        char key = (char) ('A' + (map[i][j] - 'a'));
                        keys.add(key);
                    }
                }
            }

            String keysStr = br.readLine();

            for (char k : keysStr.toCharArray()) {  //열쇠가 있어 들어갈 수 있는 문들
                char key = (char) ('A' + (k - 'a'));
                keys.add(key);
            }

            int documentCnt = 0;  //획득한 문서의 개수
            boolean[][][] visited = new boolean[H][W][4];
            Deque<Path> queue = new ArrayDeque<>();

            for (Entry e : entrances) {
                if (e.ch >= 'A' && e.ch <= 'Z' && !keys.contains(e.ch)) continue;

                queue.add(new Path(e.r, e.c, 0, e.ch));
                visited[e.r][e.c][0] = true;
                visited[e.r][e.c][1] = true;
                visited[e.r][e.c][2] = true;
                visited[e.r][e.c][3] = true;

                while (!queue.isEmpty()) {
                    Path curr = queue.remove();

                    if (curr.ch == '$') documentCnt++;

                    for (int d = 0; d < 4; d++) {
                        int nr = curr.r + DR[d], nc = curr.c + DC[d];

                        if (nr < 0 || nr >= H || nc < 0 || nc >= W) continue;
                        char nch = map[nr][nc];

                        if (visited[nr][nc][d]) continue;
                        if (nch == '*') continue;
                        if (nch >= 'A' && nch <= 'Z'
                            && !keys.contains(nch)) continue;

                        if (nch >= 'a' && nch <= 'z') {
                            char key = (char) ('A' + (nch - 'a'));
                            keys.add(key);
                        }

                        queue.add(new Path(nr, nc, d, nch));
                        visited[nr][nc][d] = true;
                    }
                }
            }

            System.out.println(documentCnt);
        }
    }

    static class Entry {
        int r, c;
        char ch;

        Entry (int r, int c, char ch) {
            this.r = r; this.c = c;
            this.ch = ch;
        }
    }

    static class Path {
        int r, c;
        int d;
        char ch;

        Path (int r, int c, int d, char ch) {
            this.r = r; this.c = c;
            this.d = d;
            this.ch = ch;
        }
    }
}
