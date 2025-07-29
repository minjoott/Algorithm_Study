package rereview.programmers;

import java.util.*;

public class 등산코스정하기 {
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
        for (int summit : summits) {
            summitSet.add(summit);
        }

        int[] minIntensityPerNode = new int[n + 1];
        Arrays.fill(minIntensityPerNode, Integer.MAX_VALUE);
        Queue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);

        for (int gate : gates) {
            minIntensityPerNode[gate] = 0;
            pq.offer(new int[]{gate, 0});
        }

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int currNode = curr[0], currIntensity = curr[1];

            if (currIntensity > minIntensityPerNode[currNode]) continue;

            for (int[] next : map.get(currNode)) {
                int nextNode = next[0], nextIntensity = Math.max(currIntensity, next[1]);
                if (nextIntensity < minIntensityPerNode[nextNode]) {
                    minIntensityPerNode[nextNode] = nextIntensity;

                    if (!summitSet.contains(nextNode)) {
                        pq.offer(new int[]{nextNode, nextIntensity});
                    }
                }
            }
        }

        int minSummit = Integer.MAX_VALUE;
        int minIntensity = Integer.MAX_VALUE;
        for (int summit : summits) {
            if (minIntensityPerNode[summit] < minIntensity) {
                minSummit = summit;
                minIntensity = minIntensityPerNode[summit];
            }
            else if (minIntensityPerNode[summit] == minIntensity) {
                minSummit = Math.min(summit, minSummit);
            }
        }
        return new int[]{minSummit, minIntensity};
    }
}
