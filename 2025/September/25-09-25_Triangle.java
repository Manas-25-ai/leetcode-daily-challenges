/**
 * Problem: Given a triangle of integers, find the minimum path sum from top to bottom.
 *          At each step, you may move to adjacent numbers on the row below.
 *
 * Approach:
 * - Use Depth-First Search (DFS) to explore all paths from top to bottom.
 * - Apply memoization to cache intermediate results and avoid redundant calculations.
 * - At each cell (i, j), recursively compute the minimum path sum from that point downward.
 * - Base case: If we're at the last row, return the value at that cell.
 * - Recursive case: Add current value to the minimum of the two possible downward paths.
 *
 * Time Complexity:
 * - O(n²): Each cell in the triangle is visited once due to memoization.
 * - Without memoization, it would be exponential (O(2^n)).
 *
 * Space Complexity:
 * - O(n²): For the memoization table.
 * - O(n): For the recursion stack (depth of triangle).
 *
 * Notes:
 * - Efficient for large triangles due to memoization.
 * - Avoids bottom-up DP by using top-down recursion with caching.
 * - Useful for dynamic programming practice and recursive optimization.
 */
import java.util.*;

class Solution {
    private int[][] memo; // class-level field to store cached results

    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0) return 0;

        int n = triangle.size();
        memo = new int[n][n];
        for (int[] row : memo) Arrays.fill(row, Integer.MAX_VALUE);

        return dfs(triangle, 0, 0);
    }

    private int dfs(List<List<Integer>> triangle, int i, int j) {
        int n = triangle.size();
        if (i == n - 1) {
            return triangle.get(i).get(j); // base case: bottom row
        }

        if (memo[i][j] != Integer.MAX_VALUE) {
            return memo[i][j]; // return cached result
        }

        int down = dfs(triangle, i + 1, j);       // move straight down
        int diag = dfs(triangle, i + 1, j + 1);   // move diagonally right
        memo[i][j] = triangle.get(i).get(j) + Math.min(down, diag);

        return memo[i][j];
    }
}