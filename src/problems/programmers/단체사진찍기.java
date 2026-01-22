package problems.programmers;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/1835
 * [Programmers] 1835. 단체사진 찍기
 * solved at: 260122
 */

import java.util.HashMap;
import java.util.Map;

public class 단체사진찍기 {
    class Solution1 {
        int answer = 0;

        public int solution(int n, String[] data) {
            Condition[] conditions = new Condition[n];
            for (int i = 0; i < n; i++) conditions[i] = new Condition(data[i]);

            char[] friends = new char[]{'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
            makeRow(new HashMap<>(), conditions, friends);
            return answer;
        }

        void makeRow(Map<Character, Integer> map, Condition[] conditions, char[] friends) {
            if (map.size() == 8) {
                int availableCnt = 0;
                for (Condition cond : conditions) {
                    int dist = Math.abs(map.get(cond.a) - map.get(cond.b)) - 1;

                    if (cond.sign == '=' && dist != cond.distance) return;
                    if (cond.sign == '<' && dist >= cond.distance) return;
                    if (cond.sign == '>' && dist <= cond.distance) return;
                }
                answer++;
                return;
            }

            for (char i : friends) {
                if (!map.containsKey(i)) {
                    map.put(i, map.size() + 1);
                    makeRow(map, conditions, friends);
                    map.remove(i);
                }
            }
        }

        class Condition {
            char a, b;
            char sign;
            int distance;

            Condition(String str) {
                a = str.charAt(0);
                b = str.charAt(2);
                sign = str.charAt(3);
                distance = str.charAt(4) - '0';
            }
        }
    }
}
