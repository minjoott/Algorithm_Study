package review.leetcode;

import java.util.ArrayList;
import java.util.List;

public class Combinations {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        backtracking(new ArrayList<>(), ans, 1, n, k);
        return ans;
    }

    void backtracking(List<Integer> curr, List<List<Integer>> ans, int start, int n, int k) {
        if (curr.size() == k) {
            ans.add(new ArrayList<>(curr));
            return;
        }

        for (int i = start; i <= n; i++) {
            curr.add(i);
            backtracking(curr, ans, i + 1, n, k);
            curr.remove(curr.size() - 1);
        }
    }
}
