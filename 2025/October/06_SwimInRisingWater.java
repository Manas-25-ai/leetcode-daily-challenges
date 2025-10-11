/**
 * Problem: Swim in Rising Water (LeetCode)
 * ----------------------------------------
 * You are given an n x n grid where each cell's value represents the time
 * when the water level rises enough to cover that cell. Starting from (0,0),
 * you can swim to adjacent cells (up/down/left/right) if their elevation
 * is less than or equal to the current water level.
 *
 * Goal: Find the minimum time required to reach (n-1, n-1).
 *
 * Intuition:
 * - This is a shortest-path problem on a grid where cost = max(elevation encountered so far).
 * - Use Dijkstra’s algorithm (with a min-heap) to always expand the lowest “time” cell.
 * - Each move updates time = max(current time, next cell’s elevation).
 * - The first time we reach the destination, that’s the minimum possible time.
 *
 * Time Complexity: O(n² log n) — each cell pushed once into a priority queue.
 * Space Complexity: O(n²) — for the result matrix and priority queue.
 */

import java.util.*;

class Solution {
    private final int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public int swimInWater(int[][] grid) {
        int n = grid.length;
        int[][] minTime = new int[n][n];
        for (int[] row : minTime) Arrays.fill(row, Integer.MAX_VALUE);

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]); // [time, i, j]
        pq.offer(new int[]{grid[0][0], 0, 0});
        minTime[0][0] = grid[0][0];

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int time = curr[0], i = curr[1], j = curr[2];

            if (i == n - 1 && j == n - 1) return time;
            if (time > minTime[i][j]) continue; // skip outdated path

            for (int[] dir : directions) {
                int ni = i + dir[0], nj = j + dir[1];
                if (ni < 0 || nj < 0 || ni >= n || nj >= n) continue;

                int nextTime = Math.max(time, grid[ni][nj]);
                if (nextTime < minTime[ni][nj]) {
                    minTime[ni][nj] = nextTime;
                    pq.offer(new int[]{nextTime, ni, nj});
                }
            }
        }

        return -1; // unreachable
    }
}
