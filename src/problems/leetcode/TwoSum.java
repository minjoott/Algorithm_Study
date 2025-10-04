package problems.leetcode;

/**
 * https://leetcode.com/problems/two-sum/description/
 * [LeetCode] 1. Two Sum
 * solved at: 251004
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TwoSum {
    class Solution1_backtracking {
        public int[] twoSum(int[] nums, int target) {
            return backtracking(0, new ArrayList<>(), nums, target);
        }

        int[] backtracking(int startIdx, List<Integer> pair, int[] nums, int target) {
            if (pair.size() == 2) {
                int[] result = null;
                int idx1 = pair.get(0), idx2 = pair.get(1);
                if (nums[idx1] + nums[idx2] == target) {
                    result = new int[]{idx1, idx2};
                }
                return result;
            }

            for (int i = startIdx; i < nums.length; i++) {
                pair.add(i);
                int[] result = backtracking(i + 1, pair, nums, target);
                ;
                if (result != null) return result;
                pair.remove(pair.size() - 1);
            }
            return null;
        }
    }


    class Solution2_sortAndTwoPointer {
        public int[] twoSum(int[] nums, int target) {
            List<int[]> list = new ArrayList<>();
            for (int i = 0; i < nums.length; i++) {
                list.add(new int[]{nums[i], i});
            }
            list.sort((a, b) -> a[0] - b[0]);

            int l = 0, r = nums.length - 1;
            int[] result = null;
            while (l < r) {
                int sum = list.get(l)[0] + list.get(r)[0];
                if (sum < target) {
                    l++;
                } else if (sum > target) {
                    r--;
                } else {
                    result = new int[]{list.get(l)[1], list.get(r)[1]};
                    break;
                }
            }
            return result;
        }
    }


    class Solution3_hashmap {
        public int[] twoSum(int[] nums, int target) {
            Map<Integer, Integer> map = new HashMap<>();
            int[] answer = null;
            for (int i = 0; i < nums.length; i++) {
                if (map.containsKey(target - nums[i])) {
                    answer = new int[]{i, map.get(target - nums[i])};
                    break;
                }
                map.put(nums[i], i);
            }
            return answer;
        }
    }
}