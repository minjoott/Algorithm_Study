package problems.programmers;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/72411
 * [Programmers] 72411. 메뉴 리뉴얼 (2021 카카오)
 * solved at: 251229
 */

import java.util.*;

public class 메뉴리뉴얼 {
    class Solution1 {
        public String[] solution(String[] orders, int[] course) {
            List<String> newMenus = new ArrayList<>();

            for (int c : course) {
                Map<String, Integer> combinations = new HashMap<>();
                for (String order : orders) {
                    if (order.length() < c) continue;
                    String sortedOrder = sortString(order);
                    makeCombination(sortedOrder, c, 0, new StringBuilder(), combinations);
                }
                int best = -1;
                List<String> bestCombinations = new ArrayList<>();
                for (String key : combinations.keySet()) {
                    int value = combinations.get(key);
                    if (value >= 2 && value >= best) {
                        if (value > best) {
                            best = value;
                            bestCombinations.clear();
                        }
                        bestCombinations.add(key);
                    }
                }
                for (String comb : bestCombinations) {
                    newMenus.add(comb);
                }
            }

            Collections.sort(newMenus);
            String[] answer = new String[newMenus.size()];
            for (int i = 0; i < newMenus.size(); i++) answer[i] = newMenus.get(i);
            return answer;
        }

        String sortString(String s) {
            char[] ch = s.toCharArray();
            Arrays.sort(ch);
            return new String(ch);
        }

        void makeCombination(String order, int c, int startIdx, StringBuilder curr, Map<String, Integer> combinations) {
            if (curr.length() == c) {
                String comb = curr.toString();
                combinations.put(comb, combinations.getOrDefault(comb, 0) + 1);
                return;
            }

            for (int i = startIdx; i < order.length(); i++) {
                curr.append(order.charAt(i));
                makeCombination(order, c, i + 1, curr, combinations);
                curr.deleteCharAt(curr.length() - 1);
            }
        }
    }
}
