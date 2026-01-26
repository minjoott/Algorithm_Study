package problems.programmers;

import java.util.*;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/64065
 * [Programmers] 64065. 튜플
 * solved at: 260126
 */

public class 튜플 {
    class Solution1 {
        public int[] solution(String s) {
            List<Set<Integer>> allSet = new ArrayList<>();
            int start = 2;
            while (start < s.length()) {
                int end = start;
                while (s.charAt(end) != '}') end++;

                String str = s.substring(start, end);
                String[] elements = str.split(",");

                Set<Integer> set = new HashSet<>();
                allSet.add(set);
                for (String numStr : elements) {
                    int num = Integer.parseInt(numStr);
                    set.add(num);
                }

                start = end + 3;
            }

            Collections.sort(allSet, (a, b) -> a.size() - b.size());

            List<Integer> ansList = new ArrayList<>();
            for (Set<Integer> set : allSet) {
                for (int element : set) {
                    if (!ansList.contains(element)) {
                        ansList.add(element);
                        break;
                    }
                }
            }

            int[] answer = new int[ansList.size()];
            for (int i = 0; i < ansList.size(); i++) answer[i] = ansList.get(i);
            return answer;
        }
    }
}
