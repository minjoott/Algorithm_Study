package leetcode;

import java.util.ArrayList;
import java.util.List;

public class Subsets {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        backtracking(0, nums, new ArrayList<>(), ans);
        return ans;
    }

    void backtracking(int start, int[] nums, List<Integer> cur, List<List<Integer>> ans) {
        ans.add(new ArrayList<>(cur));

        for (int i = start; i < nums.length; i++) {
            cur.add(nums[i]);
            backtracking(i + 1, nums, cur, ans);
            cur.remove(cur.size() - 1);
        }
    }
}
