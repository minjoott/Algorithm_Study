package rerereview.leetcode;

/**
 * https://leetcode.com/problems/keys-and-rooms/
 * 841. Keys and Rooms
 * 2025/08/31
 */

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

class KeysAndRooms_dfs {
    boolean[] visited;

    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int n = rooms.size();
        visited = new boolean[n];

        visited[0] = true;
        dfs(0, rooms);

        for (boolean v : visited) {
            if (!v) return false;
        }
        return true;
    }

    void dfs(int curr, List<List<Integer>> rooms) {
        for (int next : rooms.get(curr)) {
            if (!visited[next]) {
                visited[next] = true;
                dfs(next, rooms);
            }
        }
    }
}

class KeysAndRooms_bfs {
    boolean[] visited;

    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int n = rooms.size();
        visited = new boolean[n];

        Deque<Integer> queue = new ArrayDeque<>();
        visited[0] = true;
        queue.offer(0);
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            for (int next : rooms.get(curr)) {
                if (!visited[next]) {
                    visited[next] = true;
                    queue.offer(next);
                }
            }
        }

        for (boolean v : visited) {
            if (!v) return false;
        }
        return true;
    }
}