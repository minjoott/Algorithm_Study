package rereview.programmers;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class 디스크컨트롤러 {
    public int solution(int[][] jobsArr) {
        int jobCnt = jobsArr.length;

        List<Job> jobs = new LinkedList<>();
        for (int i = 0; i < jobCnt; i++) {
            int request = jobsArr[i][0], duration = jobsArr[i][1];
            jobs.add(new Job(request, duration, i));
        }
        jobs.sort((a, b) -> a.request - b.request);

        int totalProcessTime = 0;
        int completedCnt = 0;
        int nowTime = 0;
        Queue<Job> readyQueue = new PriorityQueue<>(
                (a, b) -> {
                    if (a.duration - b.duration != 0) return a.duration - b.duration;
                    if (a.request - b.request != 0) return a.request - b.request;
                    return a.number - b.number;
                });

        while (completedCnt < jobCnt) {
            if (!readyQueue.isEmpty()) {
                Job processingJob = readyQueue.poll();
                totalProcessTime += nowTime + processingJob.duration - processingJob.request;
                nowTime += processingJob.duration;
                ++completedCnt;
            }

            while (!jobs.isEmpty() && jobs.get(0).request <= nowTime) {
                Job jobToProcess = jobs.remove(0);
                readyQueue.offer(jobToProcess);
            }

            if (readyQueue.isEmpty() && !jobs.isEmpty()) {
                nowTime = jobs.get(0).request;
                while (!jobs.isEmpty() && jobs.get(0).request == nowTime) {
                    Job jobToProcess = jobs.remove(0);
                    readyQueue.offer(jobToProcess);
                }
            }
        }

        return totalProcessTime / jobsArr.length;
    }

    class Job {
        int request;
        int duration;
        int number;

        Job (int request, int duration, int number) {
            this.request = request;
            this.duration = duration;
            this.number = number;
        }
    }
}
