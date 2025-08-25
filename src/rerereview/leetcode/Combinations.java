package rerereview.leetcode;

/**
 * https://leetcode.com/problems/combinations/description/
 * 77. Combinations
 * 2025/08/26
 */

import java.util.ArrayList;
import java.util.List;

public class Combinations {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        backtracking(1, new ArrayList<>(), ans, n, k);
        return ans;
    }

    void backtracking(int startNum, List<Integer> curr, List<List<Integer>> ans, int n, int k) {
        if (curr.size() == k) {
            ans.add(new ArrayList<>(curr));
            return;
        }

        for (int i = startNum; i <= n; i++) {
            curr.add(i);
            backtracking(i + 1, curr, ans, n, k);
            curr.remove(curr.size() - 1);
        }
    }
}
