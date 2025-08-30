package rerereview.programmers;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/42890
 * 42890. 후보키
 * 2025/08/30
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class 후보키 {
    List<List<Integer>> candiKeys = new ArrayList<>();
    int numCols, numTuples;

    public int solution(String[][] relation) {
        numCols = relation[0].length;
        numTuples = relation.length;

        for (int i = 1; i <= numCols; i++) {
            backtracking(i, 0, new ArrayList<>(), relation);
        }
        return candiKeys.size();
    }

    void backtracking(int combSize, int startColIdx, List<Integer> combCols, String[][] relation) {
        for (List<Integer> key : candiKeys) {
            for (int col : combCols) {
                if (combCols.containsAll(key)) return;
            }
        }

        if (combCols.size() == combSize) {
            Set<String> set = new HashSet<>();

            for (String[] tuple : relation) {
                StringBuilder sb = new StringBuilder();
                for (int col : combCols) {
                    sb.append(tuple[col]);
                }
                if (!set.add(sb.toString())) return;
            }
            candiKeys.add(new ArrayList<>(combCols));
            return;
        }

        for (int i = startColIdx; i < numCols; i++) {
            combCols.add(i);
            backtracking(combSize, i + 1, combCols, relation);
            combCols.remove(combCols.size() - 1);
        }
    }
}
