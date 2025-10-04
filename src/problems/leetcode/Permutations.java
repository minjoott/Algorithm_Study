package problems.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/permutations/description/
 * [LeetCode] 46. Permutations
 * solved at: 251004
 */

public class Permutations {
    static class Solution1_backtracking {
        List<List<Integer>> answer = new ArrayList<>();

        public List<List<Integer>> permute(int[] nums) {
            backtracking(new ArrayList<>(), new boolean[nums.length], nums);
            return answer;
        }

        void backtracking(List<Integer> path, boolean[] visited, int[] nums) {
            if (path.size() == nums.length) {
                answer.add(new ArrayList<>(path));
                return;
            }

            for (int i = 0; i < nums.length; i++) {
                if (!visited[i]) {
                    path.add(nums[i]);
                    visited[i] = true;
                    backtracking(path, visited, nums);
                    path.remove(path.size() - 1);
                    visited[i] = false;
                }
            }
        }
    }
}
