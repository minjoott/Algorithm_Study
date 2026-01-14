package problems.programmers;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/12978
 * [Programmers] 12978. 배달
 * solved at: 260114
 */

import java.util.*;

public class 배달 {
    class Solution1 {
        public int solution(int N, int[][] road, int K) {
            //양방향 그래프 생성
            Map<Integer, List<int[]>> map = new HashMap<>();
            for (int i = 1; i <= N; i++) map.put(i, new ArrayList<>());
            for (int[] r : road) {
                int a = r[0], b = r[1], c = r[2];
                map.get(a).add(new int[]{b, c});
                map.get(b).add(new int[]{a, c});
            }

            //1번 마을을 시작으로 다익스트라 수행
            Set<Integer> possible = new HashSet<>();
            Queue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
            int[] times = new int[N + 1];
            Arrays.fill(times, 500001);
            pq.add(new int[]{1, 0});
            times[1] = 0;
            while (!pq.isEmpty()) {
                int[] curr = pq.remove();
                int node = curr[0];
                int time = curr[1];

                if (time > times[node]) continue;

                possible.add(node);

                for (int[] next : map.get(node)) {
                    int nextNode = next[0];
                    int nextTime = time + next[1];

                    if (nextTime < times[nextNode] && nextTime <= K) {
                        times[nextNode] = nextTime;
                        pq.add(new int[]{nextNode, nextTime});
                    }
                }
            }
            return possible.size();
        }
    }
}
