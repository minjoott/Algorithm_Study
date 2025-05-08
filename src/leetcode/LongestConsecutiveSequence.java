package leetcode;

import java.util.HashSet;
import java.util.Set;

public class LongestConsecutiveSequence {
    public int longestConsecutive(int[] nums) {
        int n = nums.length;

        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        int longest = 0;
        for (int num : set) {
            if (!set.contains(num - 1)) {
                int len = 1;

                while (set.contains(++num)) {
                    len++;
                }

                longest = Math.max(len, longest);
            }
        }

        return longest;
    }
}
