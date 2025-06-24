package review.leetcode;

import java.util.ArrayList;
import java.util.List;

public class Subsets {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        backtracking(new ArrayList<>(), ans, 0, nums);
        return ans;
    }

    void backtracking(List<Integer> curr, List<List<Integer>> ans, int start, int[] nums) {
        ans.add(new ArrayList<>(curr));

        for (int i = start; i < nums.length; i++) {
            curr.add(nums[i]);
            backtracking(curr, ans, i + 1, nums);
            curr.remove(curr.size() - 1);
        }
    }
}
