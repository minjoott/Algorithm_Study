package problems.programmers;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/340211
 * [Programmers] 450211. 충돌위험 찾기
 * solved at: 251113
 */

import java.util.HashMap;
import java.util.Map;

public class 충돌위험찾기 {
    class Solution {
        public int solution(int[][] points, int[][] routes) {
            int n = points.length;
            int x = routes.length;
            int m = routes[0].length;

            int answer = 0;  //위험한 상황 발생 횟수
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < x; i++) {
                int start = routes[i][0];
                if (map.containsKey(start) && map.get(start) == 1) answer++;
                map.put(start, map.getOrDefault(start, 0) + 1);
            }

            int[][][] visited = new int[101][101][200 * 100];  //동일한 시간에 (r,c)에 방문한 로봇 개수
            int[] cntByRobot = new int[x];
            for (int i = 0; i < m - 1; i++) {
                for (int j = 0; j < x; j++) {  //로봇 j
                    int[] curr = points[routes[j][i] - 1];  //출발할 포인트 좌표
                    int[] next = points[routes[j][i + 1] - 1];  //도착할 포인트 좌표
                    int cnt = cntByRobot[j];

                    int r = curr[0], c = curr[1];
                    //행 먼저 이동
                    if (curr[0] > next[0]) {  //위로 이동
                        while (r != next[0]) {
                            r--;
                            cnt++;
                            visited[r][c][cnt]++;
                            if (visited[r][c][cnt] == 2) answer++;
                        }
                    }
                    else {  //아래로 이동
                        while (r != next[0]) {
                            r++;
                            cnt++;
                            visited[r][c][cnt]++;
                            if (visited[r][c][cnt] == 2) answer++;
                        }
                    }
                    //열 이동
                    if (curr[1] < next[1]) {  //오른쪽으로 이동
                        while (c != next[1]) {
                            c++;
                            cnt++;
                            visited[r][c][cnt]++;
                            if (visited[r][c][cnt] == 2) answer++;
                        }
                    }
                    else {  //왼쪽으로 이동
                        while (c != next[1]) {
                            c--;
                            cnt++;
                            visited[r][c][cnt]++;
                            if (visited[r][c][cnt] == 2) answer++;
                        }
                    }
                    cntByRobot[j] = cnt;
                }
            }
            return answer;
        }
    }
}
