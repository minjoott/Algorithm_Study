package review.programmers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class 후보키 {
    private List<List<Integer>> candidates = new ArrayList<>();

    public int solution(String[][] relation) {
        backtracking(0, new ArrayList<>(), relation);
        return candidates.size();
    }

    private void backtracking(int start, List<Integer> curr, String[][] relation) {
        if (!curr.isEmpty() && isUnique(curr, relation)) {
            List<List<Integer>> toRemove = new ArrayList<>();
            for (List<Integer> cand : candidates) {
                if (cand.containsAll(curr)) {
                    toRemove.add(cand);
                }
            }
            candidates.removeAll(toRemove);
            candidates.add(new ArrayList<>(curr));
            return;
        }

        int colNum = relation[0].length;
        for (int i = start; i < colNum; i++) {
            curr.add(i);
            backtracking(i + 1, curr, relation);
            curr.remove(curr.size() - 1);
        }
    }

    private boolean isUnique(List<Integer> curr, String[][] relation) {
        int tupleNum = relation.length;
        Set<String> set = new HashSet<>();
        for (int i = 0; i < tupleNum; i++) {
            String str = "|";
            for (int col : curr) {
                str += (relation[i][col] + "|");
            }
            if (!set.add(str)) {
                return false;
            }
        }
        return true;
    }
}
