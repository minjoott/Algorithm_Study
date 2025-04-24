package leetcode;

import java.util.ArrayList;
import java.util.List;

public class Permutations {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        backtracking(nums, new ArrayList<>(), ans);
        return ans;
    }

    void backtracking(int[] nums, List<Integer> cur, List<List<Integer>> ans) {
        if (cur.size() == nums.length) {
            ans.add(new ArrayList<>(cur));
            return;
        }

        for (int num : nums) {
            if (!cur.contains(num)) {
                cur.add(num);
                backtracking(nums, cur, ans);
                cur.remove(cur.size() - 1);
            }
        }
    }
}
