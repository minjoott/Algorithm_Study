package rereview.programmers;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class 과제연습하기 {
    public String[] solution(String[][] plans) {
        String[] answer = new String[plans.length];

        List<Task> tasks = new LinkedList<>();
        for (String[] plan : plans) {
            tasks.add(new Task(plan));
        }
        tasks.sort(Comparator.comparingInt(task -> task.start));

        int completedCnt = 0;
        int arrayPtr = 0;
        Task currTask = tasks.remove(0);
        int nowTime = currTask.start;
        Stack<Task> stack = new Stack<>();

        while (completedCnt < plans.length) {
            int finish = nowTime + currTask.remaining;

            if (!tasks.isEmpty() && tasks.get(0).start <= finish) {
                Task nextTask = tasks.get(0);
                if (nextTask.start < finish) {
                    currTask.updateRemaining(finish - nextTask.start);
                    stack.push(currTask);
                }
                else {
                    answer[completedCnt++] = currTask.name;
                }
                nowTime = nextTask.start;
                currTask = tasks.remove(0);
            }
            else {
                answer[completedCnt++] = currTask.name;
                if (!stack.isEmpty()) {
                    currTask = stack.pop();
                    nowTime = finish;
                }
                else if (!tasks.isEmpty()) {
                    currTask = tasks.remove(0);
                    nowTime = currTask.start;
                }
                else {
                    break;
                }
            }
        }
        return answer;
    }

    class Task {
        String name;
        int start;
        int remaining;

        Task (String[] plan) {
            this.name = plan[0];
            String[] strs = plan[1].split(":");
            this.start = Integer.parseInt(strs[0]) * 60 + Integer.parseInt(strs[1]);
            this.remaining = Integer.parseInt(plan[2]);
        }

        Task updateRemaining(int remaining) {
            this.remaining = remaining;
            return this;
        }
    }
}
