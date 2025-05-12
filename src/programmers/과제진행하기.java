package programmers;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class 과제진행하기 {
    public String[] solution(String[][] oldPlans) {
        int N = oldPlans.length;

        Plan[] plans = new Plan[N];
        for (int i = 0; i < N; i++) {
            plans[i] = new Plan(oldPlans[i]);
        }
        Arrays.sort(plans, (a, b) -> a.start - b.start);

        int completedCnt = 0;
        int ptr = 0;
        Entry curPlan = new Entry(plans[0].name, plans[0].duration);
        int curTime = plans[0].start;
        Deque<Entry> stack = new ArrayDeque<>();
        String[] answer = new String[N];

        while (completedCnt < N) {
            int end = curTime + curPlan.rest;
            if (ptr + 1 < N && plans[ptr + 1].start < end) {  // 현재 진행 중인 과제가 끝나기 전에 새로 시작할 과제가 있다면,
                ptr++;
                stack.push(new Entry(curPlan.name, end - plans[ptr].start));  // 진행 중이던 과제를 멈추고

                curTime = plans[ptr].start;  // 새로운 과제를 시작
                curPlan = new Entry(plans[ptr].name, plans[ptr].duration);
            }
            else {
                answer[completedCnt++] = curPlan.name;  // 현재 진행 중인 과제를 완료!
                curTime = end;

                if (completedCnt < N) {  // 수행해야 하는 과제가 더 남아있다면,
                    if (stack.isEmpty()) {  // 멈춰둔 과제가 없으면,
                        ++ptr;
                        curTime = plans[ptr].start;
                        curPlan = new Entry(plans[ptr].name, plans[ptr].duration);
                    }
                    else {  // 멈춰둔 과제가 있으면,
                        curPlan = stack.pop();
                    }
                }
            }
        }

        return answer;

    }

    class Plan {
        String name;
        int start;
        int duration;

        Plan(String[] plan) {
            this.name = plan[0];

            String[] str = plan[1].split(":");
            this.start = Integer.parseInt(str[0]) * 60 + Integer.parseInt(str[1]);

            this.duration = Integer.parseInt(plan[2]);
        }
    }

    class Entry {
        String name;
        int rest;

        Entry (String name, int rest) {
            this.name = name;
            this.rest = rest;
        }
    }
}
