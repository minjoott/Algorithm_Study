package programmers;

import java.util.*;

public class 등산코스정하기 {
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            graph.put(i, new ArrayList<>());
        }
        for (int[] path : paths) {
            int i = path[0], j = path[1], w = path[2];
            graph.get(i).add(new int[]{j, w});
            graph.get(j).add(new int[]{i, w});
        }

        Set<Integer> summitSet = new HashSet<>();
        for (int summit : summits) {
            summitSet.add(summit);
        }

        int[] minIntensity = new int[n + 1];
        Arrays.fill(minIntensity, 100000001);
        Queue<int[]> pq = new PriorityQueue<>((a, b) -> (a[1] - b[1]));
        for (int gate : gates) {
            minIntensity[gate] = 0;
            pq.add(new int[]{gate, 0});
        }
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int curNode = curr[0], curIntensity = curr[1];
            if (curIntensity > minIntensity[curNode]) continue;

            for (int[] next : graph.get(curNode)) {
                int nextNode = next[0], nextIntensity = Math.max(curIntensity, next[1]);
                if (nextIntensity < minIntensity[nextNode]) {
                    minIntensity[nextNode] = nextIntensity;
                    if (!summitSet.contains(nextNode)) {
                        pq.add(new int[]{nextNode, nextIntensity});
                    }
                }
            }
        }

        int[] answer = new int[]{-1, 10000001};
        for (int summit : summitSet) {
            if (minIntensity[summit] < answer[1]) {
                answer[0] = summit;
                answer[1] = minIntensity[summit];
            }
            else if (minIntensity[summit] == answer[1]) {
                answer[0] = Math.min(summit, answer[0]);
            }
        }

        return answer;
    }
}
