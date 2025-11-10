package problems.programmers;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/42890
 * [Programmers] 42890. 후보키
 * solved at: 251106
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class 후보키 {
    class Solution {
        int n;
        int m;
        List<List<Integer>> candidates = new ArrayList<>();

        public int solution(String[][] relation) {
            n = relation.length;
            m = relation[0].length;

            for (int i = 1; i <= m; i++) {
                backtracking(0, i, new ArrayList<>(), relation);
            }
            return candidates.size();
        }

        void backtracking(int startCol, int totalColNum, List<Integer> curr, String[][] relation) {
            if (curr.size() == totalColNum) {
                for (List<Integer> candidate : candidates) {
                    if (curr.containsAll(candidate)) return;  //최소성 만족 보장
                }

                Set<String> set = new HashSet<>();  //유일성 판단
                for (int i = 0; i < n; i++) {
                    String str = "";
                    for (int j : curr) {
                        str += relation[i][j];
                    }
                    if (set.contains(str)) return;
                    set.add(str);
                }
                candidates.add(new ArrayList<>(curr));
                return;
            }

            for (int i = startCol; i < m; i++) {
                curr.add(i);
                backtracking(i + 1, totalColNum, curr, relation);
                curr.remove(curr.size() - 1);
            }
        }
    }
}
