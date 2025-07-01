package review.leetcode;

import java.util.List;

public class KeysAndRooms {
    private boolean[] visited;

    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        final int n = rooms.size();
        visited = new boolean[n];

        visited[0] = true;
        dfs(0, rooms);

        for (boolean v : visited) {
            if (!v) return false;
        }
        return true;
    }

    private void dfs(int curr, List<List<Integer>> rooms) {
        for (int key : rooms.get(curr)) {
            if (!visited[key]) {
                visited[key] = true;
                dfs(key, rooms);
            }
        }
    }
}
