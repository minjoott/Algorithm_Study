package problems.leetcode;

/**
 * https://leetcode.com/problems/permutations/description/
 * [LeetCode] 46. Permutations
 * solved at: 251104
 */

import java.util.ArrayList;
import java.util.List;

public class Permutations {
    class Solution {
        int N;
        List<List<Integer>> answer = new ArrayList<>();

        public List<List<Integer>> permute(int[] nums) {
            N = nums.length;
            backtracking(new ArrayList<>(), new boolean[N], nums);
            return answer;
        }

        void backtracking(List<Integer> list, boolean[] visited, int[] nums) {
            if (list.size() == N) {
                answer.add(new ArrayList<>(list));
                return;
            }

            for (int i = 0; i < N; i++) {
                if (!visited[i]) {
                    list.add(nums[i]);
                    visited[i] = true;
                    backtracking(list, visited, nums);
                    list.remove(list.size() - 1);
                    visited[i] = false;
                }
            }
        }
    }
}
