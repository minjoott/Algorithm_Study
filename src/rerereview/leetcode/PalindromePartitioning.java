package rerereview.leetcode;

/**
 * https://leetcode.com/problems/palindrome-partitioning/
 * 131. Palindrome Partitioning
 * 2025/08/29
 */

import java.util.ArrayList;
import java.util.List;

class PalindromePartitioning {
    List<List<String>> ans = new ArrayList<>();

    public List<List<String>> partition(String s) {
        backtracking(0, new ArrayList<>(), s);
        return ans;
    }

    void backtracking(int startIdx, List<String> list, String s) {
        if (startIdx == s.length()) {
            ans.add(new ArrayList<>(list));
            return;
        }

        for (int endIdx = startIdx; endIdx < s.length(); endIdx++) {
            String subStr = s.substring(startIdx, endIdx + 1);
            if (checkPalindrome(subStr)) {
                list.add(subStr);
                backtracking(endIdx + 1, list, s);
                list.remove(list.size() - 1);
            }
        }
    }

    boolean checkPalindrome(String str) {
        int l = 0;
        int r = str.length() - 1;

        while (l < r) {
            if (str.charAt(l++) != str.charAt(r--)){
                return false;
            }
        }
        return true;
    }
}
