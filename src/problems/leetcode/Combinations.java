package problems.leetcode;

/**
 * https://leetcode.com/problems/combinations/description/
 * [LeetCode] 77. Combinations
 * solved at: 251004
 */

import java.util.ArrayList;
import java.util.List;

public class Combinations {
    class Solution1_backtracking {
        List<List<Integer>> answer = new ArrayList<>();

        public List<List<Integer>> combine(int n, int k) {
            backtracking(1, new ArrayList<>(), n, k);
            return answer;
        }

        void backtracking(int startNum, List<Integer> comb, int n, int k) {
            if (comb.size() == k) {
                answer.add(new ArrayList<>(comb));
                return;
            }

            for (int i = startNum; i <= n; i++) {
                comb.add(i);
                backtracking(i + 1, comb, n, k);
                comb.remove(comb.size() - 1);
            }
        }
    }
}
