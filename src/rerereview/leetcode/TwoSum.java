package rerereview.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/** https://leetcode.com/problems/two-sum/description/
 * 1. Two Sum
 * 2025/08/25
 */

class TwoSum_for {
    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{-1, -1};
    }
}

class TwoSum_backtracking {
    public int[] twoSum(int[] nums, int target) {
        return backtracking(0, new ArrayList<>(), nums, target);
    }

    int[] backtracking(int currIdx, List<Integer> list, int[] nums, int target) {
        if (list.size() == 2) {
            int idx1 = list.get(0), idx2 = list.get(1);
            if (nums[idx1] + nums[idx2] == target) {
                return new int[]{idx1, idx2};
            }
            return null;
        }

        for (int i = currIdx; i < nums.length; i++) {
            list.add(i);
            int[] result = backtracking(i + 1, list, nums, target);
            if (result != null) {
                return result;
            }
            list.remove(list.size() - 1);
        }
        return null;
    }
}

class TwoSum_sortAndTwoPointer {
    public int[] twoSum(int[] nums, int target) {
        int[][] sortedNums = new int[nums.length][2];
        for (int i = 0; i < nums.length; i++) {
            sortedNums[i][0] = nums[i];
            sortedNums[i][1] = i;
        }
        Arrays.sort(sortedNums, (a, b) -> a[0] - b[0]);

        int l = 0;
        int r = nums.length - 1;
        while (l < r) {
            int sum = sortedNums[l][0] + sortedNums[r][0];
            if (sum < target) {
                ++l;
            }
            else if (sum > target) {
                --r;
            }
            else {
                return new int[]{sortedNums[l][1], sortedNums[r][1]};
            }
        }
        return new int[]{-1, -1};
    }
}

