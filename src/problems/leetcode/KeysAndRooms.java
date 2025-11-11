package problems.leetcode;

/**
 * https://leetcode.com/problems/keys-and-rooms/
 * [LeetCode] 841. Keys and Rooms
 * solved at: 251110
 */

import java.util.List;

public class KeysAndRooms {
    class Solution {
        public boolean canVisitAllRooms(List<List<Integer>> rooms) {
            int n = rooms.size();
            boolean[] visited = new boolean[n];

            dfs(0, visited, rooms);

            for (boolean v : visited) {
                if (!v) return false;
            }
            return true;
        }

        void dfs(int curr, boolean[] visited, List<List<Integer>> rooms) {
            visited[curr] = true;

            for (int key : rooms.get(curr)) {
                if (!visited[key]) dfs(key, visited, rooms);
            }
        }
    }
}
