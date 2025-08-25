package rerereview.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/subsets/description/
 * 78. Subsets
 * 2025/08/26
 */

public class Subsets {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        backtracking(0, new ArrayList<>(), ans, nums);
        return ans;
    }

    void backtracking(int startIdx, List<Integer> curr, List<List<Integer>> ans, int[] nums) {
        ans.add(new ArrayList<>(curr));

        if (startIdx == nums.length) return;

        for (int i = startIdx; i < nums.length; i++) {
            curr.add(nums[i]);
            backtracking(i + 1, curr, ans, nums);
            curr.remove(curr.size() - 1);
        }
    }
}
