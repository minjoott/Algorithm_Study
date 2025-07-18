package review.leetcode;

public class PermutationSequence {
    private int cnt = 0;

    public String getPermutation(int n, int k) {
        return backtracking("", n, k);
    }

    private String backtracking(String str, int n, int k) {
        if (str.length() == n) {
            if (++cnt == k) {
                return str;
            }
            return null;
        }

        for (int i = 1; i <= n; i++) {
            if (!str.contains(i + "")) {
                String result = backtracking(str + i, n, k);
                if (result != null) {
                    return result;
                }
            }
        }

        return null;
    }
}
