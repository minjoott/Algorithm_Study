package problems.leetcode;

/**
 * https://leetcode.com/problems/keys-and-rooms/
 * [LeetCode] 841. Keys and Rooms
 * solved at: 251009
 */

import java.util.List;

public class KeysAndRooms {

    class Solution1_dfs {
        int cnt = 0;

        public boolean canVisitAllRooms(List<List<Integer>> rooms) {
            dfs(0, new boolean[rooms.size()], rooms);
            return (cnt == rooms.size()) ? true : false;
        }

        void dfs(int room, boolean[] visited, List<List<Integer>> rooms) {
            visited[room] = true;
            cnt++;

            for (int key : rooms.get(room)) {
                if (!visited[key]) {
                    dfs(key, visited, rooms);
                }
            }
        }
    }
}
