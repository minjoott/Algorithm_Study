package problems.programmers;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/42627
 * [Programmers] 42627. 디스크 컨트롤러
 * solved at: 251011
 */

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class 디스크컨트롤러 {
    class Solution1_sortAndpriorityqueueAndgreedy {
        public int solution(int[][] jobs) {
            final int N = jobs.length;

            int[][] requestJobs = new int[N][3];
            for (int i = 0; i < N; i++) {
                requestJobs[i][0] = jobs[i][0];
                requestJobs[i][1] = jobs[i][1];
                requestJobs[i][2] = i;
            }
            Arrays.sort(requestJobs, (a, b) -> a[0] - b[0]);

            Queue<int[]> pq = new PriorityQueue<>(
                    (a, b) -> {
                        if (a[1] != b[1]) return a[1] - b[1];  //소요시간
                        if (a[0] != b[0]) return a[0] - b[0];  //요청시각
                        return a[2] - b[2];

                    }
            );

            int now = 0;
            int totalTurnaround = 0;
            int ptr = 0;  //바로 다음 순서에 대기 큐에 들어가야 할 job (at requestJobs)
            for (int i = 0; i < N; i++) {
                if (pq.isEmpty()) {  //대기 큐가 비어있으면
                    now = requestJobs[ptr][0];
                    while (ptr < N && requestJobs[ptr][0] == now) {
                        pq.add(requestJobs[ptr++]);
                    }
                }
                int[] curr = pq.remove();  //대기 큐에서 작업 하나 꺼내서 처리
                int request = curr[0];
                int duration = curr[1];
                now += duration;  //종료
                totalTurnaround += (now - request);

                while (ptr < N && requestJobs[ptr][0] <= now) {
                    pq.add(requestJobs[ptr++]);
                }
            }

            return totalTurnaround / N;
        }
    }
}
