package problems.programmers;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/118669
 * [Programmers] 118669. 등산코스 정하기
 * solved at: 251016
 */

import java.util.*;

public class 등산코스정하기 {
    class Solution1_dijkstra {
        public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
            Map<Integer, List<int[]>> map = new HashMap<>();
            for (int i = 1; i <= n; i++) {
                map.put(i, new ArrayList<>());
            }
            for (int[] path : paths) {
                int i = path[0], j = path[1], w = path[2];
                map.get(i).add(new int[]{j, w});
                map.get(j).add(new int[]{i, w});
            }

            Set<Integer> summitSet = new HashSet<>();
            for (int i : summits) {
                summitSet.add(i);
            }

            Queue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
            int[] minIntensity = new int[n + 1];  //1-indexing
            Arrays.fill(minIntensity, 100000001);

            //step1. 출입구를 우선순위큐에 모두 넣기
            for (int g : gates) {
                pq.add(new int[]{g, 0});
                minIntensity[g] = 0;
            }

            //step2. 다익스트라 수행
            while (!pq.isEmpty()) {
                int[] curr = pq.remove();
                int no = curr[0], intensity = curr[1];
                if (intensity > minIntensity[no]) continue;

                for (int[] next : map.get(no)) {
                    int nextNo = next[0], newIntensity = Math.max(next[1], intensity);
                    if (newIntensity < minIntensity[nextNo]) {
                        minIntensity[nextNo] = newIntensity;
                        if (summitSet.contains(nextNo)) continue;
                        pq.add(new int[]{nextNo, newIntensity});
                    }
                }
            }

            //step3. 정답 리턴
            int[] answer = new int[]{n + 1, 100000001};
            for (int s : summits) {
                if (minIntensity[s] < answer[1]) {
                    answer[0] = s;
                    answer[1] = minIntensity[s];
                }
                else if (minIntensity[s] == answer[1]) {
                    answer[0] = Math.min(s, answer[0]);
                }
            }
            return answer;
        }
    }

}
