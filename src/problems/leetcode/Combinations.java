package problems.leetcode;

/**
 * https://leetcode.com/problems/combinations/description/
 * [LeetCode] 77. Combinations
 * solved at: 251104
 */

import java.util.ArrayList;
import java.util.List;

public class Combinations {
    class Solution {
        List<List<Integer>> answer = new ArrayList<>();

        public List<List<Integer>> combine(int n, int k) {
            backtracking(1, new ArrayList<>(), n, k);
            return answer;
        }

        void backtracking(int start, List<Integer> curr, int n, int k) {
            if (curr.size() == k) {
                answer.add(new ArrayList<>(curr));
                return;
            }

            for (int i = start; i <= n; i++) {
                curr.add(i);
                backtracking(i + 1, curr, n, k);
                curr.remove(curr.size() - 1);
            }
        }
    }
}
