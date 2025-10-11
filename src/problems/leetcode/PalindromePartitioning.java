package problems.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/palindrome-partitioning/
 * [LeetCode] 131. Palindrome Partitioning
 * solved at: 251006
 */

public class PalindromePartitioning {
    class Solution1_backtracking {
        List<List<String>> answer = new ArrayList<>();

        public List<List<String>> partition(String s) {
            backtracking(0, new ArrayList<>(), s);
            return answer;
        }

        void backtracking(int startIdx, List<String> subStrs, String s) {
            if (startIdx == s.length()) {
                answer.add(new ArrayList<>(subStrs));
                return;
            }

            for (int i = startIdx; i < s.length(); i++) {
                String subStr = s.substring(startIdx, i + 1);
                if (isPalindrome(subStr)) {
                    subStrs.add(subStr);
                    backtracking(i + 1, subStrs, s);
                    subStrs.remove(subStrs.size() - 1);
                }
            }
        }

        boolean isPalindrome(String str) {
            int l = 0, r = str.length() - 1;
            while (l < r) {
                if (str.charAt(l++) != str.charAt(r--)) return false;
            }
            return true;
        }
    }
}
