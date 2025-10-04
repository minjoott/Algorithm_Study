package problems.leetcode;

/**
 * https://leetcode.com/problems/subsets/description/
 * [LeetCode] 78. Subsets
 * solved at: 251004
 */

import java.util.ArrayList;
import java.util.List;

public class Subsets {
    class Solution1_backtracking {
        List<List<Integer>> answer = new ArrayList<>();

        public List<List<Integer>> subsets(int[] nums) {
            backtracking(0, new ArrayList<>(), nums);
            return answer;
        }

        void backtracking(int startIdx, List<Integer> path, int[] nums) {
            answer.add(new ArrayList<>(path));

            for (int i = startIdx; i < nums.length; i++) {
                path.add(nums[i]);
                backtracking(i + 1, path, nums);
                path.remove(path.size() - 1);
            }
        }
    }
}
