package leetcode;

import java.util.ArrayList;
import java.util.List;

public class Combinations {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        backtracking(1, n, k, new ArrayList<>(), ans);
        return ans;
    }

    void backtracking(int start, int n, int k, List<Integer> cur, List<List<Integer>> ans) {
        if (cur.size() == k) {
            ans.add(new ArrayList<>(cur));
            return;
        }

        for (int i = start; i <= n; i++) {
            cur.add(i);
            backtracking(i + 1, n, k, cur, ans);
            cur.remove(cur.size() - 1);
        }
    }
}
