/**
 * Problem: Trapping Rain Water II (LeetCode)
 * ------------------------------------------
 * Given a 2D height map, find the total amount of rainwater that can be trapped after raining.
 *
 * Intuition:
 * - Think of it as a "3D container" where water can be trapped between taller boundaries.
 * - The lowest boundary determines how much water can stay at a given cell.
 * - Use a Min-Heap (PriorityQueue) to always expand from the lowest boundary outward.
 *
 * Approach:
 * 1. Push all boundary cells into a min-heap (they can't trap water).
 * 2. Process cells from the heap:
 *      • For each popped cell, check its 4 neighbors.
 *      • If a neighbor is lower, water can be trapped = (current height - neighbor height).
 *      • Update the neighbor’s effective height to max(current, neighbor) and add to heap.
 * 3. Keep marking visited cells to avoid reprocessing.
 *
 * Time Complexity:  O(m * n * log(m * n))  → due to heap operations
 * Space Complexity: O(m * n)  → for visited matrix and heap storage
 */

import java.util.PriorityQueue;

class Solution {
    public int trapRainWater(int[][] heightMap) {
        int m = heightMap.length, n = heightMap[0].length;
        if (m < 3 || n < 3) return 0; // too small to hold water

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]); // [height, x, y]
        boolean[][] visited = new boolean[m][n];

        // Add all boundary cells to heap
        for (int i = 0; i < m; i++) {
            pq.offer(new int[]{heightMap[i][0], i, 0});
            pq.offer(new int[]{heightMap[i][n - 1], i, n - 1});
            visited[i][0] = visited[i][n - 1] = true;
        }
        for (int j = 0; j < n; j++) {
            pq.offer(new int[]{heightMap[0][j], 0, j});
            pq.offer(new int[]{heightMap[m - 1][j], m - 1, j});
            visited[0][j] = visited[m - 1][j] = true;
        }

        int result = 0;
        int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        // Process cells from the smallest boundary upward
        while (!pq.isEmpty()) {
            int[] cell = pq.poll();
            int height = cell[0], x = cell[1], y = cell[2];

            for (int[] d : dirs) {
                int nx = x + d[0], ny = y + d[1];
                if (nx >= 0 && ny >= 0 && nx < m && ny < n && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    // If neighbor is lower, trap water equal to the difference
                    result += Math.max(0, height - heightMap[nx][ny]);
                    // Push the effective boundary height into heap
                    pq.offer(new int[]{Math.max(height, heightMap[nx][ny]), nx, ny});
                }
            }
        }

        return result;
    }
}
