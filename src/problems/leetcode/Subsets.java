package problems.leetcode;

/**
 * https://leetcode.com/problems/subsets/description/
 * [LeetCode] 78. Subsets
 * solved at: 251104
 */

import java.util.ArrayList;
import java.util.List;

public class Subsets {
    class Solution {
        List<List<Integer>> answer = new ArrayList<>();

        public List<List<Integer>> subsets(int[] nums) {
            backtracking(0, new ArrayList<>(), nums);
            return answer;
        }

        void backtracking(int start, List<Integer> curr, int[] nums) {
            answer.add(new ArrayList<>(curr));

            for (int i = start; i < nums.length; i++) {
                curr.add(nums[i]);
                backtracking(i + 1, curr, nums);
                curr.remove(curr.size() - 1);
            }
        }
    }
}
