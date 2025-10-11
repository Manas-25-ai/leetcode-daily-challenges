/**
 * Problem: Pacific Atlantic Water Flow (LeetCode)
 * -----------------------------------------------
 * Given an m x n matrix of heights, find all coordinates where water can flow
 * to both the Pacific and Atlantic oceans.
 *
 * Intuition:
 * - Water can only flow from higher to equal/lower height cells.
 * - Instead of simulating water flow from every cell, we reverse the logic:
 *   • Start DFS from the borders of each ocean (Pacific and Atlantic).
 *   • Mark all cells that can reach that ocean.
 * - The intersection of cells reachable from both oceans gives the answer.
 *
 * Approach:
 * 1. Run DFS from Pacific edges (top row and left column).
 * 2. Run DFS from Atlantic edges (bottom row and right column).
 * 3. Any cell visited by both searches → can reach both oceans.
 *
 * Time Complexity: O(m * n)
 * Space Complexity: O(m * n)
 */

import java.util.*;

class Solution {
    private int m, n;
    private final int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        m = heights.length;
        n = heights[0].length;
        boolean[][] pacific = new boolean[m][n];
        boolean[][] atlantic = new boolean[m][n];

        // DFS from Pacific (top and left edges)
        for (int j = 0; j < n; j++) dfs(0, j, heights, pacific);
        for (int i = 0; i < m; i++) dfs(i, 0, heights, pacific);

        // DFS from Atlantic (bottom and right edges)
        for (int j = 0; j < n; j++) dfs(m - 1, j, heights, atlantic);
        for (int i = 0; i < m; i++) dfs(i, n - 1, heights, atlantic);

        // Cells reachable by both oceans
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pacific[i][j] && atlantic[i][j]) {
                    result.add(Arrays.asList(i, j));
                }
            }
        }

        return result;
    }

    // DFS: mark all cells that can flow into the current ocean
    private void dfs(int i, int j, int[][] heights, boolean[][] visited) {
        if (visited[i][j]) return;
        visited[i][j] = true;

        for (int[] d : directions) {
            int x = i + d[0], y = j + d[1];
            if (x < 0 || y < 0 || x >= m || y >= n) continue;
            if (heights[x][y] < heights[i][j]) continue; // can only move to equal/higher cells
            dfs(x, y, heights, visited);
        }
    }
}
