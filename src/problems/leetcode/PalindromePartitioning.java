package problems.leetcode;

/**
 * https://leetcode.com/problems/palindrome-partitioning/
 * [LeetCode] 131. Palindrome Partitioning
 * solved at: 251106
 */

import java.util.ArrayList;
import java.util.List;

public class PalindromePartitioning {
    class Solution {
        List<List<String>> answer = new ArrayList<>();

        public List<List<String>> partition(String s) {
            backtracking(0, new ArrayList<>(), s);
            return answer;
        }

        void backtracking(int start, List<String> curr, String s) {
            if (start == s.length()) {
                answer.add(new ArrayList<>(curr));
                return;
            }
            for (int end = start; end < s.length(); end++) {
                String subStr = s.substring(start, end + 1);
                if (isPalindrome(subStr)) {
                    curr.add(subStr);
                    backtracking(end + 1, curr, s);
                    curr.remove(curr.size() - 1);
                }
            }
        }

        boolean isPalindrome(String str) {
            int l = 0;
            int r = str.length() - 1;
            while (l < r) {
                if (str.charAt(l++) != str.charAt(r--)) return false;
            }
            return true;
        }
    }
}
