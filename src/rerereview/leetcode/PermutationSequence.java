package rerereview.leetcode;

/**
 * https://leetcode.com/problems/permutation-sequence/description/
 * 60. Permutation Sequence
 * 2025/08/29
 */

import java.util.ArrayList;
import java.util.List;

public class PermutationSequence {
    int cnt = 0;
    String str = "";

    public String getPermutation(int n, int k) {
        backtracking(new ArrayList<>(), new boolean[n + 1], n, k);
        return str;
    }

    boolean backtracking(List<Integer> list, boolean[] visited, int n, int k) {
        if (list.size() == n) {
            ++cnt;
            if (cnt == k) {
                for (int num : list) {
                    str += num;
                }
                return true;
            }
            return false;
        }

        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                list.add(i);
                visited[i] = true;

                if (backtracking(list, visited, n, k)) {
                    return true;
                }

                list.remove(list.size() - 1);
                visited[i] = false;
            }
        }
        return false;
    }
}
