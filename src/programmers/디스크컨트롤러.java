package programmers;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class 디스크컨트롤러 {
    public int solution(int[][] oldJobs) {
        int N = oldJobs.length;

        int[][] jobs = new int[N][3];
        for (int i = 0; i < N; i++) {
            int request = oldJobs[i][0];
            int duration = oldJobs[i][1];
            int no = i;
            jobs[i] = new int[]{request, duration, no};
        }
        Arrays.sort(jobs, (j1, j2) -> j1[0] - j2[0]);

        int time = 0;
        int visitedIdx = -1;
        int totalProcessTime = 0;
        int completedCnt = 0;
        Queue<int[]> pq = new PriorityQueue<>((p1, p2) -> {
            if (p1[1] != p2[1]) return p1[1] - p2[1];
            return p1[2] - p2[2];
        });
        while (completedCnt < N) {
            if (!pq.isEmpty()) {
                int[] curJob = pq.poll();
                int curRequest = curJob[0];
                int curDuration = curJob[1];
                int curNo = curJob[2];

                time += curDuration;
                int processTime = time - curRequest;
                totalProcessTime += processTime;

                completedCnt++;
            }
            else {
                ++visitedIdx;
                pq.add(jobs[visitedIdx]);
                time = jobs[visitedIdx][0];
            }

            while (visitedIdx + 1 < N && jobs[visitedIdx + 1][0] <= time) {
                pq.add(jobs[++visitedIdx]);
            }
        }

        return totalProcessTime / N;
    }
}
