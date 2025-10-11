package problems.programmers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/42890
 * [Programmers] 42890. 후보키
 * solved at: 251009
 */

public class 후보키 {

    class Solution1_backtrackingAndHashSet {
        int degree;
        int cardinality;
        List<Set<Integer>> candidates = new ArrayList<>();

        public int solution(String[][] relation) {
            degree = relation[0].length;
            cardinality = relation.length;

            for (int i = 1; i <= degree; i++) {
                backtracking(0, i, new HashSet<>(), relation);
            }

            return candidates.size();
        }

        void backtracking(int startIdx, int colsNum, Set<Integer> colsSet, String[][] relation) {
            if (colsSet.size() == colsNum) {
                for (Set<Integer> cand : candidates) {
                    if (colsSet.containsAll(cand)) return;
                }

                Set<String> set = new HashSet<>();
                for (int i = 0; i < cardinality; i++) {
                    StringBuilder sb = new StringBuilder();
                    for (int col : colsSet) {
                        sb.append(relation[i][col]);
                    }
                    if (set.contains(sb.toString())) return;
                    set.add(sb.toString());
                }
                candidates.add(new HashSet<>(colsSet));
                return;
            }

            for (int i = startIdx; i < degree; i++) {
                colsSet.add(i);
                backtracking(i + 1, colsNum, colsSet, relation);
                colsSet.remove(i);
            }
        }
    }
}
