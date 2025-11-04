package problems.leetcode;

/**
 * https://leetcode.com/problems/two-sum/description/
 * [LeetCode] 1. Two Sum
 * solved at: 251104
 */

import java.util.*;

public class TwoSum {
    class Solution1 {  //백트래킹
        public int[] twoSum(int[] nums, int target) {
            return backtracking(0, new ArrayList<>(), nums, target);
        }
        int[] backtracking(int start, List<Integer> list, int[] nums, int target) {
            if (list.size() == 2) {
                int num1 = nums[list.get(0)], num2 = nums[list.get(1)];
                if (num1 + num2 == target) {
                    return new int[]{list.get(0), list.get(1)};
                }
                return null;
            }

            for (int i = start; i < nums.length; i++) {
                list.add(i);
                int[] result = backtracking(i + 1, list, nums, target);
                if (result != null) return result;
                list.remove(list.size() - 1);
            }

            return null;
        }
    }

    class Solution2 {  //정렬 + 투포인터
        public int[] twoSum(int[] nums, int target) {
            final int N = nums.length;

            int[][] sortedNums = new int[N][2];
            for (int i = 0; i < N; i++) {
                sortedNums[i] = new int[]{nums[i], i};
            }
            Arrays.sort(sortedNums, (a, b) -> a[0] - b[0]);

            int l = 0, r = N - 1;
            int[] result = null;
            while (l < r) {
                int sum = sortedNums[l][0] + sortedNums[r][0];
                if (sum == target) {
                    result = new int[]{sortedNums[l][1], sortedNums[r][1]};
                    break;
                }
                else if (sum < target) sum -= l++;
                else sum -= r--;
            }
            return result;
        }
    }

    class Solution3 {  //해시맵
        public int[] twoSum(int[] nums, int target) {
            Map<Integer, Integer> map = new HashMap<>();
            int[] answer = null;
            for (int i = 0; i < nums.length; i++) {
                int complement = target - nums[i];
                if (map.containsKey(complement)) {
                    answer = new int[]{i, map.get(complement)};
                    break;
                }
                map.put(nums[i], i);
            }
            return answer;
        }
    }
}
