package problems.programmers;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/92341
 * [Programmers] 92451. 주차 요금 계산 (2022 카카오)
 * solved at: 260102
 */

import java.util.*;

public class 주차요금계산 {
    class Solution1 {
        public int[] solution(int[] fees, String[] records) {
            StringTokenizer st;

            Map<Integer, Integer> parkedCars = new HashMap<>();
            Map<Integer, Integer> tatalTimeByCar = new HashMap<>();
            for (String r : records) {
                st = new StringTokenizer(r);
                String[] timeArr = st.nextToken().split(":");
                int time = Integer.parseInt(timeArr[0]) * 60 + Integer.parseInt(timeArr[1]);
                int carNo = Integer.parseInt(st.nextToken());
                String act = st.nextToken();

                if (act.equals("IN")) {  //입차
                    parkedCars.put(carNo, time);
                }
                else {  //출차
                    int times = time - parkedCars.remove(carNo);
                    tatalTimeByCar.put(carNo, tatalTimeByCar.getOrDefault(carNo, 0) + times);
                }
            }

            if (!parkedCars.isEmpty()) {
                for (Map.Entry<Integer, Integer> parked : parkedCars.entrySet()) {
                    int carNo = parked.getKey();
                    int startTime = parked.getValue();

                    int totalTime = (23 * 60 + 59) - startTime;
                    tatalTimeByCar.put(carNo, tatalTimeByCar.getOrDefault(carNo, 0) + totalTime);
                }
            }

            List<int[]> list = new ArrayList<>();
            for (Map.Entry<Integer, Integer> receipt : tatalTimeByCar.entrySet()) {
                int carNo = receipt.getKey();
                int totalTime = receipt.getValue();

                int price = fees[1];
                int overTimes = totalTime - fees[0];
                if (overTimes > 0) {
                    price += (int) Math.ceil((double) overTimes / fees[2]) * fees[3];
                }
                list.add(new int[]{carNo, price});
            }
            list.sort((a, b) -> a[0] - b[0]);

            int[] answer = new int[list.size()];
            for (int i = 0; i < list.size(); i++) {
                answer[i] = list.get(i)[1];
            }
            return answer;
        }
    }
}
