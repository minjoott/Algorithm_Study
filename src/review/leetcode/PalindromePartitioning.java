package review.leetcode;

import java.util.ArrayList;
import java.util.List;

public class PalindromePartitioning {
    private List<List<String>> ans = new ArrayList<>();

    public List<List<String>> partition(String str) {
        backtracking(0, new ArrayList<>(), str);
        return ans;
    }

    private void backtracking(int start, List<String> curr, String str) {
        if (start == str.length()) {
            ans.add(new ArrayList<>(curr));
            return;
        }

        for (int end = start + 1; end <= str.length(); end++) {
            String subStr = str.substring(start, end);
            if (isPalindrome(subStr)) {
                curr.add(subStr);
                backtracking(end, curr, str);
                curr.remove(curr.size() - 1);
            }
        }
    }

    private boolean isPalindrome(String str) {
        int front = 0, back = str.length() - 1;
        while (front < back) {
            if (str.charAt(front++) != str.charAt(back--)) {
                return false;
            }
        }
        return true;
    }
}
