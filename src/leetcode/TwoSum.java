package leetcode;

import java.util.*;

class TwoSum_for {
    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length - 1; i++) {
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
        List<Integer> ans = new ArrayList<>();
        backtracking(0, nums, target, ans);
        return new int[]{ans.get(0), ans.get(1)};
    }

    boolean backtracking(int start, int[] nums, int target, List<Integer> ans) {
        if (ans.size() == 2) {
            if (nums[ans.get(0)] + nums[ans.get(1)] == target) {
                return true;
            }
            return false;
        }

        for (int i = start; i < nums.length; i++) {
            ans.add(i);
            if (backtracking(i + 1, nums, target, ans)) return true;
            ans.remove(ans.size() - 1);
        }

        return false;
    }
}

class TwoSum_twoPointer {
    public int[] twoSum(int[] nums, int target) {
        int[][] sortedNums = new int[nums.length][2];
        for (int i = 0; i < nums.length; i++) {
            sortedNums[i][0] = nums[i];
            sortedNums[i][1] = i;
        }

        Arrays.sort(sortedNums, (n1, n2) -> n1[0] - n2[0]);

        int front = 0, back = nums.length - 1;
        while (front < back) {
            int sum = sortedNums[front][0] + sortedNums[back][0];
            if (sum < target)
                front++;
            else if (sum > target)
                back--;
            else
                return new int[]{sortedNums[front][1], sortedNums[back][1]};
        }

        return new int[]{-1, -1};
    }
}

class TwoSum_hashMap {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> hashtable = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (hashtable.containsKey(target - nums[i])) {
                return new int[]{hashtable.get(target - nums[i]), i};
            }
            hashtable.put(nums[i], i);
        }

        return new int[]{-1, -1};
    }
}

