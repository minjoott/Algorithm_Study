package rerereview.programmers;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/42627
 * 42627. 디스크 컨트롤러
 * 2025/09/10
 */

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class 디스크컨트롤러 {
    public int solution(int[][] jobs) {
        Job[] requestJobs = new Job[jobs.length];
        for (int i = 0; i < jobs.length; i++) {
            int request = jobs[i][0];
            int duration = jobs[i][1];
            requestJobs[i] = new Job(request, duration, i);
        }
        Arrays.sort(requestJobs, (a, b) -> a.request - b.request);

        Queue<Job> readyQueue = new PriorityQueue<>((a, b) -> {
            if (a.duration != b.duration) return a.duration - b.duration;
            else if (a.request != b.request) return a.request - b.request;
            else return a.no - b.no;
        });

        int ptr = 0;
        int nowTime = 0;
        int totalTurnaround = 0;
        for (int i = 0; i < jobs.length; i++) {
            if (readyQueue.isEmpty()) {
                nowTime = requestJobs[ptr].request;
                while (ptr < jobs.length && requestJobs[ptr].request == nowTime) {
                    readyQueue.add(requestJobs[ptr++]);
                }
            }

            Job curr = readyQueue.remove();
            int finish = nowTime + curr.duration;
            totalTurnaround += finish - curr.request;

            while (ptr < jobs.length && requestJobs[ptr].request <= finish) {
                readyQueue.add(requestJobs[ptr++]);
            }
            nowTime = finish;
        }
        return (int)(totalTurnaround / jobs.length);
    }

    class Job {
        int request;
        int duration;
        int no;

        Job (int request, int duration, int no) {
            this.request = request;
            this.duration = duration;
            this.no = no;
        }
    }
}
