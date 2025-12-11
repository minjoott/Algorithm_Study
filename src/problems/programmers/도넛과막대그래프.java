package problems.programmers;

import java.util.*;

public class 도넛과막대그래프 {
    class Solution {
        public int[] solution(int[][] edges) {
            Map<Integer, Set<Integer>> graph = new HashMap<>();
            int[] inCnt = new int[1000001];
            int[] outCnt = new int[1000001];
            int newNode = -1;  //생성한 정점의 번호
            for (int[] edge : edges) {
                int a = edge[0];
                int b = edge[1];

                if (!graph.containsKey(a)) graph.put(a, new HashSet<>());
                graph.get(a).add(b);

                outCnt[a]++;
                inCnt[b]++;
            }

            for (int node : graph.keySet()) {
                if (graph.get(node).size() >= 2 && inCnt[node] == 0) newNode = node;
            }

            int donutCnt = 0;  //도넛 모양 그래프의 수
            int barCnt = 0;  //막대 모양 그래프의 수
            int eightCnt = 0;  //8자 모양 그래프의 수
            boolean[] visited = new boolean[1000001];
            for (int start : graph.get(newNode)) {
                Deque<Integer> queue = new ArrayDeque<>();
                queue.add(start);
                visited[start] = true;
                boolean found = false;
                while (!queue.isEmpty()) {
                    int curr = queue.remove();
                    if (inCnt[curr] >= 2 && outCnt[curr] == 2) {  //8자 모양 그래프
                        eightCnt++;
                        found = true;
                        break;
                    }
                    else if (inCnt[curr] >= 1 && outCnt[curr] == 0) {  //막대 모양 그래프
                        barCnt++;
                        found = true;
                        break;
                    }

                    for (int next : graph.get(curr)) {
                        if (visited[next]) continue;
                        queue.add(next);
                        visited[next] = true;
                    }
                }
                if (!found) donutCnt++;  //도넛 모양 그래프
            }

            return new int[]{newNode, donutCnt, barCnt, eightCnt};
        }
    }
}
