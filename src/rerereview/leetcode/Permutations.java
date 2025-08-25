package rerereview.leetcode;

/**
 * https://leetcode.com/problems/permutations/description/
 * 46. Permutations
 * 2025/08/26
 */

import java.util.ArrayList;
import java.util.List;

class Permutations_noVisitedCheck {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        backtracking(new ArrayList<>(), ans, nums);
        return ans;
    }

    void backtracking(List<Integer> curr, List<List<Integer>> ans, int[] nums) {
        if (curr.size() == nums.length) {
            ans.add(new ArrayList<>(curr));
            return;
        }

        for (int num : nums) {
            if (!curr.contains(num)) {
                curr.add(num);
                backtracking(curr, ans, nums);
                curr.remove(curr.size() - 1);
            }
        }
    }
}

class Permutations_yesVisitedCheck {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        backtracking(new ArrayList<>(), new boolean[nums.length], ans, nums);
        return ans;
    }

    void backtracking(List<Integer> curr, boolean[] visited, List<List<Integer>> ans, int[] nums) {
        if (curr.size() == nums.length) {
            ans.add(new ArrayList<>(curr));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (!visited[i]) {
                int num = nums[i];

                curr.add(num);
                visited[i] = true;
                backtracking(curr, visited, ans, nums);
                curr.remove(curr.size() - 1);
                visited[i] = false;
            }
        }
    }
}
