package review.leetcode;

import java.util.*;

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
        return backtracking(new ArrayList<>(), 0, nums, target);
    }

    int[] backtracking(List<Integer> ans, int start, int[] nums, int target) {
        if (ans.size() == 2) {
            int idx1 = ans.get(0), idx2 = ans.get(1);
            if (nums[idx1] + nums[idx2] == target) {
                return new int[]{idx1, idx2};
            }
            return null;
        }

        for (int i = start; i < nums.length; i++) {
            ans.add(i);
            int[] result = backtracking(ans, i + 1, nums, target);
            if (result != null) {
                return result;
            }
            ans.remove(ans.size() - 1);
        }

        return null;
    }
}

class TwoSum_sortAndTwoPointer {
    public int[] twoSum(int[] oldNums, int target) {
        final int N = oldNums.length;
        int[][] nums = new int[N][2];
        for (int i = 0; i < N; i++) {
            nums[i] = new int[]{oldNums[i], i};
        }

        Arrays.sort(nums, (a, b) -> a[0] - b[0]);

        int front = 0, back = N - 1;
        while (front < back) {
            int sum = nums[front][0] + nums[back][0];
            if (sum < target) {
                front++;
            }
            else if (sum > target) {
                back--;
            }
            else {
                return new int[]{nums[front][1], nums[back][1]};
            }
        }

        return new int[]{-1, -1};
    }
}

class TwoSum_hashMap {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }
            else {
                map.put(nums[i], i);
            }
        }

        return new int[]{-1, -1};
    }
}