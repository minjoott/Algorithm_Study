package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

class KeysAndRooms_bfs {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int n = rooms.size();
        boolean[] visited = new boolean[n];
        Deque<Integer> queue = new ArrayDeque<>();

        queue.add(0);
        visited[0] = true;

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (int key : rooms.get(cur)) {
                if (!visited[key]) {
                    queue.add(key);
                    visited[key] = true;
                }
            }
        }

        for (boolean v : visited) {
            if (!v) return false;
        }
        return true;
    }
}

class KeysAndRooms_dfs {
    int n;
    boolean[] visited;

    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        n = rooms.size();
        visited = new boolean[n];

        visited[0] = true;
        dfs(0, rooms);

        for (boolean v : visited) {
            if (!v) return false;
        }
        return true;
    }

    void dfs(int cur, List<List<Integer>> rooms) {
        for (int key : rooms.get(cur)) {
            if (!visited[key]) {
                visited[key] = true;
                dfs(key, rooms);
            }
        }
    }
}
